package logicandcomputings.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BufferOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double gathered_signal = 0;
		double signal_x = 0;
		double signal_y = 0;
		double signal_z = 0;
		boolean control_flag = false;
		SignalproducersbaseProcedure.execute(world, x, y, z, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "output_signal_value"), 0);
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}