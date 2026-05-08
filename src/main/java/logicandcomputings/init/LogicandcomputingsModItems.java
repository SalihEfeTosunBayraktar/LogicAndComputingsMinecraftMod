/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package logicandcomputings.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;

import logicandcomputings.item.CableconnectorItem;

import logicandcomputings.LogicandcomputingsMod;

import java.util.function.Function;

public class LogicandcomputingsModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(LogicandcomputingsMod.MODID);
	public static final DeferredItem<Item> BUFFER_GATE;
	public static final DeferredItem<Item> WIRE;
	public static final DeferredItem<Item> NOT_GATE;
	public static final DeferredItem<Item> CABLECONNECTOR;
	public static final DeferredItem<Item> AND_GATE;
	public static final DeferredItem<Item> OR_GATE;
	public static final DeferredItem<Item> NAND_GATE;
	public static final DeferredItem<Item> N_OR_GATE;
	public static final DeferredItem<Item> X_OR_GATE;
	public static final DeferredItem<Item> XN_OR_GATE;
	public static final DeferredItem<Item> PULSEGENERATOR;
	public static final DeferredItem<Item> MUX_SWITCH2X1;
	static {
		BUFFER_GATE = block(LogicandcomputingsModBlocks.BUFFER_GATE);
		WIRE = block(LogicandcomputingsModBlocks.WIRE);
		NOT_GATE = block(LogicandcomputingsModBlocks.NOT_GATE);
		CABLECONNECTOR = register("cableconnector", CableconnectorItem::new);
		AND_GATE = block(LogicandcomputingsModBlocks.AND_GATE);
		OR_GATE = block(LogicandcomputingsModBlocks.OR_GATE);
		NAND_GATE = block(LogicandcomputingsModBlocks.NAND_GATE);
		N_OR_GATE = block(LogicandcomputingsModBlocks.N_OR_GATE);
		X_OR_GATE = block(LogicandcomputingsModBlocks.X_OR_GATE);
		XN_OR_GATE = block(LogicandcomputingsModBlocks.XN_OR_GATE);
		PULSEGENERATOR = block(LogicandcomputingsModBlocks.PULSEGENERATOR);
		MUX_SWITCH2X1 = block(LogicandcomputingsModBlocks.MUX_SWITCH2X1);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class ItemsClientSideHandler {
		@SubscribeEvent
		public static void registerItemModelProperties(RegisterRangeSelectItemModelPropertyEvent event) {
			event.register(ResourceLocation.parse("logicandcomputings:cableconnector/mode"), CableconnectorItem.ModeProperty.MAP_CODEC);
		}
	}
}