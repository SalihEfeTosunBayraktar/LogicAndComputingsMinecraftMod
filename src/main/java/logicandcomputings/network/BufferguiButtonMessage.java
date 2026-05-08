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
public record BufferguiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<BufferguiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(LogicandcomputingsMod.MODID, "buffergui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, BufferguiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, BufferguiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new BufferguiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<BufferguiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final BufferguiButtonMessage message, final IPayloadContext context) {
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

			AddervalueminusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			AddervalueplusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 2) {

			DelayminusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 3) {

			DelayplusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 4) {

			DelayminusonesecondProcedure.execute(world, x, y, z);
		}
		if (buttonID == 5) {

			DelayplusonesecondProcedure.execute(world, x, y, z);
		}
		if (buttonID == 6) {

			OutputsignalvalueplusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 7) {

			OutputsignalvalueminusoneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 8) {

			SetaddermodeProcedure.execute(world, x, y, z);
		}
		if (buttonID == 9) {

			SetrepeatermodeProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		LogicandcomputingsMod.addNetworkMessage(BufferguiButtonMessage.TYPE, BufferguiButtonMessage.STREAM_CODEC, BufferguiButtonMessage::handleData);
	}
}