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

public class DecoderSignalproducersProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Direction block_facing = Direction.NORTH;
		Direction target_facing = Direction.NORTH;
		double gathered_signal_amount = 0;
		double loop_counter = 0;
		double target_x = 0;
		double target_y = 0;
		double target_z = 0;
		double target_signal = 0;
		double gathered_signal = 0;
		double connected_faces_amount = 0;
		double selection_power = 0;
		double dec0_x = 0;
		double dec0_y = 0;
		double dec0_z = 0;
		double dec1_x = 0;
		double dec1_y = 0;
		double dec1_z = 0;
		String connected_face = "";
		String dec0_connection = "";
		String dec1_connection = "";
		selection_power = 0;
		if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.NORTH) {
			dec0_x = x - 1;
			dec0_y = y;
			dec0_z = z;
			dec1_x = x + 1;
			dec1_y = y;
			dec1_z = z;
			selection_power = getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "signal_value");
		} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.SOUTH) {
			dec0_x = x + 1;
			dec0_y = y;
			dec0_z = z;
			dec1_x = x - 1;
			dec1_y = y;
			dec1_z = z;
			selection_power = getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "signal_value");
		} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.WEST) {
			dec0_x = x;
			dec0_y = y;
			dec0_z = z + 1;
			dec1_x = x;
			dec1_y = y;
			dec1_z = z - 1;
			selection_power = getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "signal_value");
		} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.EAST) {
			dec0_x = x;
			dec0_y = y;
			dec0_z = z - 1;
			dec1_x = x;
			dec1_y = y;
			dec1_z = z + 1;
			selection_power = getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "signal_value");
		}
		if (selection_power == 0) {
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
					_blockEntity.getPersistentData().putDouble("output_face_x", dec0_x);
					_blockEntity.getPersistentData().putDouble("output_face_y", dec0_y);
					_blockEntity.getPersistentData().putDouble("output_face_z", dec0_z);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (selection_power >= 1) {
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
					_blockEntity.getPersistentData().putDouble("output_face_x", dec1_x);
					_blockEntity.getPersistentData().putDouble("output_face_y", dec1_y);
					_blockEntity.getPersistentData().putDouble("output_face_z", dec1_z);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
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

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}