package logicandcomputings.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class NotGateOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean control_flag = false;
		double gathered_signal = 0;
		double signal_x = 0;
		double signal_y = 0;
		double signal_z = 0;
		SignalproducersbaseProcedure.execute(world, x, y, z, 0, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "output_signal_value"));
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}