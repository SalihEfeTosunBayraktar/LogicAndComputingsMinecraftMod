/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package logicandcomputings.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import logicandcomputings.client.gui.WireGuiScreen;
import logicandcomputings.client.gui.Test0Screen;
import logicandcomputings.client.gui.PulsegeneratorguiScreen;
import logicandcomputings.client.gui.GuiNotGateScreen;
import logicandcomputings.client.gui.BufferguiScreen;

@EventBusSubscriber(Dist.CLIENT)
public class LogicandcomputingsModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(LogicandcomputingsModMenus.TEST_0.get(), Test0Screen::new);
		event.register(LogicandcomputingsModMenus.BUFFERGUI.get(), BufferguiScreen::new);
		event.register(LogicandcomputingsModMenus.PULSEGENERATORGUI.get(), PulsegeneratorguiScreen::new);
		event.register(LogicandcomputingsModMenus.GUI_NOT_GATE.get(), GuiNotGateScreen::new);
		event.register(LogicandcomputingsModMenus.WIRE_GUI.get(), WireGuiScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}