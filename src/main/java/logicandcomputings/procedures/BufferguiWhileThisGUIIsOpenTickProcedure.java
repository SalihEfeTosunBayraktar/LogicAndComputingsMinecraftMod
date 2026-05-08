package logicandcomputings.procedures;

import net.minecraft.world.level.LevelAccessor;

public class BufferguiWhileThisGUIIsOpenTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ReturndelayProcedure.execute(world, x, y, z);
		ReturndelaytimeProcedure.execute(world, x, y, z);
		ReturnsignalvalueProcedure.execute(world, x, y, z);
		ReturnadderinfoProcedure.execute(world, x, y, z);
		ReturnrepeatervalueProcedure.execute(world, x, y, z);
		ReturnaddervalueProcedure.execute(world, x, y, z);
		ReturnoutputsignalvalueProcedure.execute(world, x, y, z);
	}
}