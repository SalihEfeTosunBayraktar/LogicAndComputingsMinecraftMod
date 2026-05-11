/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package logicandcomputings.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import logicandcomputings.block.entity.*;

import logicandcomputings.LogicandcomputingsMod;

@EventBusSubscriber
public class LogicandcomputingsModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, LogicandcomputingsMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BufferBlockEntity>> BUFFER_GATE = register("buffer_gate", LogicandcomputingsModBlocks.BUFFER_GATE, BufferBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WireBlockEntity>> WIRE = register("wire", LogicandcomputingsModBlocks.WIRE, WireBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NotGateBlockEntity>> NOT_GATE = register("not_gate", LogicandcomputingsModBlocks.NOT_GATE, NotGateBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndGateBlockEntity>> AND_GATE = register("and_gate", LogicandcomputingsModBlocks.AND_GATE, AndGateBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OrGateBlockEntity>> OR_GATE = register("or_gate", LogicandcomputingsModBlocks.OR_GATE, OrGateBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NandGateBlockEntity>> NAND_GATE = register("nand_gate", LogicandcomputingsModBlocks.NAND_GATE, NandGateBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NOrGateBlockEntity>> N_OR_GATE = register("n_or_gate", LogicandcomputingsModBlocks.N_OR_GATE, NOrGateBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<XOrGateBlockEntity>> X_OR_GATE = register("x_or_gate", LogicandcomputingsModBlocks.X_OR_GATE, XOrGateBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<XNOrGateBlockEntity>> XN_OR_GATE = register("xn_or_gate", LogicandcomputingsModBlocks.XN_OR_GATE, XNOrGateBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PulsegeneratorBlockEntity>> PULSEGENERATOR = register("pulsegenerator", LogicandcomputingsModBlocks.PULSEGENERATOR, PulsegeneratorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MuxSiwtchBlockEntity>> MUX_SWITCH2X1 = register("mux_switch2x1", LogicandcomputingsModBlocks.MUX_SWITCH2X1, MuxSiwtchBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LampBlockEntity>> LAMP = register("lamp", LogicandcomputingsModBlocks.LAMP, LampBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DecoderBlockEntity>> DECODER = register("decoder", LogicandcomputingsModBlocks.DECODER, DecoderBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> new BlockEntityType(supplier, block.get()));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BUFFER_GATE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, WIRE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NOT_GATE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, AND_GATE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, OR_GATE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NAND_GATE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, N_OR_GATE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, X_OR_GATE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, XN_OR_GATE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PULSEGENERATOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, MUX_SWITCH2X1.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, LAMP.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DECODER.get(), SidedInvWrapper::new);
	}
}