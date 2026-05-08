package logicandcomputings.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.multiplayer.ClientLevel;

import logicandcomputings.procedures.CableconnectorRightclickedProcedure;
import logicandcomputings.procedures.CableconnectorRightclickedOnBlockProcedure;
import logicandcomputings.procedures.CableconnectorPropertyValueProviderProcedure;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

public class CableconnectorItem extends Item {
	public CableconnectorItem(Item.Properties properties) {
		super(properties.stacksTo(1));
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		CableconnectorRightclickedProcedure.execute(entity);
		return ar;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		CableconnectorRightclickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getClickedFace(), context.getPlayer());
		return InteractionResult.SUCCESS;
	}

	public record ModeProperty() implements RangeSelectItemModelProperty {
		public static final MapCodec<ModeProperty> MAP_CODEC = MapCodec.unit(new ModeProperty());

		@Override
		public float get(ItemStack itemStackToRender, @Nullable ClientLevel clientWorld, @Nullable LivingEntity entity, int seed) {
			return (float) CableconnectorPropertyValueProviderProcedure.execute(entity);
		}

		@Override
		public MapCodec<ModeProperty> type() {
			return MAP_CODEC;
		}
	}
}