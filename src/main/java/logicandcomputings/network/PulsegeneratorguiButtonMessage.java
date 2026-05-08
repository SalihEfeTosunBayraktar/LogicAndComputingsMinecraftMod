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
public record PulsegeneratorguiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<PulsegeneratorguiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(LogicandcomputingsMod.MODID, "pulsegeneratorgui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, PulsegeneratorguiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, PulsegeneratorguiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new PulsegeneratorguiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<PulsegeneratorguiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final PulsegeneratorguiButtonMessage message, final IPayloadContext context) {
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

			MaxdelayminusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			MaxdelayplusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 2) {

			DurationplusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 3) {

			DurationminusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 4) {

			OutputsignalvalueplusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 5) {

			OutputsignalvalueminusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 6) {

			PausetoggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 7) {

			ResetpulsegeneratorProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		LogicandcomputingsMod.addNetworkMessage(PulsegeneratorguiButtonMessage.TYPE, PulsegeneratorguiButtonMessage.STREAM_CODEC, PulsegeneratorguiButtonMessage::handleData);
	}
}