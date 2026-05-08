package logicandcomputings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import io.netty.buffer.Unpooled;

import logicandcomputings.block.entity.LampBlockEntity;
import logicandcomputings.procedures.LampOnTickUpdateProcedure;
import logicandcomputings.world.inventory.LampGuiMenu;

public class LampBlock extends Block implements EntityBlock {

    public static final IntegerProperty GATHERED_SIGNAL =
            IntegerProperty.create("gathered_signal", 0, 15);

    public LampBlock(BlockBehaviour.Properties properties) {
        super(properties
                .sound(SoundType.METAL)
                .strength(1f, 10f)
                .lightLevel(state -> state.getValue(GATHERED_SIGNAL))
                .noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false)
                .instrument(NoteBlockInstrument.CHIME));

        this.registerDefaultState(
                this.stateDefinition.any().setValue(GATHERED_SIGNAL, 0)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(GATHERED_SIGNAL);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state,
                                     BlockGetter world,
                                     BlockPos pos,
                                     CollisionContext context) {

        return Shapes.empty();
    }

    @Override
    public void onPlace(BlockState state,
                        Level world,
                        BlockPos pos,
                        BlockState oldState,
                        boolean moving) {

        super.onPlace(state, world, pos, oldState, moving);

        if (!world.isClientSide()) {
            world.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public void tick(BlockState state,
                     ServerLevel world,
                     BlockPos pos,
                     RandomSource random) {

        super.tick(state, world, pos, random);

        LampOnTickUpdateProcedure.execute(
                world,
                pos.getX(),
                pos.getY(),
                pos.getZ()
        );

        world.scheduleTick(pos, this, 1);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(GATHERED_SIGNAL, 0);
    }

    public static int getColor(BlockAndTintGetter world,
                               BlockPos pos,
                               int tintIndex) {

        if (tintIndex != 1 || world == null || pos == null) {
            return 0xFFFFFF;
        }

        BlockEntity be = world.getBlockEntity(pos);

        if (be == null) {
            return 0xFFFFFF;
        }

        CompoundTag tag = be.getPersistentData();

        double signal = 0;

        if (tag.contains("gathered_signal")) {
            signal = tag.getDouble("gathered_signal").orElse(0.0);
        }

        if (signal <= 0) {
            return 0x333333;
        }

        float input = (float) (signal / 15.0);

        int r = (int) (Math.min(1.0,
                Math.max(0.0, 2.0 * input - 0.5)) * 255);

        int g = (int) (Math.min(1.0,
                Math.max(0.0,
                        1.0 - Math.abs(2.0 * input - 1.0))) * 255);

        int b = (int) (Math.min(1.0,
                Math.max(0.0,
                        1.5 - 2.0 * input)) * 255);

        return (r << 16) | (g << 8) | b;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state,
                                            Level world,
                                            BlockPos pos,
                                            Player player,
                                            BlockHitResult hit) {

        if (!world.isClientSide()
                && player instanceof ServerPlayer serverPlayer) {

            serverPlayer.openMenu(new MenuProvider() {

                @Override
                public Component getDisplayName() {
                    return Component.literal("Lamp");
                }

                @Override
                public AbstractContainerMenu createMenu(
                        int id,
                        Inventory inventory,
                        Player player) {

                    return new LampGuiMenu(
                            id,
                            inventory,
                            new FriendlyByteBuf(Unpooled.buffer())
                                    .writeBlockPos(pos)
                    );
                }
            }, pos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos,
                                      BlockState state) {

        return new LampBlockEntity(pos, state);
    }

    @Override
    public boolean triggerEvent(BlockState state,
                                Level world,
                                BlockPos pos,
                                int eventID,
                                int eventParam) {

        super.triggerEvent(
                state,
                world,
                pos,
                eventID,
                eventParam
        );

        BlockEntity blockEntity = world.getBlockEntity(pos);

        return blockEntity != null
                && blockEntity.triggerEvent(eventID, eventParam);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state,
                                     Level world,
                                     BlockPos pos) {

        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof LampBlockEntity be) {
            return AbstractContainerMenu
                    .getRedstoneSignalFromContainer(be);
        }

        return 0;
    }
}