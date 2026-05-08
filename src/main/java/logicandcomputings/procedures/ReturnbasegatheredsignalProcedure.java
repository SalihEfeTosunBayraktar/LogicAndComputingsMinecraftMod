package logicandcomputings.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;

public class ReturnbasegatheredsignalProcedure {
	public static double execute(BlockState blockstate) {
		return getPropertyByName(blockstate, "gathered_signal") instanceof IntegerProperty _getip1 ? blockstate.getValue(_getip1) : -1;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}