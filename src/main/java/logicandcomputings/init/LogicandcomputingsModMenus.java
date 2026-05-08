/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package logicandcomputings.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import logicandcomputings.world.inventory.WireGuiMenu;
import logicandcomputings.world.inventory.Test0Menu;
import logicandcomputings.world.inventory.PulsegeneratorguiMenu;
import logicandcomputings.world.inventory.GuiNotGateMenu;
import logicandcomputings.world.inventory.BufferguiMenu;

import logicandcomputings.network.MenuStateUpdateMessage;

import logicandcomputings.LogicandcomputingsMod;

import java.util.Map;

public class LogicandcomputingsModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, LogicandcomputingsMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<Test0Menu>> TEST_0 = REGISTRY.register("test_0", () -> IMenuTypeExtension.create(Test0Menu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BufferguiMenu>> BUFFERGUI = REGISTRY.register("buffergui", () -> IMenuTypeExtension.create(BufferguiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PulsegeneratorguiMenu>> PULSEGENERATORGUI = REGISTRY.register("pulsegeneratorgui", () -> IMenuTypeExtension.create(PulsegeneratorguiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GuiNotGateMenu>> GUI_NOT_GATE = REGISTRY.register("gui_not_gate", () -> IMenuTypeExtension.create(GuiNotGateMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<WireGuiMenu>> WIRE_GUI = REGISTRY.register("wire_gui", () -> IMenuTypeExtension.create(WireGuiMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof LogicandcomputingsModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				ClientPacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}