package logicandcomputings.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
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

import logicandcomputings.world.inventory.PulsegeneratorguiMenu;

import logicandcomputings.procedures.PulsegeneratorOnTickUpdateProcedure;
import logicandcomputings.procedures.PulsegeneratorBlockAddedProcedure;

import logicandcomputings.block.entity.PulsegeneratorBlockEntity;

import java.util.function.Function;

import io.netty.buffer.Unpooled;

public class PulsegeneratorBlock extends Block implements EntityBlock {
	public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public PulsegeneratorBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(POWERED) == true) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(14, 0, 14, 16, 15, 16), box(12, 4, 14, 14, 12, 15), box(2, 4, 14, 4, 12, 15), box(2, 2, 14, 14, 4, 15), box(2, 12, 14, 14, 14, 15), box(2, 14, 14, 14, 15, 16), box(2, 0, 14, 14, 2, 16),
							box(0, 0, 14, 2, 15, 16), box(0, 0, 0, 16, 15, 14), box(0, 15, 0, 16, 16, 1), box(0, 15, 15, 16, 16, 16), box(0, 15, 1, 1, 16, 15), box(15, 15, 1, 16, 16, 15));
					case NORTH -> Shapes.or(box(0, 0, 0, 2, 15, 2), box(2, 4, 1, 4, 12, 2), box(12, 4, 1, 14, 12, 2), box(2, 2, 1, 14, 4, 2), box(2, 12, 1, 14, 14, 2), box(2, 14, 0, 14, 15, 2), box(2, 0, 0, 14, 2, 2), box(14, 0, 0, 16, 15, 2),
							box(0, 0, 2, 16, 15, 16), box(0, 15, 15, 16, 16, 16), box(0, 15, 0, 16, 16, 1), box(15, 15, 1, 16, 16, 15), box(0, 15, 1, 1, 16, 15));
					case EAST -> Shapes.or(box(14, 0, 0, 16, 15, 2), box(14, 4, 2, 15, 12, 4), box(14, 4, 12, 15, 12, 14), box(14, 2, 2, 15, 4, 14), box(14, 12, 2, 15, 14, 14), box(14, 14, 2, 16, 15, 14), box(14, 0, 2, 16, 2, 14),
							box(14, 0, 14, 16, 15, 16), box(0, 0, 0, 14, 15, 16), box(0, 15, 0, 1, 16, 16), box(15, 15, 0, 16, 16, 16), box(1, 15, 15, 15, 16, 16), box(1, 15, 0, 15, 16, 1));
					case WEST -> Shapes.or(box(0, 0, 14, 2, 15, 16), box(1, 4, 12, 2, 12, 14), box(1, 4, 2, 2, 12, 4), box(1, 2, 2, 2, 4, 14), box(1, 12, 2, 2, 14, 14), box(0, 14, 2, 2, 15, 14), box(0, 0, 2, 2, 2, 14), box(0, 0, 0, 2, 15, 2),
							box(2, 0, 0, 16, 15, 16), box(15, 15, 0, 16, 16, 16), box(0, 15, 0, 1, 16, 16), box(1, 15, 0, 15, 16, 1), box(1, 15, 15, 15, 16, 16));
					case UP -> Shapes.or(box(0, 14, 0, 2, 16, 15), box(2, 14, 4, 4, 15, 12), box(12, 14, 4, 14, 15, 12), box(2, 14, 2, 14, 15, 4), box(2, 14, 12, 14, 15, 14), box(2, 14, 14, 14, 16, 15), box(2, 14, 0, 14, 16, 2),
							box(14, 14, 0, 16, 16, 15), box(0, 0, 0, 16, 14, 15), box(0, 0, 15, 16, 1, 16), box(0, 15, 15, 16, 16, 16), box(15, 1, 15, 16, 15, 16), box(0, 1, 15, 1, 15, 16));
					case DOWN -> Shapes.or(box(0, 0, 1, 2, 2, 16), box(2, 1, 4, 4, 2, 12), box(12, 1, 4, 14, 2, 12), box(2, 1, 12, 14, 2, 14), box(2, 1, 2, 14, 2, 4), box(2, 0, 1, 14, 2, 2), box(2, 0, 14, 14, 2, 16), box(14, 0, 1, 16, 2, 16),
							box(0, 2, 1, 16, 16, 16), box(0, 15, 0, 16, 16, 1), box(0, 0, 0, 16, 1, 1), box(15, 1, 0, 16, 15, 1), box(0, 1, 0, 1, 15, 1));
				};
			}
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(14, 0, 14, 16, 15, 16), box(12, 4, 14, 14, 12, 15), box(2, 4, 14, 4, 12, 15), box(2, 2, 14, 14, 4, 15), box(2, 12, 14, 14, 14, 15), box(2, 14, 14, 14, 15, 16), box(2, 0, 14, 14, 2, 16),
						box(0, 0, 14, 2, 15, 16), box(0, 0, 0, 16, 15, 14), box(0, 15, 0, 16, 16, 1), box(0, 15, 15, 16, 16, 16), box(0, 15, 1, 1, 16, 15), box(15, 15, 1, 16, 16, 15));
				case NORTH -> Shapes.or(box(0, 0, 0, 2, 15, 2), box(2, 4, 1, 4, 12, 2), box(12, 4, 1, 14, 12, 2), box(2, 2, 1, 14, 4, 2), box(2, 12, 1, 14, 14, 2), box(2, 14, 0, 14, 15, 2), box(2, 0, 0, 14, 2, 2), box(14, 0, 0, 16, 15, 2),
						box(0, 0, 2, 16, 15, 16), box(0, 15, 15, 16, 16, 16), box(0, 15, 0, 16, 16, 1), box(15, 15, 1, 16, 16, 15), box(0, 15, 1, 1, 16, 15));
				case EAST -> Shapes.or(box(14, 0, 0, 16, 15, 2), box(14, 4, 2, 15, 12, 4), box(14, 4, 12, 15, 12, 14), box(14, 2, 2, 15, 4, 14), box(14, 12, 2, 15, 14, 14), box(14, 14, 2, 16, 15, 14), box(14, 0, 2, 16, 2, 14),
						box(14, 0, 14, 16, 15, 16), box(0, 0, 0, 14, 15, 16), box(0, 15, 0, 1, 16, 16), box(15, 15, 0, 16, 16, 16), box(1, 15, 15, 15, 16, 16), box(1, 15, 0, 15, 16, 1));
				case WEST -> Shapes.or(box(0, 0, 14, 2, 15, 16), box(1, 4, 12, 2, 12, 14), box(1, 4, 2, 2, 12, 4), box(1, 2, 2, 2, 4, 14), box(1, 12, 2, 2, 14, 14), box(0, 14, 2, 2, 15, 14), box(0, 0, 2, 2, 2, 14), box(0, 0, 0, 2, 15, 2),
						box(2, 0, 0, 16, 15, 16), box(15, 15, 0, 16, 16, 16), box(0, 15, 0, 1, 16, 16), box(1, 15, 0, 15, 16, 1), box(1, 15, 15, 15, 16, 16));
				case UP -> Shapes.or(box(0, 14, 0, 2, 16, 15), box(2, 14, 4, 4, 15, 12), box(12, 14, 4, 14, 15, 12), box(2, 14, 2, 14, 15, 4), box(2, 14, 12, 14, 15, 14), box(2, 14, 14, 14, 16, 15), box(2, 14, 0, 14, 16, 2),
						box(14, 14, 0, 16, 16, 15), box(0, 0, 0, 16, 14, 15), box(0, 0, 15, 16, 1, 16), box(0, 15, 15, 16, 16, 16), box(15, 1, 15, 16, 15, 16), box(0, 1, 15, 1, 15, 16));
				case DOWN -> Shapes.or(box(0, 0, 1, 2, 2, 16), box(2, 1, 4, 4, 2, 12), box(12, 1, 4, 14, 2, 12), box(2, 1, 12, 14, 2, 14), box(2, 1, 2, 14, 2, 4), box(2, 0, 1, 14, 2, 2), box(2, 0, 14, 14, 2, 16), box(14, 0, 1, 16, 2, 16),
						box(0, 2, 1, 16, 16, 16), box(0, 15, 0, 16, 16, 1), box(0, 0, 0, 16, 1, 1), box(15, 1, 0, 16, 15, 1), box(0, 1, 0, 1, 15, 1));
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
		PulsegeneratorOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		PulsegeneratorBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Pulsegenerator");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new PulsegeneratorguiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
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
		return new PulsegeneratorBlockEntity(pos, state);
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
		if (tileentity instanceof PulsegeneratorBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}