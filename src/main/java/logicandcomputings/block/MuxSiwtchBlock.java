package logicandcomputings.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Containers;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import logicandcomputings.procedures.MuxSwitchSignalproducersProcedure;

import logicandcomputings.block.entity.MuxSiwtchBlockEntity;

import java.util.function.Function;

public class MuxSiwtchBlock extends Block implements EntityBlock {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public MuxSiwtchBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(POWERED) == true) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(1, 0, 1, 15, 15, 15), box(0, 0, 15, 16, 2, 16), box(0, 14, 15, 16, 16, 16), box(0, 2, 15, 2, 14, 16), box(14, 2, 15, 16, 14, 16), box(0, 0, 0, 16, 2, 1), box(5, 5, 0, 11, 7, 1), box(5, 9, 0, 11, 11, 1),
							box(9, 7, 0, 11, 9, 1), box(5, 7, 0, 7, 9, 1), box(5, 5, 15, 11, 7, 16), box(5, 7, 15, 7, 9, 16), box(9, 7, 15, 11, 9, 16), box(5, 9, 15, 11, 11, 16), box(0, 2, 0, 2, 14, 1), box(0, 14, 0, 16, 16, 1),
							box(0, 14, 1, 1, 16, 15), box(0, 9, 5, 1, 11, 11), box(0, 5, 5, 1, 7, 11), box(0, 7, 5, 1, 9, 7), box(0, 7, 9, 1, 9, 11), box(15, 5, 5, 16, 7, 11), box(15, 9, 5, 16, 11, 11), box(15, 7, 5, 16, 9, 7),
							box(15, 7, 9, 16, 9, 11), box(0, 0, 1, 1, 2, 15), box(15, 14, 1, 16, 16, 15), box(15, 0, 1, 16, 2, 15), box(14, 2, 0, 16, 14, 1));
					case NORTH -> Shapes.or(box(1, 0, 1, 15, 15, 15), box(0, 0, 0, 16, 2, 1), box(0, 14, 0, 16, 16, 1), box(14, 2, 0, 16, 14, 1), box(0, 2, 0, 2, 14, 1), box(0, 0, 15, 16, 2, 16), box(5, 5, 15, 11, 7, 16), box(5, 9, 15, 11, 11, 16),
							box(5, 7, 15, 7, 9, 16), box(9, 7, 15, 11, 9, 16), box(5, 5, 0, 11, 7, 1), box(9, 7, 0, 11, 9, 1), box(5, 7, 0, 7, 9, 1), box(5, 9, 0, 11, 11, 1), box(14, 2, 15, 16, 14, 16), box(0, 14, 15, 16, 16, 16),
							box(15, 14, 1, 16, 16, 15), box(15, 9, 5, 16, 11, 11), box(15, 5, 5, 16, 7, 11), box(15, 7, 9, 16, 9, 11), box(15, 7, 5, 16, 9, 7), box(0, 5, 5, 1, 7, 11), box(0, 9, 5, 1, 11, 11), box(0, 7, 9, 1, 9, 11),
							box(0, 7, 5, 1, 9, 7), box(15, 0, 1, 16, 2, 15), box(0, 14, 1, 1, 16, 15), box(0, 0, 1, 1, 2, 15), box(0, 2, 15, 2, 14, 16));
					case EAST -> Shapes.or(box(1, 0, 1, 15, 15, 15), box(15, 0, 0, 16, 2, 16), box(15, 14, 0, 16, 16, 16), box(15, 2, 14, 16, 14, 16), box(15, 2, 0, 16, 14, 2), box(0, 0, 0, 1, 2, 16), box(0, 5, 5, 1, 7, 11), box(0, 9, 5, 1, 11, 11),
							box(0, 7, 5, 1, 9, 7), box(0, 7, 9, 1, 9, 11), box(15, 5, 5, 16, 7, 11), box(15, 7, 9, 16, 9, 11), box(15, 7, 5, 16, 9, 7), box(15, 9, 5, 16, 11, 11), box(0, 2, 14, 1, 14, 16), box(0, 14, 0, 1, 16, 16),
							box(1, 14, 15, 15, 16, 16), box(5, 9, 15, 11, 11, 16), box(5, 5, 15, 11, 7, 16), box(5, 7, 15, 7, 9, 16), box(9, 7, 15, 11, 9, 16), box(5, 5, 0, 11, 7, 1), box(5, 9, 0, 11, 11, 1), box(5, 7, 0, 7, 9, 1),
							box(9, 7, 0, 11, 9, 1), box(1, 0, 15, 15, 2, 16), box(1, 14, 0, 15, 16, 1), box(1, 0, 0, 15, 2, 1), box(0, 2, 0, 1, 14, 2));
					case WEST -> Shapes.or(box(1, 0, 1, 15, 15, 15), box(0, 0, 0, 1, 2, 16), box(0, 14, 0, 1, 16, 16), box(0, 2, 0, 1, 14, 2), box(0, 2, 14, 1, 14, 16), box(15, 0, 0, 16, 2, 16), box(15, 5, 5, 16, 7, 11), box(15, 9, 5, 16, 11, 11),
							box(15, 7, 9, 16, 9, 11), box(15, 7, 5, 16, 9, 7), box(0, 5, 5, 1, 7, 11), box(0, 7, 5, 1, 9, 7), box(0, 7, 9, 1, 9, 11), box(0, 9, 5, 1, 11, 11), box(15, 2, 0, 16, 14, 2), box(15, 14, 0, 16, 16, 16),
							box(1, 14, 0, 15, 16, 1), box(5, 9, 0, 11, 11, 1), box(5, 5, 0, 11, 7, 1), box(9, 7, 0, 11, 9, 1), box(5, 7, 0, 7, 9, 1), box(5, 5, 15, 11, 7, 16), box(5, 9, 15, 11, 11, 16), box(9, 7, 15, 11, 9, 16),
							box(5, 7, 15, 7, 9, 16), box(1, 0, 0, 15, 2, 1), box(1, 14, 15, 15, 16, 16), box(1, 0, 15, 15, 2, 16), box(15, 2, 14, 16, 14, 16));
				};
			}
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(1, 0, 1, 15, 15, 15), box(0, 0, 15, 16, 2, 16), box(0, 14, 15, 16, 16, 16), box(0, 2, 15, 2, 14, 16), box(14, 2, 15, 16, 14, 16), box(0, 0, 0, 16, 2, 1), box(5, 5, 0, 11, 7, 1), box(5, 9, 0, 11, 11, 1),
						box(9, 7, 0, 11, 9, 1), box(5, 7, 0, 7, 9, 1), box(5, 5, 15, 11, 7, 16), box(5, 7, 15, 7, 9, 16), box(9, 7, 15, 11, 9, 16), box(5, 9, 15, 11, 11, 16), box(0, 2, 0, 2, 14, 1), box(0, 14, 0, 16, 16, 1), box(0, 14, 1, 1, 16, 15),
						box(0, 9, 5, 1, 11, 11), box(0, 5, 5, 1, 7, 11), box(0, 7, 5, 1, 9, 7), box(0, 7, 9, 1, 9, 11), box(15, 5, 5, 16, 7, 11), box(15, 9, 5, 16, 11, 11), box(15, 7, 5, 16, 9, 7), box(15, 7, 9, 16, 9, 11), box(0, 0, 1, 1, 2, 15),
						box(15, 14, 1, 16, 16, 15), box(15, 0, 1, 16, 2, 15), box(14, 2, 0, 16, 14, 1));
				case NORTH -> Shapes.or(box(1, 0, 1, 15, 15, 15), box(0, 0, 0, 16, 2, 1), box(0, 14, 0, 16, 16, 1), box(14, 2, 0, 16, 14, 1), box(0, 2, 0, 2, 14, 1), box(0, 0, 15, 16, 2, 16), box(5, 5, 15, 11, 7, 16), box(5, 9, 15, 11, 11, 16),
						box(5, 7, 15, 7, 9, 16), box(9, 7, 15, 11, 9, 16), box(5, 5, 0, 11, 7, 1), box(9, 7, 0, 11, 9, 1), box(5, 7, 0, 7, 9, 1), box(5, 9, 0, 11, 11, 1), box(14, 2, 15, 16, 14, 16), box(0, 14, 15, 16, 16, 16),
						box(15, 14, 1, 16, 16, 15), box(15, 9, 5, 16, 11, 11), box(15, 5, 5, 16, 7, 11), box(15, 7, 9, 16, 9, 11), box(15, 7, 5, 16, 9, 7), box(0, 5, 5, 1, 7, 11), box(0, 9, 5, 1, 11, 11), box(0, 7, 9, 1, 9, 11),
						box(0, 7, 5, 1, 9, 7), box(15, 0, 1, 16, 2, 15), box(0, 14, 1, 1, 16, 15), box(0, 0, 1, 1, 2, 15), box(0, 2, 15, 2, 14, 16));
				case EAST -> Shapes.or(box(1, 0, 1, 15, 15, 15), box(15, 0, 0, 16, 2, 16), box(15, 14, 0, 16, 16, 16), box(15, 2, 14, 16, 14, 16), box(15, 2, 0, 16, 14, 2), box(0, 0, 0, 1, 2, 16), box(0, 5, 5, 1, 7, 11), box(0, 9, 5, 1, 11, 11),
						box(0, 7, 5, 1, 9, 7), box(0, 7, 9, 1, 9, 11), box(15, 5, 5, 16, 7, 11), box(15, 7, 9, 16, 9, 11), box(15, 7, 5, 16, 9, 7), box(15, 9, 5, 16, 11, 11), box(0, 2, 14, 1, 14, 16), box(0, 14, 0, 1, 16, 16),
						box(1, 14, 15, 15, 16, 16), box(5, 9, 15, 11, 11, 16), box(5, 5, 15, 11, 7, 16), box(5, 7, 15, 7, 9, 16), box(9, 7, 15, 11, 9, 16), box(5, 5, 0, 11, 7, 1), box(5, 9, 0, 11, 11, 1), box(5, 7, 0, 7, 9, 1),
						box(9, 7, 0, 11, 9, 1), box(1, 0, 15, 15, 2, 16), box(1, 14, 0, 15, 16, 1), box(1, 0, 0, 15, 2, 1), box(0, 2, 0, 1, 14, 2));
				case WEST -> Shapes.or(box(1, 0, 1, 15, 15, 15), box(0, 0, 0, 1, 2, 16), box(0, 14, 0, 1, 16, 16), box(0, 2, 0, 1, 14, 2), box(0, 2, 14, 1, 14, 16), box(15, 0, 0, 16, 2, 16), box(15, 5, 5, 16, 7, 11), box(15, 9, 5, 16, 11, 11),
						box(15, 7, 9, 16, 9, 11), box(15, 7, 5, 16, 9, 7), box(0, 5, 5, 1, 7, 11), box(0, 7, 5, 1, 9, 7), box(0, 7, 9, 1, 9, 11), box(0, 9, 5, 1, 11, 11), box(15, 2, 0, 16, 14, 2), box(15, 14, 0, 16, 16, 16), box(1, 14, 0, 15, 16, 1),
						box(5, 9, 0, 11, 11, 1), box(5, 5, 0, 11, 7, 1), box(9, 7, 0, 11, 9, 1), box(5, 7, 0, 7, 9, 1), box(5, 5, 15, 11, 7, 16), box(5, 9, 15, 11, 11, 16), box(9, 7, 15, 11, 9, 16), box(5, 7, 15, 7, 9, 16), box(1, 0, 0, 15, 2, 1),
						box(1, 14, 15, 15, 16, 16), box(1, 0, 15, 15, 2, 16), box(15, 2, 14, 16, 14, 16));
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.apply(state);
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, POWERED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(POWERED, false);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		MuxSwitchSignalproducersProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new MuxSiwtchBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	protected void affectNeighborsAfterRemoval(BlockState blockstate, ServerLevel world, BlockPos blockpos, boolean flag) {
		Containers.updateNeighboursAfterDestroy(blockstate, world, blockpos);
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof MuxSiwtchBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}