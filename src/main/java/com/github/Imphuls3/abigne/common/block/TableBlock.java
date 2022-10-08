package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TableBlock extends AbIgneBlock {
    VoxelShape NORMAL = Shapes.box(0, 0.75, 0, 1, 1, 1),
            CORNER = Shapes.joinUnoptimized(
                    NORMAL,
                    Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.75, 0.9375),
                    BooleanOp.OR);

    public static BooleanProperty WEST = BooleanProperty.create("west"),
            EAST = BooleanProperty.create("east"),
            NORTH = BooleanProperty.create("north"),
            SOUTH = BooleanProperty.create("south"),
            NW = BooleanProperty.create("nw"),
            NE = BooleanProperty.create("ne"),
            SW = BooleanProperty.create("sw"),
            SE = BooleanProperty.create("se");

    public TableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WEST, false)
                .setValue(EAST, false)
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(NW, false)
                .setValue(NE, false)
                .setValue(SW, false)
                .setValue(SE, false));
    }

    protected BlockState updateCorners(BlockGetter world, BlockPos pos, BlockState state) {
        BlockState blockstate = world.getBlockState(pos.north());
        BlockState blockstate1 = world.getBlockState(pos.east());
        BlockState blockstate2 = world.getBlockState(pos.south());
        BlockState blockstate3 = world.getBlockState(pos.west());
        BlockState blockstate4 = world.getBlockState(pos.north().west());
        BlockState blockstate5 = world.getBlockState(pos.north().east());
        BlockState blockstate6 = world.getBlockState(pos.south().west());
        BlockState blockstate7 = world.getBlockState(pos.south().east());
        boolean conn = blockstate.getBlock() == this,
                conn1 = blockstate1.getBlock() == this,
                conn2 = blockstate2.getBlock() == this,
                conn3 = blockstate3.getBlock() == this,
                conn4 = blockstate4.getBlock() == this,
                conn5 = blockstate5.getBlock() == this,
                conn6 = blockstate6.getBlock() == this,
                conn7 = blockstate7.getBlock() == this;
        return state
                .setValue(NORTH, conn).setValue(EAST, conn1)
                .setValue(SOUTH, conn2).setValue(WEST, conn3)
                .setValue(NW, conn4).setValue(NE, conn5)
                .setValue(SW, conn6).setValue(SE, conn7);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter iblockreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return updateCorners(iblockreader, blockpos, super.getStateForPlacement(context));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
        return updateCorners(world, pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WEST, EAST, NORTH, SOUTH, NW, NE, SW, SE);
    }
}
