/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package logicandcomputings.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import logicandcomputings.LogicandcomputingsMod;

@EventBusSubscriber
public class LogicandcomputingsModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LogicandcomputingsMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			tabData.accept(LogicandcomputingsModBlocks.BUFFER_GATE.get().asItem());
			tabData.accept(LogicandcomputingsModBlocks.WIRE.get().asItem());
			tabData.accept(LogicandcomputingsModBlocks.NOT_GATE.get().asItem());
			tabData.accept(LogicandcomputingsModItems.CABLECONNECTOR.get());
			tabData.accept(LogicandcomputingsModBlocks.AND_GATE.get().asItem());
			tabData.accept(LogicandcomputingsModBlocks.OR_GATE.get().asItem());
			tabData.accept(LogicandcomputingsModBlocks.NAND_GATE.get().asItem());
			tabData.accept(LogicandcomputingsModBlocks.N_OR_GATE.get().asItem());
			tabData.accept(LogicandcomputingsModBlocks.X_OR_GATE.get().asItem());
			tabData.accept(LogicandcomputingsModBlocks.XN_OR_GATE.get().asItem());
			tabData.accept(LogicandcomputingsModBlocks.MUX_SWITCH2X1.get().asItem());
		}
	}
}