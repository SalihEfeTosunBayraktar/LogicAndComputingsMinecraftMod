package logicandcomputings.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.SectionPos;

import logicandcomputings.procedures.*;

import logicandcomputings.LogicandcomputingsMod;

@EventBusSubscriber
public record LampGuiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<LampGuiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(LogicandcomputingsMod.MODID, "lamp_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, LampGuiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, LampGuiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new LampGuiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<LampGuiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final LampGuiButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
			return;
		if (buttonID == 0) {

			SetreadingfaceNorthProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			SetreadingfacesouthProcedure.execute(world, x, y, z);
		}
		if (buttonID == 2) {

			SetreadingfaceEastProcedure.execute(world, x, y, z);
		}
		if (buttonID == 3) {

			SetreadingfaceWestProcedure.execute(world, x, y, z);
		}
		if (buttonID == 4) {

			SetreadingfaceUpProcedure.execute(world, x, y, z);
		}
		if (buttonID == 5) {

			SetreadingfacedownProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		LogicandcomputingsMod.addNetworkMessage(LampGuiButtonMessage.TYPE, LampGuiButtonMessage.STREAM_CODEC, LampGuiButtonMessage::handleData);
	}
}