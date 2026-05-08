package logicandcomputings.procedures;

import net.minecraft.world.level.LevelAccessor;

public class PulsegeneratorguiWhileThisGUIIsOpenTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ReturntimerinfoProcedure.execute(world, x, y, z);
		ReturnMaxDelayinfoProcedure.execute(world, x, y, z);
		ReturndurationProcedure.execute(world, x, y, z);
		ReturnmaxdelayhalfinfoProcedure.execute(world, x, y, z);
		ReturndurationinfoProcedure.execute(world, x, y, z);
	}
}