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

import logicandcomputings.world.inventory.PulsegeneratorguiMenu;

import logicandcomputings.procedures.*;

import logicandcomputings.network.PulsegeneratorguiButtonMessage;

import logicandcomputings.init.LogicandcomputingsModScreens;

public class PulsegeneratorguiScreen extends AbstractContainerScreen<PulsegeneratorguiMenu> implements LogicandcomputingsModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_5;
	private Button button_51;
	private Button button_52;
	private Button button_53;
	private Button button_empty;
	private Button button_1;
	private Button button_empty1;
	private Button button_r;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("logicandcomputings:textures/screens/pulsegeneratorgui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("logicandcomputings:textures/screens/clock.png");

	public PulsegeneratorguiScreen(PulsegeneratorguiMenu container, Inventory inventory, Component text) {
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 51, this.topPos + 61, 0, 0, 64, 64, 64, 64);
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
		guiGraphics.drawString(this.font, ReturnMaxDelayinfoProcedure.execute(world, x, y, z), 24, 7, -12829636, false);
		guiGraphics.drawString(this.font, ReturntimerinfoProcedure.execute(world, x, y, z), 24, 34, -12829636, false);
		guiGraphics.drawString(this.font, ReturndurationProcedure.execute(world, x, y, z), 24, 43, -12829636, false);
		guiGraphics.drawString(this.font, ReturnoutputsignalvalueProcedure.execute(world, x, y, z), 24, 25, -12829636, false);
		guiGraphics.drawString(this.font, ReturnpausedisplayProcedure.execute(world, x, y, z), 6, 151, -12829636, false);
		guiGraphics.drawString(this.font, ReturnmaxdelayhalfinfoProcedure.execute(world, x, y, z), 42, 97, -12829636, false);
		guiGraphics.drawString(this.font, ReturnmaxdelayhalfinfoProcedure.execute(world, x, y, z), 105, 97, -12829636, false);
		guiGraphics.drawString(this.font, ReturndurationinfoProcedure.execute(world, x, y, z), 69, 79, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_5 = Button.builder(Component.translatable("gui.logicandcomputings.pulsegeneratorgui.button_5"), e -> {
			int x = PulsegeneratorguiScreen.this.x;
			int y = PulsegeneratorguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PulsegeneratorguiButtonMessage(0, x, y, z));
				PulsegeneratorguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 7, 9, 20).build();
		this.addRenderableWidget(button_5);
		button_51 = Button.builder(Component.translatable("gui.logicandcomputings.pulsegeneratorgui.button_51"), e -> {
			int x = PulsegeneratorguiScreen.this.x;
			int y = PulsegeneratorguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PulsegeneratorguiButtonMessage(1, x, y, z));
				PulsegeneratorguiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 7, 9, 20).build();
		this.addRenderableWidget(button_51);
		button_52 = Button.builder(Component.translatable("gui.logicandcomputings.pulsegeneratorgui.button_52"), e -> {
			int x = PulsegeneratorguiScreen.this.x;
			int y = PulsegeneratorguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PulsegeneratorguiButtonMessage(2, x, y, z));
				PulsegeneratorguiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 43, 9, 20).build();
		this.addRenderableWidget(button_52);
		button_53 = Button.builder(Component.translatable("gui.logicandcomputings.pulsegeneratorgui.button_53"), e -> {
			int x = PulsegeneratorguiScreen.this.x;
			int y = PulsegeneratorguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PulsegeneratorguiButtonMessage(3, x, y, z));
				PulsegeneratorguiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 43, 9, 20).build();
		this.addRenderableWidget(button_53);
		button_empty = Button.builder(Component.translatable("gui.logicandcomputings.pulsegeneratorgui.button_empty"), e -> {
			int x = PulsegeneratorguiScreen.this.x;
			int y = PulsegeneratorguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PulsegeneratorguiButtonMessage(4, x, y, z));
				PulsegeneratorguiButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 25, 9, 20).build();
		this.addRenderableWidget(button_empty);
		button_1 = Button.builder(Component.translatable("gui.logicandcomputings.pulsegeneratorgui.button_1"), e -> {
			int x = PulsegeneratorguiScreen.this.x;
			int y = PulsegeneratorguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PulsegeneratorguiButtonMessage(5, x, y, z));
				PulsegeneratorguiButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 25, 9, 20).build();
		this.addRenderableWidget(button_1);
		button_empty1 = Button.builder(Component.translatable("gui.logicandcomputings.pulsegeneratorgui.button_empty1"), e -> {
			int x = PulsegeneratorguiScreen.this.x;
			int y = PulsegeneratorguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PulsegeneratorguiButtonMessage(6, x, y, z));
				PulsegeneratorguiButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}).bounds(this.leftPos + 105, this.topPos + 142, 9, 20).build();
		this.addRenderableWidget(button_empty1);
		button_r = Button.builder(Component.translatable("gui.logicandcomputings.pulsegeneratorgui.button_r"), e -> {
			int x = PulsegeneratorguiScreen.this.x;
			int y = PulsegeneratorguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PulsegeneratorguiButtonMessage(7, x, y, z));
				PulsegeneratorguiButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}).bounds(this.leftPos + 141, this.topPos + 142, 30, 20).build();
		this.addRenderableWidget(button_r);
	}
}