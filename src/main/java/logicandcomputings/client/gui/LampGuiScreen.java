package logicandcomputings.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import logicandcomputings.world.inventory.LampGuiMenu;

import logicandcomputings.network.LampGuiButtonMessage;

import logicandcomputings.init.LogicandcomputingsModScreens;

public class LampGuiScreen extends AbstractContainerScreen<LampGuiMenu> implements LogicandcomputingsModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_empty;
	private Button button_s;
	private Button button_e;
	private Button button_w;
	private Button button_u;
	private Button button_d;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("logicandcomputings:textures/screens/lamp_gui.png");

	public LampGuiScreen(LampGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		button_empty = Button.builder(Component.translatable("gui.logicandcomputings.lamp_gui.button_empty"), e -> {
			int x = LampGuiScreen.this.x;
			int y = LampGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new LampGuiButtonMessage(0, x, y, z));
				LampGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 69, this.topPos + 16, 27, 20).build();
		this.addRenderableWidget(button_empty);
		button_s = Button.builder(Component.translatable("gui.logicandcomputings.lamp_gui.button_s"), e -> {
			int x = LampGuiScreen.this.x;
			int y = LampGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new LampGuiButtonMessage(1, x, y, z));
				LampGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 69, this.topPos + 133, 30, 20).build();
		this.addRenderableWidget(button_s);
		button_e = Button.builder(Component.translatable("gui.logicandcomputings.lamp_gui.button_e"), e -> {
			int x = LampGuiScreen.this.x;
			int y = LampGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new LampGuiButtonMessage(2, x, y, z));
				LampGuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 132, this.topPos + 70, 30, 20).build();
		this.addRenderableWidget(button_e);
		button_w = Button.builder(Component.translatable("gui.logicandcomputings.lamp_gui.button_w"), e -> {
			int x = LampGuiScreen.this.x;
			int y = LampGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new LampGuiButtonMessage(3, x, y, z));
				LampGuiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 70, 30, 20).build();
		this.addRenderableWidget(button_w);
		button_u = Button.builder(Component.translatable("gui.logicandcomputings.lamp_gui.button_u"), e -> {
			int x = LampGuiScreen.this.x;
			int y = LampGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new LampGuiButtonMessage(4, x, y, z));
				LampGuiButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 69, this.topPos + 52, 30, 20).build();
		this.addRenderableWidget(button_u);
		button_d = Button.builder(Component.translatable("gui.logicandcomputings.lamp_gui.button_d"), e -> {
			int x = LampGuiScreen.this.x;
			int y = LampGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new LampGuiButtonMessage(5, x, y, z));
				LampGuiButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 69, this.topPos + 79, 30, 20).build();
		this.addRenderableWidget(button_d);
	}
}