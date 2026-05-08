package logicandcomputings.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Containers;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import logicandcomputings.world.inventory.BufferguiMenu;

import logicandcomputings.procedures.BufferOnTickUpdateProcedure;
import logicandcomputings.procedures.BufferBlockAddedProcedure;

import logicandcomputings.block.entity.BufferBlockEntity;

import java.util.function.Function;

import io.netty.buffer.Unpooled;

public class BufferBlock extends Block implements EntityBlock {
	public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public BufferBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(1f, 10f).noCollission().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.IRON_XYLOPHONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(POWERED) == true) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(7, 7, 11, 9, 10, 13), box(5, 7, 9, 11, 10, 11), box(3, 7, 7, 13, 10, 9), box(2, 7, 5, 14, 10, 7), box(7, 8, 0, 9, 9, 5), box(7, 8, 13, 9, 9, 16));
					case NORTH -> Shapes.or(box(7, 7, 3, 9, 10, 5), box(5, 7, 5, 11, 10, 7), box(3, 7, 7, 13, 10, 9), box(2, 7, 9, 14, 10, 11), box(7, 8, 11, 9, 9, 16), box(7, 8, 0, 9, 9, 3));
					case EAST -> Shapes.or(box(11, 7, 7, 13, 10, 9), box(9, 7, 5, 11, 10, 11), box(7, 7, 3, 9, 10, 13), box(5, 7, 2, 7, 10, 14), box(0, 8, 7, 5, 9, 9), box(13, 8, 7, 16, 9, 9));
					case WEST -> Shapes.or(box(3, 7, 7, 5, 10, 9), box(5, 7, 5, 7, 10, 11), box(7, 7, 3, 9, 10, 13), box(9, 7, 2, 11, 10, 14), box(11, 8, 7, 16, 9, 9), box(0, 8, 7, 3, 9, 9));
					case UP -> Shapes.or(box(7, 11, 7, 9, 13, 10), box(5, 9, 7, 11, 11, 10), box(3, 7, 7, 13, 9, 10), box(2, 5, 7, 14, 7, 10), box(7, 0, 8, 9, 5, 9), box(7, 13, 8, 9, 16, 9));
					case DOWN -> Shapes.or(box(7, 3, 6, 9, 5, 9), box(5, 5, 6, 11, 7, 9), box(3, 7, 6, 13, 9, 9), box(2, 9, 6, 14, 11, 9), box(7, 11, 7, 9, 16, 8), box(7, 0, 7, 9, 3, 8));
				};
			}
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(7, 7, 11, 9, 10, 13), box(5, 7, 9, 11, 10, 11), box(3, 7, 7, 13, 10, 9), box(2, 7, 5, 14, 10, 7), box(7, 8, 0, 9, 9, 5), box(7, 8, 13, 9, 9, 16));
				case NORTH -> Shapes.or(box(7, 7, 3, 9, 10, 5), box(5, 7, 5, 11, 10, 7), box(3, 7, 7, 13, 10, 9), box(2, 7, 9, 14, 10, 11), box(7, 8, 11, 9, 9, 16), box(7, 8, 0, 9, 9, 3));
				case EAST -> Shapes.or(box(11, 7, 7, 13, 10, 9), box(9, 7, 5, 11, 10, 11), box(7, 7, 3, 9, 10, 13), box(5, 7, 2, 7, 10, 14), box(0, 8, 7, 5, 9, 9), box(13, 8, 7, 16, 9, 9));
				case WEST -> Shapes.or(box(3, 7, 7, 5, 10, 9), box(5, 7, 5, 7, 10, 11), box(7, 7, 3, 9, 10, 13), box(9, 7, 2, 11, 10, 14), box(11, 8, 7, 16, 9, 9), box(0, 8, 7, 3, 9, 9));
				case UP -> Shapes.or(box(7, 11, 7, 9, 13, 10), box(5, 9, 7, 11, 11, 10), box(3, 7, 7, 13, 9, 10), box(2, 5, 7, 14, 7, 10), box(7, 0, 8, 9, 5, 9), box(7, 13, 8, 9, 16, 9));
				case DOWN -> Shapes.or(box(7, 3, 6, 9, 5, 9), box(5, 5, 6, 11, 7, 9), box(3, 7, 6, 13, 9, 9), box(2, 9, 6, 14, 11, 9), box(7, 11, 7, 9, 16, 8), box(7, 0, 7, 9, 3, 8));
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.apply(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 0;
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
		return super.getStateForPlacement(context).setValue(FACING, context.getNearestLookingDirection().getOpposite()).setValue(POWERED, false);
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
		BufferOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		BufferBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Buffer");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new BufferguiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new BufferBlockEntity(pos, state);
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
		if (tileentity instanceof BufferBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}