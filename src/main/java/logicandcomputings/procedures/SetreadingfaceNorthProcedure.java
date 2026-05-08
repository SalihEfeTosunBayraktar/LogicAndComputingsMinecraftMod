package logicandcomputings.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class SetreadingfaceNorthProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("north_reading", (!getBlockNBTLogic(world, BlockPos.containing(x, y, z), "north_reading")));
				_blockEntity.getPersistentData().putBoolean("south_reading", false);
				_blockEntity.getPersistentData().putBoolean("down_reading", false);
				_blockEntity.getPersistentData().putBoolean("up_reading", false);
				_blockEntity.getPersistentData().putBoolean("west_reading", false);
				_blockEntity.getPersistentData().putBoolean("east_reading", false);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}