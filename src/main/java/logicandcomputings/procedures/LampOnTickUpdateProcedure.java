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
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "north_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip8
					? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip8)
					: -1) != (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z - 1))), "power") instanceof IntegerProperty _getip10 ? (world.getBlockState(BlockPos.containing(x, y, z - 1))).getValue(_getip10) : -1)) {
				{
					int _value = getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z - 1))), "power") instanceof IntegerProperty _getip12 ? (world.getBlockState(BlockPos.containing(x, y, z - 1))).getValue(_getip12) : -1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "south_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip16 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip16) : -1) != getBlockNBTNumber(world,
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
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "south_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip22
					? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip22)
					: -1) != (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z + 1))), "power") instanceof IntegerProperty _getip24 ? (world.getBlockState(BlockPos.containing(x, y, z + 1))).getValue(_getip24) : -1)) {
				{
					int _value = getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z + 1))), "power") instanceof IntegerProperty _getip26 ? (world.getBlockState(BlockPos.containing(x, y, z + 1))).getValue(_getip26) : -1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "west_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip30 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip30) : -1) != getBlockNBTNumber(world,
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
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "west_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip36
					? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip36)
					: -1) != (getPropertyByName((world.getBlockState(BlockPos.containing(x - 1, y, z))), "power") instanceof IntegerProperty _getip38 ? (world.getBlockState(BlockPos.containing(x - 1, y, z))).getValue(_getip38) : -1)) {
				{
					int _value = getPropertyByName((world.getBlockState(BlockPos.containing(x - 1, y, z))), "power") instanceof IntegerProperty _getip40 ? (world.getBlockState(BlockPos.containing(x - 1, y, z))).getValue(_getip40) : -1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "east_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip44 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip44) : -1) != getBlockNBTNumber(world,
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
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "east_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip50
					? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip50)
					: -1) != (getPropertyByName((world.getBlockState(BlockPos.containing(x + 1, y, z))), "power") instanceof IntegerProperty _getip52 ? (world.getBlockState(BlockPos.containing(x + 1, y, z))).getValue(_getip52) : -1)) {
				{
					int _value = getPropertyByName((world.getBlockState(BlockPos.containing(x + 1, y, z))), "power") instanceof IntegerProperty _getip54 ? (world.getBlockState(BlockPos.containing(x + 1, y, z))).getValue(_getip54) : -1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "up_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip58 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip58) : -1) != getBlockNBTNumber(world,
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
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "up_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip64
					? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip64)
					: -1) != (getPropertyByName((world.getBlockState(BlockPos.containing(x, y + 1, z))), "power") instanceof IntegerProperty _getip66 ? (world.getBlockState(BlockPos.containing(x, y + 1, z))).getValue(_getip66) : -1)) {
				{
					int _value = getPropertyByName((world.getBlockState(BlockPos.containing(x, y + 1, z))), "power") instanceof IntegerProperty _getip68 ? (world.getBlockState(BlockPos.containing(x, y + 1, z))).getValue(_getip68) : -1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("gathered_signal") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "down_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip72 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip72) : -1) != getBlockNBTNumber(world,
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
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "down_reading")) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "gathered_signal") instanceof IntegerProperty _getip78
					? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip78)
					: -1) != (getPropertyByName((world.getBlockState(BlockPos.containing(x, y - 1, z))), "power") instanceof IntegerProperty _getip80 ? (world.getBlockState(BlockPos.containing(x, y - 1, z))).getValue(_getip80) : -1)) {
				{
					int _value = getPropertyByName((world.getBlockState(BlockPos.containing(x, y - 1, z))), "power") instanceof IntegerProperty _getip82 ? (world.getBlockState(BlockPos.containing(x, y - 1, z))).getValue(_getip82) : -1;
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