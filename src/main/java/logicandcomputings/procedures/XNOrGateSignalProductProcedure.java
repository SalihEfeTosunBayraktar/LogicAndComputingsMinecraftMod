package logicandcomputings.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import logicandcomputings.init.LogicandcomputingsModBlocks;

public class XNOrGateSignalProductProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Direction block_facing = Direction.NORTH;
		Direction target_facing = Direction.NORTH;
		String connected_face = "";
		double gathered_signal_amount = 0;
		double loop_counter = 0;
		double target_x = 0;
		double target_y = 0;
		double target_z = 0;
		double target_signal = 0;
		double gathered_signal = 0;
		double connected_faces_amount = 0;
		if (!(gathered_signal_amount == 0)) {
			gathered_signal_amount = 0;
		}
		if (!(block_facing == (getBlockDirection(world, BlockPos.containing(x, y, z))))) {
			block_facing = getBlockDirection(world, BlockPos.containing(x, y, z));
		}
		loop_counter = 0;
		connected_faces_amount = 0;
		for (int index0 = 0; index0 < 6; index0++) {
			loop_counter = loop_counter + 1;
			if (loop_counter == 1) {
				target_facing = Direction.NORTH;
				target_x = x;
				target_y = y;
				target_z = z - 1;
				connected_face = "connected_south";
			} else if (loop_counter == 2) {
				target_facing = Direction.SOUTH;
				target_x = x;
				target_y = y;
				target_z = z + 1;
				connected_face = "connected_north";
			} else if (loop_counter == 3) {
				target_facing = Direction.WEST;
				target_x = x - 1;
				target_y = y;
				target_z = z;
				connected_face = "connected_east";
			} else if (loop_counter == 4) {
				target_facing = Direction.EAST;
				target_x = x + 1;
				target_y = y;
				target_z = z;
				connected_face = "connected_west";
			} else if (loop_counter == 5) {
				target_facing = Direction.UP;
				target_x = x;
				target_y = y + 1;
				target_z = z;
				connected_face = "connected_down";
			} else if (loop_counter == 6) {
				target_facing = Direction.DOWN;
				target_x = x;
				target_y = y - 1;
				target_z = z;
				connected_face = "connected_up";
			}
			if (!(block_facing == target_facing)) {
				if ((world.getBlockState(BlockPos.containing(target_x, target_y, target_z))).getBlock() == LogicandcomputingsModBlocks.WIRE.get() && getBlockNBTLogic(world, BlockPos.containing(target_x, target_y, target_z), connected_face)) {
					connected_faces_amount = connected_faces_amount + 1;
					if (getBlockNBTNumber(world, BlockPos.containing(target_x, target_y, target_z), "signal_value") > 0) {
						gathered_signal_amount = gathered_signal_amount + 1;
					}
				}
			}
		}
		if (gathered_signal_amount == 0 || gathered_signal_amount == connected_faces_amount) {
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "signal_value") != getBlockNBTNumber(world, BlockPos.containing(x, y, z), "output_signal_value")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("signal_value", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "output_signal_value")));
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
			}
		} else {
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "signal_value") != 0) {
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
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("powered") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
		}
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
}