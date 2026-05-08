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
public record Test0ButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<Test0ButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(LogicandcomputingsMod.MODID, "test_0_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, Test0ButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, Test0ButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new Test0ButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<Test0ButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final Test0ButtonMessage message, final IPayloadContext context) {
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

			Setconnectionstatus0Procedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			Setconnectionstatus1Procedure.execute(world, x, y, z);
		}
		if (buttonID == 2) {

			Setconnectionstatus2Procedure.execute(world, x, y, z);
		}
		if (buttonID == 3) {

			Setconnectionstatus3Procedure.execute(world, x, y, z);
		}
		if (buttonID == 4) {

			Setconnectionstatus4Procedure.execute(world, x, y, z);
		}
		if (buttonID == 5) {

			Setconnectionstatus5Procedure.execute(world, x, y, z);
		}
		if (buttonID == 6) {

			EmitsredstoneProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		LogicandcomputingsMod.addNetworkMessage(Test0ButtonMessage.TYPE, Test0ButtonMessage.STREAM_CODEC, Test0ButtonMessage::handleData);
	}
}