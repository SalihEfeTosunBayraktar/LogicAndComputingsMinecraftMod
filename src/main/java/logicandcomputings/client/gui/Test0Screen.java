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

import logicandcomputings.world.inventory.Test0Menu;

import logicandcomputings.procedures.*;

import logicandcomputings.network.Test0ButtonMessage;

import logicandcomputings.init.LogicandcomputingsModScreens;

public class Test0Screen extends AbstractContainerScreen<Test0Menu> implements LogicandcomputingsModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_selectfacenorth0;
	private ImageButton imagebutton_selectfacesouth0;
	private ImageButton imagebutton_selectfacewest0;
	private ImageButton imagebutton_selectfaceeast0;
	private ImageButton imagebutton_selectfaceup0;
	private ImageButton imagebutton_selectfacedown0;
	private ImageButton imagebutton_blankredstone;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("logicandcomputings:textures/screens/test_0.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("logicandcomputings:textures/screens/faceconnectedtrue.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("logicandcomputings:textures/screens/faceconnectedtrue.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("logicandcomputings:textures/screens/faceconnectedtrue.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("logicandcomputings:textures/screens/faceconnectedtrue.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("logicandcomputings:textures/screens/faceconnectedtrue.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("logicandcomputings:textures/screens/faceconnectedtrue.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("logicandcomputings:textures/screens/bg.png");
	private static final ResourceLocation IMAGE_7 = ResourceLocation.parse("logicandcomputings:textures/screens/redstone_not_emiting.png");
	private static final ResourceLocation IMAGE_8 = ResourceLocation.parse("logicandcomputings:textures/screens/redstone_dust_je2_be2.png");

	public Test0Screen(Test0Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 95;
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
		if (Returnconnectionstatus0Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 32, this.topPos + 51, 0, 0, 14, 14, 14, 14);
		}
		if (Returnconnectionstatus1Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 50, this.topPos + 51, 0, 0, 14, 14, 14, 14);
		}
		if (Returnconnectionstatus2Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 68, this.topPos + 51, 0, 0, 14, 14, 14, 14);
		}
		if (Returnconnectionstatus3Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 86, this.topPos + 51, 0, 0, 14, 14, 14, 14);
		}
		if (Returnconnectionstatus4Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 104, this.topPos + 51, 0, 0, 14, 14, 14, 14);
		}
		if (Returnconnectionstatus5Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 122, this.topPos + 51, 0, 0, 14, 14, 14, 14);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + 15, this.topPos + 7, 0, 0, 142, 40, 142, 40);
		if (ReturnredstoneemitsinfotrueProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, this.leftPos + 141, this.topPos + 61, 0, 0, 32, 32, 32, 32);
		}
		if (ReturnredstoneemitsinfofalseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8, this.leftPos + 141, this.topPos + 61, 0, 0, 32, 32, 32, 32);
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
		guiGraphics.drawString(this.font, ReturnsignalvalueProcedure.execute(world, x, y, z), 18, 11, -205, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.test_0.label_redstone_power"), 42, 70, -3407872, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.logicandcomputings.test_0.label_reading"), 105, 79, -3407872, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_selectfacenorth0 = new ImageButton(this.leftPos + 33, this.topPos + 52, 12, 12,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/selectfacenorth0.png"), ResourceLocation.parse("logicandcomputings:textures/screens/selectfacenorthh.png")), e -> {
					int x = Test0Screen.this.x;
					int y = Test0Screen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new Test0ButtonMessage(0, x, y, z));
						Test0ButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_selectfacenorth0);
		imagebutton_selectfacesouth0 = new ImageButton(this.leftPos + 51, this.topPos + 52, 12, 12,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/selectfacesouth0.png"), ResourceLocation.parse("logicandcomputings:textures/screens/selectfacesouthh.png")), e -> {
					int x = Test0Screen.this.x;
					int y = Test0Screen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new Test0ButtonMessage(1, x, y, z));
						Test0ButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_selectfacesouth0);
		imagebutton_selectfacewest0 = new ImageButton(this.leftPos + 69, this.topPos + 52, 12, 12,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/selectfacewest0.png"), ResourceLocation.parse("logicandcomputings:textures/screens/selectfacewesth.png")), e -> {
					int x = Test0Screen.this.x;
					int y = Test0Screen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new Test0ButtonMessage(2, x, y, z));
						Test0ButtonMessage.handleButtonAction(entity, 2, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_selectfacewest0);
		imagebutton_selectfaceeast0 = new ImageButton(this.leftPos + 87, this.topPos + 52, 12, 12,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/selectfaceeast0.png"), ResourceLocation.parse("logicandcomputings:textures/screens/selectfaceeasth.png")), e -> {
					int x = Test0Screen.this.x;
					int y = Test0Screen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new Test0ButtonMessage(3, x, y, z));
						Test0ButtonMessage.handleButtonAction(entity, 3, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_selectfaceeast0);
		imagebutton_selectfaceup0 = new ImageButton(this.leftPos + 105, this.topPos + 52, 12, 12,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/selectfaceup0.png"), ResourceLocation.parse("logicandcomputings:textures/screens/selectfaceuph.png")), e -> {
					int x = Test0Screen.this.x;
					int y = Test0Screen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new Test0ButtonMessage(4, x, y, z));
						Test0ButtonMessage.handleButtonAction(entity, 4, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_selectfaceup0);
		imagebutton_selectfacedown0 = new ImageButton(this.leftPos + 123, this.topPos + 52, 12, 12,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/selectfacedown0.png"), ResourceLocation.parse("logicandcomputings:textures/screens/selectfacedownh.png")), e -> {
					int x = Test0Screen.this.x;
					int y = Test0Screen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new Test0ButtonMessage(5, x, y, z));
						Test0ButtonMessage.handleButtonAction(entity, 5, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_selectfacedown0);
		imagebutton_blankredstone = new ImageButton(this.leftPos + 141, this.topPos + 61, 32, 32,
				new WidgetSprites(ResourceLocation.parse("logicandcomputings:textures/screens/blankredstone.png"), ResourceLocation.parse("logicandcomputings:textures/screens/blankredstone.png")), e -> {
					int x = Test0Screen.this.x;
					int y = Test0Screen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new Test0ButtonMessage(6, x, y, z));
						Test0ButtonMessage.handleButtonAction(entity, 6, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_blankredstone);
	}
}