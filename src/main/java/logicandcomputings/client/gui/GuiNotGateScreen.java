package logicandcomputings.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import logicandcomputings.world.inventory.GuiNotGateMenu;

import logicandcomputings.procedures.*;

import logicandcomputings.network.GuiNotGateButtonMessage;

import logicandcomputings.init.LogicandcomputingsModScreens;

public class GuiNotGateScreen extends AbstractContainerScreen<GuiNotGateMenu> implements LogicandcomputingsModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_adder_minus1;
	private ImageButton imagebutton_adder_plus1;
	private ImageButton imagebutton_adder_minus_20;
	private ImageButton imagebutton_adder_plus_20;
	private ImageButton imagebutton_adder_plus2;
	private ImageButton imagebutton_adder_minus2;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("logicandcomputings:textures/screens/gui_not_gate.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("logicandcomputings:textures/screens/addergui.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("logicandcomputings:textures/screens/adder_not_powered_gui.png");

	public GuiNotGateScreen(GuiNotGateMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 174;
		this.imageHeight = 97;
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
		if (PoweredfalseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 113, this.topPos + 35, 0, 0, 64, 64, 64, 64);
		}
		if (PoweredtrueProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 113, this.topPos + 35, 0, 0, 64, 64, 64, 64);
		}
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
		guiGraphics.drawString(this.font, ReturndelaytimeProcedure.execute(world, x, y, z), 53, 35, -1, false);
		guiGraphics.drawString(this.font, ReturnoutputsignalvalueProcedure.execute(world, x, y, z), 53, 62, -1, false);
		guiGraphics.drawString(this.font, Returnsignalvalue2Procedure.execute(world, x, y, z), 43, 17, -1, false);
		guiGraphics.drawString(this.font, ReturndelayProcedure.execute(world, x, y, z), 133, 8, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.gui_not_gate.label_delay"), 5, 35, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.gui_not_gate.label_time"), 5, 44, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.gui_not_gate.label_output_redstone"), 5, 62, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.gui_not_gate.label_signal"), 5, 71, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.gui_not_gate.label_final_signal"), 5, 8, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.gui_not_gate.label_value1"), 5, 17, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.gui_not_gate.label_delay1"), 95, 8, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_adder_minus1 = new ImageButton(this.leftPos + 32, this.topPos + 44, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_hovered.png")), e -> {
					int x = GuiNotGateScreen.this.x;
					int y = GuiNotGateScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new GuiNotGateButtonMessage(0, x, y, z));
						GuiNotGateButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_minus1);
		imagebutton_adder_plus1 = new ImageButton(this.leftPos + 32, this.topPos + 35, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_hovered.png")), e -> {
					int x = GuiNotGateScreen.this.x;
					int y = GuiNotGateScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new GuiNotGateButtonMessage(1, x, y, z));
						GuiNotGateButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_plus1);
		imagebutton_adder_minus_20 = new ImageButton(this.leftPos + 41, this.topPos + 44, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_20.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_20_hovered.png")), e -> {
					int x = GuiNotGateScreen.this.x;
					int y = GuiNotGateScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new GuiNotGateButtonMessage(2, x, y, z));
						GuiNotGateButtonMessage.handleButtonAction(entity, 2, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_minus_20);
		imagebutton_adder_plus_20 = new ImageButton(this.leftPos + 41, this.topPos + 35, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_20.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_20_hovered.png")), e -> {
					int x = GuiNotGateScreen.this.x;
					int y = GuiNotGateScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new GuiNotGateButtonMessage(3, x, y, z));
						GuiNotGateButtonMessage.handleButtonAction(entity, 3, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_plus_20);
		imagebutton_adder_plus2 = new ImageButton(this.leftPos + 41, this.topPos + 62, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_hovered.png")), e -> {
					int x = GuiNotGateScreen.this.x;
					int y = GuiNotGateScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new GuiNotGateButtonMessage(4, x, y, z));
						GuiNotGateButtonMessage.handleButtonAction(entity, 4, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_plus2);
		imagebutton_adder_minus2 = new ImageButton(this.leftPos + 41, this.topPos + 71, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_hovered.png")), e -> {
					int x = GuiNotGateScreen.this.x;
					int y = GuiNotGateScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new GuiNotGateButtonMessage(5, x, y, z));
						GuiNotGateButtonMessage.handleButtonAction(entity, 5, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_minus2);
	}
}