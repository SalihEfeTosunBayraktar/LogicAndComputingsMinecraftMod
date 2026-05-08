package logicandcomputings;

import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.api.distmarker.Dist;

import logicandcomputings.init.LogicandcomputingsModBlocks;
import logicandcomputings.block.LampBlock;

@EventBusSubscriber(modid = "logicandcomputings", value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ColorRegistry {

    @SubscribeEvent
    public static void onBlockColorHandler(RegisterColorHandlersEvent.Block event) {
        if (LogicandcomputingsModBlocks.LAMP != null && LogicandcomputingsModBlocks.LAMP.get() != null) {
            event.register((state, world, pos, tintIndex) -> {
                return LampBlock.getColor(world, pos, tintIndex);
            }, LogicandcomputingsModBlocks.LAMP.get());
        }
    }
}