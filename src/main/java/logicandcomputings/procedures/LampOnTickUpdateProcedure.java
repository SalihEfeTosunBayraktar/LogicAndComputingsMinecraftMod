package logicandcomputings.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class LampOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "north_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip2 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip2) : -1) != getBlockNBTNumber(world,
					BlockPos.containing(x, y, z - 1), "signal_value")) {
				{
					int _value = (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z - 1), "signal_value");
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "south_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip8 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip8) : -1) != getBlockNBTNumber(world,
					BlockPos.containing(x, y, z + 1), "signal_value")) {
				{
					int _value = (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z + 1), "signal_value");
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "west_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip14 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip14) : -1) != getBlockNBTNumber(world,
					BlockPos.containing(x - 1, y, z), "signal_value")) {
				{
					int _value = (int) getBlockNBTNumber(world, BlockPos.containing(x - 1, y, z), "signal_value");
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "east_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip20 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip20) : -1) != getBlockNBTNumber(world,
					BlockPos.containing(x + 1, y, z), "signal_value")) {
				{
					int _value = (int) getBlockNBTNumber(world, BlockPos.containing(x + 1, y, z), "signal_value");
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "up_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip26 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip26) : -1) != getBlockNBTNumber(world,
					BlockPos.containing(x, y + 1, z), "signal_value")) {
				{
					int _value = (int) getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "signal_value");
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "down_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip32 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip32) : -1) != getBlockNBTNumber(world,
					BlockPos.containing(x, y - 1, z), "signal_value")) {
				{
					int _value = (int) getBlockNBTNumber(world, BlockPos.containing(x, y - 1, z), "signal_value");
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
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

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}