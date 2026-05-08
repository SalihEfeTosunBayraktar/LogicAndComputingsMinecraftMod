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

import logicandcomputings.world.inventory.BufferguiMenu;

import logicandcomputings.procedures.*;

import logicandcomputings.network.BufferguiButtonMessage;

import logicandcomputings.init.LogicandcomputingsModScreens;

public class BufferguiScreen extends AbstractContainerScreen<BufferguiMenu> implements LogicandcomputingsModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_adder_minus;
	private ImageButton imagebutton_adder_plus;
	private ImageButton imagebutton_adder_minus1;
	private ImageButton imagebutton_adder_plus1;
	private ImageButton imagebutton_adder_minus_20;
	private ImageButton imagebutton_adder_plus_20;
	private ImageButton imagebutton_adder_plus2;
	private ImageButton imagebutton_adder_minus2;
	private ImageButton imagebutton_blank_switch;
	private ImageButton imagebutton_blank_switch1;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("logicandcomputings:textures/screens/buffergui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("logicandcomputings:textures/screens/addergui.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("logicandcomputings:textures/screens/switch_off.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("logicandcomputings:textures/screens/switch_on.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("logicandcomputings:textures/screens/lamp_off.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("logicandcomputings:textures/screens/lamp_on.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("logicandcomputings:textures/screens/switch_off.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("logicandcomputings:textures/screens/switch_on.png");
	private static final ResourceLocation IMAGE_7 = ResourceLocation.parse("logicandcomputings:textures/screens/lamp_off.png");
	private static final ResourceLocation IMAGE_8 = ResourceLocation.parse("logicandcomputings:textures/screens/lamp_on.png");
	private static final ResourceLocation IMAGE_9 = ResourceLocation.parse("logicandcomputings:textures/screens/adder_not_powered_gui.png");
	private static final ResourceLocation IMAGE_10 = ResourceLocation.parse("logicandcomputings:textures/screens/lamp_off.png");
	private static final ResourceLocation IMAGE_11 = ResourceLocation.parse("logicandcomputings:textures/screens/lamp_on.png");

	public BufferguiScreen(BufferguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 174;
		this.imageHeight = 144;
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
		if (PoweredtrueProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 113, this.topPos + 77, 0, 0, 64, 64, 64, 64);
		}
		if (ReturnaddermodefalseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + -13, this.topPos + 59, 0, 0, 10, 20, 10, 20);
		}
		if (ReturnaddermodetrueProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + -13, this.topPos + 59, 0, 0, 10, 20, 10, 20);
		}
		if (ReturnaddermodefalseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + -13, this.topPos + 50, 0, 0, 9, 9, 9, 9);
		}
		if (ReturnaddermodetrueProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + -13, this.topPos + 50, 0, 0, 9, 9, 9, 9);
		}
		if (ReturnrepeatermodefalseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + -13, this.topPos + 113, 0, 0, 10, 20, 10, 20);
		}
		if (ReturnrepeatermodetrueProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + -13, this.topPos + 113, 0, 0, 10, 20, 10, 20);
		}
		if (ReturnrepeatermodefalseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, this.leftPos + -13, this.topPos + 104, 0, 0, 9, 9, 9, 9);
		}
		if (ReturnrepeatermodetrueProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8, this.leftPos + -13, this.topPos + 104, 0, 0, 9, 9, 9, 9);
		}
		if (PoweredfalseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_9, this.leftPos + 113, this.topPos + 77, 0, 0, 64, 64, 64, 64);
		}
		if (ReturnadderandrepeatermodetrueProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_10, this.leftPos + -13, this.topPos + 86, 0, 0, 9, 9, 9, 9);
		}
		if (ReturnadderandrepeatermodefalseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_11, this.leftPos + -13, this.topPos + 86, 0, 0, 9, 9, 9, 9);
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
		guiGraphics.drawString(this.font, ReturndelaytimeProcedure.execute(world, x, y, z), 51, 35, -1, false);
		guiGraphics.drawString(this.font, ReturnoutputsignalvalueProcedure.execute(world, x, y, z), 53, 89, -1, false);
		guiGraphics.drawString(this.font, Returnsignalvalue2Procedure.execute(world, x, y, z), 43, 14, -1, false);
		guiGraphics.drawString(this.font, ReturndelayProcedure.execute(world, x, y, z), 131, 5, -1, false);
		guiGraphics.drawString(this.font, ReturnaddervalueProcedure.execute(world, x, y, z), 43, 62, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_delay"), 5, 32, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_time"), 5, 41, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_adder"), 5, 59, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_value"), 5, 68, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_output_redstone"), 5, 86, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_signal"), 5, 95, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_direct_flow"), 5, 113, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_repeater"), 5, 122, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_final_signal"), 5, 5, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_value1"), 5, 14, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.buffergui.label_delay1"), 95, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_adder_minus = new ImageButton(this.leftPos + 32, this.topPos + 68, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_hovered.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(0, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_minus);
		imagebutton_adder_plus = new ImageButton(this.leftPos + 32, this.topPos + 59, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_hovered.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(1, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_plus);
		imagebutton_adder_minus1 = new ImageButton(this.leftPos + 32, this.topPos + 41, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_hovered.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(2, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 2, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_minus1);
		imagebutton_adder_plus1 = new ImageButton(this.leftPos + 32, this.topPos + 32, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_hovered.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(3, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 3, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_plus1);
		imagebutton_adder_minus_20 = new ImageButton(this.leftPos + 41, this.topPos + 41, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_20.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_20_hovered.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(4, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 4, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_minus_20);
		imagebutton_adder_plus_20 = new ImageButton(this.leftPos + 41, this.topPos + 32, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_20.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_20_hovered.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(5, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 5, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_plus_20);
		imagebutton_adder_plus2 = new ImageButton(this.leftPos + 41, this.topPos + 86, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_plus_hovered.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(6, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 6, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_plus2);
		imagebutton_adder_minus2 = new ImageButton(this.leftPos + 41, this.topPos + 95, 9, 9,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus.png"), ResourceLocation.parse("logicandcomputings:textures/screens/adder_minus_hovered.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(7, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 7, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_adder_minus2);
		imagebutton_blank_switch = new ImageButton(this.leftPos + -13, this.topPos + 59, 10, 20,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/blank_switch.png"), ResourceLocation.parse("logicandcomputings:textures/screens/blank_switch.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(8, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 8, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_blank_switch);
		imagebutton_blank_switch1 = new ImageButton(this.leftPos + -13, this.topPos + 113, 10, 20,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/blank_switch.png"), ResourceLocation.parse("logicandcomputings:textures/screens/blank_switch.png")), e -> {
					int x = BufferguiScreen.this.x;
					int y = BufferguiScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new BufferguiButtonMessage(9, x, y, z));
						BufferguiButtonMessage.handleButtonAction(entity, 9, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_blank_switch1);
	}
}