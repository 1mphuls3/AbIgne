package com.Imphuls3.abigne.common.block.custom;

import com.Imphuls3.abigne.common.block.utils.ModBlock;
import com.Imphuls3.abigne.core.init.BlockInit;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EnflamedAshBlock extends Block {
    public static final EnumProperty<RedstoneSide> NORTH = BlockStateProperties.NORTH_REDSTONE;
    public static final EnumProperty<RedstoneSide> EAST = BlockStateProperties.EAST_REDSTONE;
    public static final EnumProperty<RedstoneSide> SOUTH = BlockStateProperties.SOUTH_REDSTONE;
    public static final EnumProperty<RedstoneSide> WEST = BlockStateProperties.WEST_REDSTONE;

    public static final BooleanProperty IGNITED = BooleanProperty.create("ignited");

    public static final Map<Direction, EnumProperty<RedstoneSide>> FACING_PROPERTY_MAP = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.SOUTH, SOUTH, Direction.WEST, WEST));

    private static final VoxelShape BASE_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D);

    private static final Map<Direction, VoxelShape> SIDE_TO_SHAPE = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH,
            Block.box(3.0D, 0.0D, 0.0D, 13.0D, 1.0D, 13.0D), Direction.SOUTH,
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 16.0D), Direction.EAST,
            Block.box(3.0D, 0.0D, 3.0D, 16.0D, 1.0D, 13.0D), Direction.WEST,
            Block.box(0.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D)));

    private static final Map<Direction, VoxelShape> SIDE_TO_ASCENDING_SHAPE = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH,
            Shapes.or(SIDE_TO_SHAPE.get(Direction.NORTH), Block.box(3.0D, 0.0D, 0.0D, 13.0D, 16.0D, 1.0D)),
            Direction.SOUTH, Shapes.or(SIDE_TO_SHAPE.get(Direction.SOUTH), Block.box(3.0D, 0.0D, 15.0D, 13.0D, 16.0D, 16.0D)),
            Direction.EAST, Shapes.or(SIDE_TO_SHAPE.get(Direction.EAST), Block.box(15.0D, 0.0D, 3.0D, 16.0D, 16.0D, 13.0D)),
            Direction.WEST, Shapes.or(SIDE_TO_SHAPE.get(Direction.WEST), Block.box(0.0D, 0.0D, 3.0D, 1.0D, 16.0D, 13.0D))));


    private final Map<BlockState, VoxelShape> stateToShapeMap = Maps.newHashMap();
    private final BlockState sideBaseState;

    public EnflamedAshBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.defaultBlockState().setValue(NORTH, RedstoneSide.NONE).setValue(EAST, RedstoneSide.NONE).setValue(SOUTH, RedstoneSide.NONE).setValue(WEST, RedstoneSide.NONE).setValue(IGNITED, false));
        this.sideBaseState = this.defaultBlockState().setValue(NORTH, RedstoneSide.NONE).setValue(EAST, RedstoneSide.NONE).setValue(SOUTH, RedstoneSide.NONE).setValue(WEST, RedstoneSide.NONE);

        for(BlockState blockstate : this.getStateDefinition().getPossibleStates()) {
            this.stateToShapeMap.put(blockstate, this.getShapeForState(blockstate));
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        ItemStack item = player.getItemInHand(hand);
        if(item.getItem() == Items.FLINT_AND_STEEL) {
            item.setDamageValue(item.getDamageValue() - 1);
            BlockState s = state.setValue(IGNITED, true);
            level.setBlock(pos, s, 1);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        BlockPos p = new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ());
        BlockState newState = defaultBlockState().setValue(IGNITED, true);
        level.setBlock(p, newState, 16);
    }

    private VoxelShape getShapeForState(BlockState state) {
        VoxelShape voxelshape = BASE_SHAPE;
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide redstoneside = state.getValue(FACING_PROPERTY_MAP.get(direction));
            if (redstoneside == RedstoneSide.SIDE) {
                voxelshape = Shapes.or(voxelshape, SIDE_TO_SHAPE.get(direction));
            } else if (redstoneside == RedstoneSide.UP) {
                voxelshape = Shapes.or(voxelshape, SIDE_TO_ASCENDING_SHAPE.get(direction));
            }
        }
        return voxelshape;
    }

    private static boolean areAllSidesInvalid(BlockState state) {
        return !state.getValue(NORTH).isConnected() && !state.getValue(SOUTH).isConnected() && !state.getValue(EAST).isConnected() && !state.getValue(WEST).isConnected();
    }

    private static boolean areAllSidesValid(BlockState state) {
        return state.getValue(NORTH).isConnected() && state.getValue(SOUTH).isConnected() && state.getValue(EAST).isConnected() && state.getValue(WEST).isConnected();
    }

    private BlockState recalculateFacingState(BlockGetter reader, BlockState state, BlockPos pos) {
        boolean flag = !reader.getBlockState(pos.above()).isRedstoneConductor(reader, pos);

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            if (!state.getValue(FACING_PROPERTY_MAP.get(direction)).isConnected()) {
                RedstoneSide redstoneside = this.recalculateSide(reader, pos, direction, flag);
                state = state.setValue(FACING_PROPERTY_MAP.get(direction), redstoneside);
            }
        }

        return state;
    }

    private RedstoneSide getSide(BlockGetter level, BlockPos pos, Direction face) {
        RedStoneWireBlock r;
        return this.recalculateSide(level, pos, face, !level.getBlockState(pos.above()).isRedstoneConductor(level, pos));
    }

    public void updateIndirectNeighbourShapes(BlockState state, LevelAccessor level, BlockPos pos, int flags, int recursionLeft) {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide redstoneside = state.getValue(FACING_PROPERTY_MAP.get(direction));
            if (redstoneside != RedstoneSide.NONE && !level.getBlockState(blockpos$mutable.setWithOffset(pos, direction)).is(this)) {
                blockpos$mutable.move(Direction.DOWN);
                BlockState blockstate = level.getBlockState(blockpos$mutable);
                if (!blockstate.is(Blocks.OBSERVER)) {
                    BlockPos blockpos = blockpos$mutable.relative(direction.getOpposite());
                    BlockState blockstate1 = blockstate.updateShape(direction.getOpposite(), level.getBlockState(blockpos), level, blockpos$mutable, blockpos);
                    updateOrDestroy(blockstate, blockstate1, level, blockpos$mutable, flags, recursionLeft);
                }

                blockpos$mutable.setWithOffset(pos, direction).move(Direction.UP);
                BlockState blockstate3 = level.getBlockState(blockpos$mutable);
                if (!blockstate3.is(Blocks.OBSERVER)) {
                    BlockPos blockpos1 = blockpos$mutable.relative(direction.getOpposite());
                    BlockState blockstate2 = blockstate3.updateShape(direction.getOpposite(), level.getBlockState(blockpos1), level, blockpos$mutable, blockpos1);
                    updateOrDestroy(blockstate3, blockstate2, level, blockpos$mutable, flags, recursionLeft);
                }
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.DOWN) {
            return state;
        } else if (facing == Direction.UP) {
            return this.getUpdatedState(level, state, currentPos);
        } else {
            RedstoneSide redstoneside = this.getSide(level, currentPos, facing);
            return redstoneside.isConnected() == state.getValue(FACING_PROPERTY_MAP.get(facing)).isConnected() && !areAllSidesValid(state)
                    ? state.setValue(FACING_PROPERTY_MAP.get(facing), redstoneside).setValue(IGNITED, state.getValue(IGNITED))
                    : this.getUpdatedState(level, this.sideBaseState.setValue(FACING_PROPERTY_MAP.get(facing), redstoneside), currentPos);
        }
    }

    private RedstoneSide recalculateSide(BlockGetter reader, BlockPos pos, Direction direction, boolean nonNormalCubeAbove) {
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = reader.getBlockState(blockpos);
        if (nonNormalCubeAbove) {
            boolean flag = this.canPlaceOnTopOf(reader, blockpos, blockstate);
            if (flag && canConnectTo(reader.getBlockState(blockpos.above()), reader, blockpos.above(), null) ) {
                if (blockstate.isFaceSturdy(reader, blockpos, direction.getOpposite())) {
                    return RedstoneSide.UP;
                }
                return RedstoneSide.SIDE;
            }
        }
        return !canConnectTo(blockstate, reader, blockpos, direction) && (blockstate.isRedstoneConductor(reader, blockpos) || !canConnectTo(reader.getBlockState(blockpos.below()), reader, blockpos.below(), null)) ? RedstoneSide.NONE : RedstoneSide.SIDE;
    }

    private boolean canPlaceOnTopOf(BlockGetter reader, BlockPos pos, BlockState state) {
        return state.isFaceSturdy(reader, pos, Direction.UP);
    }

    private BlockState getUpdatedState(BlockGetter reader, BlockState state, BlockPos pos) {
        boolean flag = areAllSidesInvalid(state);
        state = this.recalculateFacingState(reader, this.defaultBlockState(), pos);
        if (flag && areAllSidesInvalid(state)) {
            return state;
        } else {
            boolean flag1 = state.getValue(NORTH).isConnected();
            boolean flag2 = state.getValue(SOUTH).isConnected();
            boolean flag3 = state.getValue(EAST).isConnected();
            boolean flag4 = state.getValue(WEST).isConnected();
            boolean flag5 = !flag1 && !flag2;
            boolean flag6 = !flag3 && !flag4;
            if (!flag4 && flag5) {
                state = state.setValue(WEST, RedstoneSide.SIDE);
            }

            if (!flag3 && flag5) {
                state = state.setValue(EAST, RedstoneSide.SIDE);
            }

            if (!flag1 && flag6) {
                state = state.setValue(NORTH, RedstoneSide.SIDE);
            }

            if (!flag2 && flag6) {
                state = state.setValue(SOUTH, RedstoneSide.SIDE);
            }

            return state;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.stateToShapeMap.get(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getUpdatedState(context.getLevel(), this.sideBaseState, context.getClickedPos());
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock()) && !level.isClientSide) {
            for(Direction direction : Direction.Plane.VERTICAL) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }

            this.updateNeighboursStateChange(level, pos);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!isMoving && !state.is(newState.getBlock())) {
            super.onRemove(state, level, pos, newState, isMoving);
            if (!level.isClientSide) {
                for(Direction direction : Direction.values()) {
                    level.updateNeighborsAt(pos.relative(direction), this);
                }

                this.updateNeighboursStateChange(level, pos);
            }
        }
    }

    private void updateChangedConnections(Level world, BlockPos pos, BlockState prevState, BlockState newState) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = pos.relative(direction);
            if (prevState.getValue(FACING_PROPERTY_MAP.get(direction)).isConnected() != newState.getValue(FACING_PROPERTY_MAP.get(direction)).isConnected() && world.getBlockState(blockpos).isRedstoneConductor(world, blockpos)) {
                world.updateNeighborsAtExceptFromFacing(blockpos, newState.getBlock(), direction.getOpposite());
            }
        }

    }

    private void notifyWireNeighborsOfStateChange(Level level, BlockPos pos) {
        if (level.getBlockState(pos).is(this)) {
            level.updateNeighborsAt(pos, this);

            for(Direction direction : Direction.values()) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }
        }
    }

    private void updateNeighboursStateChange(Level world, BlockPos pos) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            this.notifyWireNeighborsOfStateChange(world, pos.relative(direction));
        }

        for(Direction direction1 : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = pos.relative(direction1);
            if (world.getBlockState(blockpos).isRedstoneConductor(world, blockpos)) {
                this.notifyWireNeighborsOfStateChange(world, blockpos.above());
            } else {
                this.notifyWireNeighborsOfStateChange(world, blockpos.below());
            }
        }

    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            if (!state.canSurvive(level, pos)) {
                dropResources(state, level, pos);
                level.removeBlock(pos, false);
            }
        }
    }

    protected static boolean canConnectTo(BlockState blockState, BlockGetter world, BlockPos pos, Direction side) {
        if (blockState.is(BlockInit.ENFLAMED_ASH.get())) {
            return true;
        }
        return false;
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NORTH, SOUTH, EAST, WEST, IGNITED);
    }
}
