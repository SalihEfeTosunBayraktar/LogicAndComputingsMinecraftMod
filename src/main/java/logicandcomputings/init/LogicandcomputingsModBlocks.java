/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package logicandcomputings.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import logicandcomputings.block.*;

import logicandcomputings.LogicandcomputingsMod;

import java.util.function.Function;

public class LogicandcomputingsModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(LogicandcomputingsMod.MODID);
	public static final DeferredBlock<Block> BUFFER_GATE;
	public static final DeferredBlock<Block> WIRE;
	public static final DeferredBlock<Block> NOT_GATE;
	public static final DeferredBlock<Block> AND_GATE;
	public static final DeferredBlock<Block> OR_GATE;
	public static final DeferredBlock<Block> NAND_GATE;
	public static final DeferredBlock<Block> N_OR_GATE;
	public static final DeferredBlock<Block> X_OR_GATE;
	public static final DeferredBlock<Block> XN_OR_GATE;
	public static final DeferredBlock<Block> PULSEGENERATOR;
	public static final DeferredBlock<Block> MUX_SWITCH2X1;
	static {
		BUFFER_GATE = register("buffer_gate", BufferBlock::new);
		WIRE = register("wire", WireBlock::new);
		NOT_GATE = register("not_gate", NotGateBlock::new);
		AND_GATE = register("and_gate", AndGateBlock::new);
		OR_GATE = register("or_gate", OrGateBlock::new);
		NAND_GATE = register("nand_gate", NandGateBlock::new);
		N_OR_GATE = register("n_or_gate", NOrGateBlock::new);
		X_OR_GATE = register("x_or_gate", XOrGateBlock::new);
		XN_OR_GATE = register("xn_or_gate", XNOrGateBlock::new);
		PULSEGENERATOR = register("pulsegenerator", PulsegeneratorBlock::new);
		MUX_SWITCH2X1 = register("mux_switch2x1", MuxSiwtchBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}