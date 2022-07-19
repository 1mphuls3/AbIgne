package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.common.block.entity.CalmingTorchBlockEntity;
import com.Imphuls3.abigne.common.block.utils.ModBlock;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;
import java.util.stream.Stream;

public class CalmingTorchBlock extends ModBlock<CalmingTorchBlockEntity> {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public CalmingTorchBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityInit.TORCH);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        Item item = player.getItemInHand(hand).getItem();
        Random random = new Random();
        if(item == Items.FLINT_AND_STEEL && !state.getValue(LIT)) {
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.NEUTRAL, 0.6f, random.nextFloat() * 0.3f + 0.9f);
            BlockState newState = state.setValue(LIT, true);
            level.setBlock(pos, newState, 1);
            return InteractionResult.SUCCESS;
        } else if (item == Items.WATER_BUCKET && state.getValue(LIT)) {
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL, 0.6f, random.nextFloat() * 0.8F);
            BlockState newState = state.setValue(LIT, false);
            level.setBlock(pos, newState, 1);
            player.setItemInHand(hand, Items.BUCKET.getDefaultInstance());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
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
}
