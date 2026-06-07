package logicandcomputings.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import logicandcomputings.init.LogicandcomputingsModBlocks;

public class ModularWireOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double localmax = 0;
		BlockPos wirePos = BlockPos.containing(x, y, z);
		BlockState wireState = world.getBlockState(wirePos);
		
		// 1. Connection states sync
		for (Direction dir : Direction.values()) {
			String name = dir.getName();
			String tag = "connected_" + name;
			boolean propertyVal = getPropertyByName(wireState, name) instanceof BooleanProperty bp && wireState.getValue(bp);
			boolean nbtVal = getBlockNBTLogic(world, wirePos, tag);
			
			if (propertyVal != nbtVal) {
				BlockState bs = world.getBlockState(wirePos);
				if (bs.getBlock().getStateDefinition().getProperty(name) instanceof BooleanProperty booleanProp) {
					world.setBlock(wirePos, bs.setValue(booleanProp, nbtVal), 3);
					wireState = world.getBlockState(wirePos); // Update reference
				}
			}
		}
		
		// 2. has_signal status sync
		double currentSignal = getBlockNBTNumber(world, wirePos, "signal_value");
		boolean hasSignalProp = getPropertyByName(wireState, "has_signal") instanceof BooleanProperty bp && wireState.getValue(bp);
		
		if (currentSignal > 0 && !hasSignalProp) {
			if (wireState.getBlock().getStateDefinition().getProperty("has_signal") instanceof BooleanProperty booleanProp) {
				world.setBlock(wirePos, wireState.setValue(booleanProp, true), 3);
				wireState = world.getBlockState(wirePos);
			}
		} else if (currentSignal == 0 && hasSignalProp) {
			if (wireState.getBlock().getStateDefinition().getProperty("has_signal") instanceof BooleanProperty booleanProp) {
				world.setBlock(wirePos, wireState.setValue(booleanProp, false), 3);
				wireState = world.getBlockState(wirePos);
			}
		}
		
		// 3. Scan neighbors for power
		if (wireState.is(BlockTags.create(ResourceLocation.parse("logic_and_computings:wires")))) {
			for (Direction dir : Direction.values()) {
				BlockPos neighborPos = wirePos.relative(dir);
				double power = getPowerFromNeighbor(world, wirePos, neighborPos, dir);
				if (power > localmax) {
					localmax = power;
				}
			}
		}
		
		// 4. Update NBT power target values
		if (localmax > 0) {
			if (!world.isClientSide()) {
				BlockState _bs = world.getBlockState(wirePos);
				BlockEntity _blockEntity = world.getBlockEntity(wirePos);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("target_signal_power", (localmax - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(wirePos, _bs, _bs, 3);
			}
		} else {
			if (!world.isClientSide()) {
				BlockState _bs = world.getBlockState(wirePos);
				BlockEntity _blockEntity = world.getBlockEntity(wirePos);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("target_signal_power", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(wirePos, _bs, _bs, 3);
			}
		}
		
		// 5. Update NBT signal_value
		double targetPower = getBlockNBTNumber(world, wirePos, "target_signal_power");
		double signalValue = getBlockNBTNumber(world, wirePos, "signal_value");
		
		if (targetPower < signalValue) {
			if (!world.isClientSide()) {
				BlockState _bs = world.getBlockState(wirePos);
				BlockEntity _blockEntity = world.getBlockEntity(wirePos);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("signal_value", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(wirePos, _bs, _bs, 3);
			}
			if (world instanceof Level _level) {
				_level.updateNeighborsAt(wirePos, _level.getBlockState(wirePos).getBlock());
			}
		} else if (targetPower > signalValue) {
			if (!world.isClientSide()) {
				BlockState _bs = world.getBlockState(wirePos);
				BlockEntity _blockEntity = world.getBlockEntity(wirePos);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("signal_value", targetPower);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(wirePos, _bs, _bs, 3);
			}
			if (world instanceof Level _level) {
				_level.updateNeighborsAt(wirePos, _level.getBlockState(wirePos).getBlock());
			}
		}
	}
	
	private static double getPowerFromNeighbor(LevelAccessor world, BlockPos wirePos, BlockPos neighborPos, Direction directionToNeighbor) {
		BlockState state = world.getBlockState(neighborPos);
		BlockEntity entity = world.getBlockEntity(neighborPos);
		
		// Wire to Wire Connection
		if (state.is(BlockTags.create(ResourceLocation.parse("logic_and_computings:wires")))) {
			String connectedThis = "connected_" + directionToNeighbor.getName();
			String connectedOther = "connected_" + directionToNeighbor.getOpposite().getName();
			
			if (getBlockNBTLogic(world, wirePos, connectedThis) && getBlockNBTLogic(world, neighborPos, connectedOther)) {
				return getBlockNBTNumber(world, neighborPos, "signal_value");
			}
			return 0;
		}
		
		// If wire is not connected in this direction, it cannot draw power
		String connectedThis = "connected_" + directionToNeighbor.getName();
		if (!getBlockNBTLogic(world, wirePos, connectedThis)) {
			return 0;
		}
		
		// Redstone block
		if (state.getBlock() == Blocks.REDSTONE_BLOCK) {
			return 15;
		}
		
		// Lever or button
		if (state.getBlock() == Blocks.LEVER || state.is(BlockTags.create(ResourceLocation.parse("minecraft:buttons")))) {
			if (state.hasProperty(BlockStateProperties.POWERED) && state.getValue(BlockStateProperties.POWERED)) {
				if (!getBlockNBTLogic(world, wirePos, "redstone_emits")) {
					return 15;
				}
			}
			return 0;
		}
		
		// Redstone wire
		if (state.getBlock() == Blocks.REDSTONE_WIRE) {
			if (!getBlockNBTLogic(world, wirePos, "redstone_emits")) {
				if (state.hasProperty(BlockStateProperties.POWER)) {
					return state.getValue(BlockStateProperties.POWER);
				}
			}
			return 0;
		}
		
		// Custom blocks
		if (entity != null) {
			// Decoder Check
			if (state.getBlock() == LogicandcomputingsModBlocks.DECODER.get()) {
				double outX = entity.getPersistentData().getDoubleOr("output_face_x", 0);
				double outY = entity.getPersistentData().getDoubleOr("output_face_y", 0);
				double outZ = entity.getPersistentData().getDoubleOr("output_face_z", 0);
				
				if (wirePos.getX() == outX && wirePos.getY() == outY && wirePos.getZ() == outZ) {
					double outVal = entity.getPersistentData().getDoubleOr("output_signal_value", -1);
					if (outVal != -1) {
						return outVal;
					}
					return entity.getPersistentData().getDoubleOr("signal_value", 0);
				}
				return 0;
			}
			
			// Directional logic gates (points towards the wire)
			if (state.is(BlockTags.create(ResourceLocation.parse("logic_and_computings:gate_blocks")))) {
				Direction gateFacing = getDirectionFromBlockState(state);
				if (gateFacing == directionToNeighbor.getOpposite()) {
					return getBlockNBTNumber(world, neighborPos, "signal_value");
				}
				return 0;
			}
			
			// General signal source blocks (outputs in all directions)
			if (state.is(BlockTags.create(ResourceLocation.parse("logic_and_computings:signal_source_blocks")))) {
				return getBlockNBTNumber(world, neighborPos, "signal_value");
			}
		}
		
		return 0;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}
}
