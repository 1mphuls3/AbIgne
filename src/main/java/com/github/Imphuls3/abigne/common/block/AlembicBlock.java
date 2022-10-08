package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class AlembicBlock extends AbIgneBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final VoxelShape SHAPE_N = Stream.of(
            Block.box(8, 0, 4, 16, 4, 12),
            Block.box(8, 5, 4, 16, 6, 12),
            Block.box(9, 4, 5, 15, 5, 11),
            Block.box(1, 2, 5, 7, 3, 11),
            Block.box(1, 0, 5, 7, 1, 11),
            Block.box(9, 6, 5, 15, 13, 11),
            Block.box(11, 13, 7, 13, 17, 9),
            Block.box(3, 8, 7, 5, 10, 9),
            Block.box(2.5, 10, 6.5, 5.5, 12, 9.5),
            Block.box(5.499999976158138, 18.000000023841867, 7.5, 10.49999997615814, 19.00000002384187, 8.5),
            Block.box(3.499999976158138, 12.000000023841867, 7.5, 4.49999997615814, 17.00000002384187, 8.5),
            Block.box(10.5, 17, 6.5, 13.5, 20.000000000000007, 9.5),
            Block.box(2.5, 17, 6.5, 5.5, 20.000000000000007, 9.5),
            Block.box(2, 1, 6, 6, 2, 10),
            Block.box(2, 3, 6, 6, 8, 10),
            Block.box(2, 3, 6, 6, 8, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_W = Stream.of(
            Block.box(4, 0, 0, 12, 4, 8),
            Block.box(4, 5, 0, 12, 6, 8),
            Block.box(5, 4, 1, 11, 5, 7),
            Block.box(5, 2, 9, 11, 3, 15),
            Block.box(5, 0, 9, 11, 1, 15),
            Block.box(5, 6, 1, 11, 13, 7),
            Block.box(7, 13, 3, 9, 17, 5),
            Block.box(7, 8, 11, 9, 10, 13),
            Block.box(6.5, 10, 10.5, 9.5, 12, 13.5),
            Block.box(7.5, 18, 5.5, 8.5, 19, 10.5),
            Block.box(7.5, 12, 11.5, 8.5, 17, 12.5),
            Block.box(6.5, 17, 2.5, 9.5, 20, 5.5),
            Block.box(6.5, 17, 10.5, 9.5, 20, 13.5),
            Block.box(6, 1, 10, 10, 2, 14),
            Block.box(6, 3, 10, 10, 8, 14),
            Block.box(6, 3, 10, 10, 8, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_S = Stream.of(
            Block.box(0, 0, 4, 8, 4, 12),
            Block.box(0, 5, 4, 8, 6, 12),
            Block.box(1, 4, 5, 7, 5, 11),
            Block.box(9, 2, 5, 15, 3, 11),
            Block.box(9, 0, 5, 15, 1, 11),
            Block.box(1, 6, 5, 7, 13, 11),
            Block.box(3, 13, 7, 5, 17, 9),
            Block.box(11, 8, 7, 13, 10, 9),
            Block.box(10.5, 10, 6.5, 13.5, 12, 9.5),
            Block.box(5.5, 18, 7.5, 10.5, 19, 8.5),
            Block.box(11.5, 12, 7.5, 12.5, 17, 8.5),
            Block.box(2.5, 17, 6.5, 5.5, 20, 9.5),
            Block.box(10.5, 17, 6.5, 13.5, 20, 9.5),
            Block.box(10, 1, 6, 14, 2, 10),
            Block.box(10, 3, 6, 14, 8, 10),
            Block.box(10, 3, 6, 14, 8, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_E = Stream.of(
            Block.box(4, 0, 8, 12, 4, 16),
            Block.box(4, 5, 8, 12, 6, 16),
            Block.box(5, 4, 9, 11, 5, 15),
            Block.box(5, 2, 1, 11, 3, 7),
            Block.box(5, 0, 1, 11, 1, 7),
            Block.box(5, 6, 9, 11, 13, 15),
            Block.box(7, 13, 11, 9, 17, 13),
            Block.box(7, 8, 3, 9, 10, 5),
            Block.box(6.5, 10, 2.5, 9.5, 12, 5.5),
            Block.box(7.5, 18, 5.5, 8.5, 19, 10.5),
            Block.box(7.5, 12, 3.5, 8.5, 17, 4.5),
            Block.box(6.5, 17, 10.5, 9.5, 20, 13.5),
            Block.box(6.5, 17, 2.5, 9.5, 20, 5.5),
            Block.box(6, 1, 2, 10, 2, 6),
            Block.box(6, 3, 2, 10, 8, 6),
            Block.box(6, 3, 2, 10, 8, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public AlembicBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        setBlockEntity(BlockEntityRegistry.ALEMBIC);
    }



    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
