package logicandcomputings.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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

public class WireOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		String connected_other_blocks = "";
		String connected_this_block = "";
		double localmax = 0;
		double signal_x = 0;
		double signal_y = 0;
		double signal_z = 0;
		double counter = 0;
		Direction flag_facing = Direction.NORTH;
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "south") instanceof BooleanProperty _getbp1 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp1)) != getBlockNBTLogic(world,
				BlockPos.containing(x, y, z), "connected_south")) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "connected_south"))), 3);
			}
		}
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "north") instanceof BooleanProperty _getbp6 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp6)) != getBlockNBTLogic(world,
				BlockPos.containing(x, y, z), "connected_north")) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "connected_north"))), 3);
			}
		}
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "up") instanceof BooleanProperty _getbp11 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp11)) != getBlockNBTLogic(world,
				BlockPos.containing(x, y, z), "connected_up")) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("up") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "connected_up"))), 3);
			}
		}
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "down") instanceof BooleanProperty _getbp16 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp16)) != getBlockNBTLogic(world,
				BlockPos.containing(x, y, z), "connected_down")) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("down") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "connected_down"))), 3);
			}
		}
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "west") instanceof BooleanProperty _getbp21 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp21)) != getBlockNBTLogic(world,
				BlockPos.containing(x, y, z), "connected_west")) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "connected_west"))), 3);
			}
		}
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "east") instanceof BooleanProperty _getbp26 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp26)) != getBlockNBTLogic(world,
				BlockPos.containing(x, y, z), "connected_east")) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "connected_east"))), 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "signal_value") > 0
				&& (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "has_signal") instanceof BooleanProperty _getbp32 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp32)) == false) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("has_signal") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "signal_value") == 0
				&& (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "has_signal") instanceof BooleanProperty _getbp36 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp36)) == true) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("has_signal") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
			}
		}
		if (localmax != 0) {
			localmax = 0;
			counter = 0;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == LogicandcomputingsModBlocks.WIRE.get()) {
			for (int index0 = 0; index0 < 6; index0++) {
				counter = counter + 1;
				if (counter == 1) {
					signal_x = x;
					signal_y = y;
					signal_z = z + 1;
					flag_facing = Direction.NORTH;
					connected_other_blocks = "connected_north";
					connected_this_block = "connected_south";
				} else if (counter == 2) {
					signal_x = x;
					signal_y = y;
					signal_z = z - 1;
					flag_facing = Direction.SOUTH;
					connected_other_blocks = "connected_south";
					connected_this_block = "connected_north";
				} else if (counter == 3) {
					signal_x = x + 1;
					signal_y = y;
					signal_z = z;
					flag_facing = Direction.WEST;
					connected_other_blocks = "connected_west";
					connected_this_block = "connected_east";
				} else if (counter == 4) {
					signal_x = x - 1;
					signal_y = y;
					signal_z = z;
					flag_facing = Direction.EAST;
					connected_other_blocks = "connected_east";
					connected_this_block = "connected_west";
				} else if (counter == 5) {
					signal_x = x;
					signal_y = y + 1;
					signal_z = z;
					flag_facing = Direction.DOWN;
					connected_other_blocks = "connected_down";
					connected_this_block = "connected_up";
				} else if (counter == 6) {
					signal_x = x;
					signal_y = y - 1;
					signal_z = z;
					flag_facing = Direction.UP;
					connected_other_blocks = "connected_up";
					connected_this_block = "connected_down";
				}
				if ((world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))).getBlock() == LogicandcomputingsModBlocks.WIRE.get()) {
					if (getBlockNBTLogic(world, BlockPos.containing(signal_x, signal_y, signal_z), connected_other_blocks) == true && getBlockNBTLogic(world, BlockPos.containing(x, y, z), connected_this_block) == true) {
						if (localmax < getBlockNBTNumber(world, BlockPos.containing(signal_x, signal_y, signal_z), "signal_value")) {
							localmax = getBlockNBTNumber(world, BlockPos.containing(signal_x, signal_y, signal_z), "signal_value");
						}
					}
				}
				if ((world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))).getBlock() == Blocks.REDSTONE_BLOCK && getBlockNBTLogic(world, BlockPos.containing(x, y, z), connected_this_block) == true) {
					if (localmax < 15) {
						localmax = 15;
					}
				}
				if ((world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))).getBlock() == Blocks.REDSTONE_WIRE && getBlockNBTLogic(world, BlockPos.containing(x, y, z), connected_this_block) == true) {
					if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "redstone_emits") == false) {
						if (localmax < (getPropertyByName((world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))), "power") instanceof IntegerProperty _getip54
								? (world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))).getValue(_getip54)
								: -1)) {
							localmax = getPropertyByName((world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))), "power") instanceof IntegerProperty _getip56
									? (world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))).getValue(_getip56)
									: -1;
						}
					}
				}
				if ((world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))).is(BlockTags.create(ResourceLocation.parse("logic_and_computings:signal_source_blocks")))
						&& getBlockNBTLogic(world, BlockPos.containing(x, y, z), connected_this_block) == true) {
					if (localmax < getBlockNBTNumber(world, BlockPos.containing(signal_x, signal_y, signal_z), "signal_value")) {
						localmax = getBlockNBTNumber(world, BlockPos.containing(signal_x, signal_y, signal_z), "signal_value");
					}
				}
				if ((world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))).is(BlockTags.create(ResourceLocation.parse("logic_and_computings:gate_blocks")))
						&& getBlockNBTLogic(world, BlockPos.containing(x, y, z), connected_this_block) == true) {
					if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(signal_x, signal_y, signal_z))))) == flag_facing) {
						if (localmax < getBlockNBTNumber(world, BlockPos.containing(signal_x, signal_y, signal_z), "signal_value")) {
							localmax = getBlockNBTNumber(world, BlockPos.containing(signal_x, signal_y, signal_z), "signal_value");
						}
					}
				}
			}
			if (localmax > 0) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("target_signal_power", (localmax - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("target_signal_power", 0);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal_power") < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "signal_value")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("signal_value", 0);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (world instanceof Level _level)
					_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
			} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal_power") > getBlockNBTNumber(world, BlockPos.containing(x, y, z), "signal_value")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("signal_value", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal_power")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (world instanceof Level _level)
					_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
			}
		}
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