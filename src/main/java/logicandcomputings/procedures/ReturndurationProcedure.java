package logicandcomputings.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class ReturndurationProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		return "Duration :" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "duration");
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}