package logicandcomputings.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import logicandcomputings.init.LogicandcomputingsModBlocks;

public class SignalproducersbaseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double output_0, double output_1) {
		double gathered_signal = 0;
		gathered_signal = 0;
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "signal_value") > 16) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("target_signal", 0);
					_blockEntity.getPersistentData().putDouble("adder_value", 0);
					_blockEntity.getPersistentData().putBoolean("adder_mode", false);
					_blockEntity.getPersistentData().putBoolean("repeater_mode", false);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.NORTH) {
			if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BlockTags.create(ResourceLocation.parse("logic_and_computings:gate_blocks")))) {
				if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z + 1))))) == (getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))))) {
					gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x, y, z + 1), "signal_value");
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == LogicandcomputingsModBlocks.WIRE.get() && getBlockNBTLogic(world, BlockPos.containing(x, y, z + 1), "connected_north")) {
				gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x, y, z + 1), "signal_value");
			}
		}
		if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.SOUTH) {
			if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BlockTags.create(ResourceLocation.parse("logic_and_computings:gate_blocks")))) {
				if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z - 1))))) == (getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))))) {
					gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x, y, z - 1), "signal_value");
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == LogicandcomputingsModBlocks.WIRE.get() && getBlockNBTLogic(world, BlockPos.containing(x, y, z - 1), "connected_south")) {
				gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x, y, z - 1), "signal_value");
			}
		}
		if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.WEST) {
			if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BlockTags.create(ResourceLocation.parse("logic_and_computings:gate_blocks")))) {
				if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x + 1, y, z))))) == (getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))))) {
					gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x + 1, y, z), "signal_value");
				}
			} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == LogicandcomputingsModBlocks.WIRE.get() && getBlockNBTLogic(world, BlockPos.containing(x + 1, y, z), "connected_west")) {
				gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x + 1, y, z), "signal_value");
			}
		}
		if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.EAST) {
			if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BlockTags.create(ResourceLocation.parse("logic_and_computings:gate_blocks")))) {
				if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x - 1, y, z))))) == (getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))))) {
					gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x - 1, y, z), "signal_value");
				}
			} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == LogicandcomputingsModBlocks.WIRE.get() && getBlockNBTLogic(world, BlockPos.containing(x - 1, y, z), "connected_east")) {
				gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x - 1, y, z), "signal_value");
			}
		}
		if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.UP) {
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(ResourceLocation.parse("logic_and_computings:gate_blocks")))) {
				if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y - 1, z))))) == (getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))))) {
					gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x, y - 1, z), "signal_value");
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == LogicandcomputingsModBlocks.WIRE.get() && getBlockNBTLogic(world, BlockPos.containing(x, y - 1, z), "connected_up")) {
				gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x, y - 1, z), "signal_value");
			}
		}
		if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.DOWN) {
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(ResourceLocation.parse("logic_and_computings:gate_blocks")))) {
				if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y + 1, z))))) == (getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))))) {
					gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "signal_value");
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == LogicandcomputingsModBlocks.WIRE.get() && getBlockNBTLogic(world, BlockPos.containing(x, y + 1, z), "connected_down")) {
				gathered_signal = getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "signal_value");
			}
		}
		if (gathered_signal > 0) {
			if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "repeater_mode") != true && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "adder_mode") != true) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal") != output_0) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("target_signal", output_0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("powered") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("delay", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "delay_time")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "repeater_mode") == true) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal") != gathered_signal + 1) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("target_signal", (gathered_signal + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("powered") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("delay", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "delay_time")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "adder_mode") == true) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal") != gathered_signal + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "adder_value") + 1) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("target_signal", (gathered_signal + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "adder_value") + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("powered") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("delay", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "delay_time")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
		} else {
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal") != output_1) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("target_signal", output_1);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("powered") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("delay", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "delay_time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "delay") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("delay", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "delay") - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "delay") == 0) {
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "signal_value") != getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("signal_value", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "target_signal")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static Direction getBlockDirection(LevelAccessor world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		Property<?> property = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (property != null && blockState.getValue(property) instanceof Direction direction)
			return direction;
		else if (blockState.hasProperty(BlockStateProperties.AXIS))
			return Direction.fromAxisAndDirection(blockState.getValue(BlockStateProperties.AXIS), Direction.AxisDirection.POSITIVE);
		else if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
			return Direction.fromAxisAndDirection(blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}