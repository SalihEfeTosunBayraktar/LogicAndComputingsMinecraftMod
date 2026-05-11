package logicandcomputings.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import logicandcomputings.init.LogicandcomputingsModBlocks;

public class RedstonevaluereadingproduceProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, double blockx, double blocky, double blockz) {
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == LogicandcomputingsModBlocks.WIRE.get()) {
			if (getBlockNBTLogic(world, BlockPos.containing(blockx, blocky, blockz), "redstone_emits")) {
				if ((world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).getBlock() == Blocks.REDSTONE_BLOCK) {
					return 15;
				}
				if ((world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).getBlock() == Blocks.LEVER || (world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).is(BlockTags.create(ResourceLocation.parse("minecraft:buttons")))
						|| (world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).is(BlockTags.create(ResourceLocation.parse("minecraft:pressure_plates")))) {
					if (getPropertyByName((world.getBlockState(BlockPos.containing(blockx, blocky, blockz))), "powered") instanceof BooleanProperty _getbp12 && (world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).getValue(_getbp12)) {
						return 15;
					} else {
						return 0;
					}
				}
			}
		} else {
			if ((world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).getBlock() == Blocks.REDSTONE_BLOCK) {
				return 15;
			}
			if ((world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).getBlock() == Blocks.LEVER || (world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).is(BlockTags.create(ResourceLocation.parse("minecraft:buttons")))
					|| (world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).is(BlockTags.create(ResourceLocation.parse("minecraft:pressure_plates")))) {
				if (getPropertyByName((world.getBlockState(BlockPos.containing(blockx, blocky, blockz))), "powered") instanceof BooleanProperty _getbp22 && (world.getBlockState(BlockPos.containing(blockx, blocky, blockz))).getValue(_getbp22)) {
					return 15;
				} else {
					return 0;
				}
			}
		}
		return 0;
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