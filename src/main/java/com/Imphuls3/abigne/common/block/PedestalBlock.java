package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.common.block.entity.PedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class PedestalBlock extends Block implements EntityBlock {
    public PedestalBlock(Properties properties) {
        super(properties);
    }

    public static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 0, 4, 12, 4, 12),
            Block.box(5, 4, 5, 11, 10, 11),
            Block.box(3, 10, 3, 13, 13, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof final PedestalBlockEntity pedestal) {
            if (player.isCrouching()) {
                player.displayClientMessage(
                        new TextComponent("Current count is " + pedestal.getItemInSlot(0).getCount() + "!"), false);
            }
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        this.spawnDestroyParticles(level, player, pos, state);
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof final PedestalBlockEntity pedestal) {
            pedestal.prependStackAtBlock(pos);
        }
        level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> beType) {
        return level.isClientSide ? null : (level0, pos, state0, blockEntity) -> ((PedestalBlockEntity) blockEntity).tick();
    }

    @SuppressWarnings("resource")
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof final PedestalBlockEntity pedestal) {
            if (!player.isCrouching()) {
                final LocalPlayer user = Minecraft.getInstance().player;
                if(!user.getMainHandItem().isEmpty()){
                    pedestal.appendItem(player.getItemInHand(hand));
                } else{
                    pedestal.prependStack(player);
                }
            } else {
            }
        }
        return InteractionResult.SUCCESS;
    }
}
