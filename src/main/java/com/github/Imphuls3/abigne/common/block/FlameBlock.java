package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.common.blockentity.FlameBlockEntity;
import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class FlameBlock extends AbIgneBlock<FlameBlockEntity> {
    public static final VoxelShape SHAPE = Block.box(6, 6, 6, 10, 10, 10);

    public Item[] items = {Items.LIME_DYE,
            Items.LIGHT_BLUE_DYE,
            Items.BLUE_DYE,
            Items.YELLOW_DYE,
            Items.PURPLE_DYE,
            Items.ORANGE_DYE,
            Items.GREEN_DYE,
            Items.CYAN_DYE,
            Items.MAGENTA_DYE,
            Items.CRIMSON_FUNGUS,
            Items.WARPED_FUNGUS
    };
    public static Color[] colors3 = {new Color(25, 255, 100),//1
            new Color(25, 255, 255),//2
            new Color(5, 25, 255),//3
            new Color(255, 5, 125),//4
            new Color(185, 175, 215),//5
            new Color(100, 5, 255),//6
            new Color(2, 100, 2),//7
            new Color(25, 100, 200),//8
            new Color(150, 25, 75),//9
            new Color(150, 25, 75),//Crimson
            new Color(25, 255, 175)//Warped
    };
    public static Color[] colors2 = {new Color(85, 255, 0),//1
            new Color(0, 150, 255),//2
            new Color(75, 5, 255),//3
            new Color(255, 200, 5),//4
            new Color(150, 75, 175),//5
            new Color(175, 5, 255),//6
            new Color(5, 175, 5),//7
            new Color(25, 175, 255),//8
            new Color(255, 25, 100),//9
            new Color(200, 5, 15),//Crimson
            new Color(5, 200, 150)//Warped
    };
    public static Color[] colors = {new Color(125, 255, 5),//1
            new Color(125, 5, 255),//2
            new Color(175, 50, 255),//3
            new Color(2, 150, 255),//4
            new Color(125, 50, 255),//5
            new Color(255, 200, 0),//6
            new Color(75, 200, 100),//7
            new Color(5, 255, 150),//8
            new Color(225, 100, 250),//9
            new Color(255, 50, 5),//Crimson
            new Color(255, 100, 50)//Warped
    };
    public static Color[] colorsMain = {new Color(200, 255, 25),//1
            new Color(255, 100, 200),//2
            new Color(255, 25, 185),//3
            new Color(5, 150, 255),//4
            new Color(100, 25, 255),//5
            new Color(255, 255, 5),//6
            new Color(180, 200, 150),//7
            new Color(25, 200, 75),//8
            new Color(255, 200, 15),//9
            new Color(255, 125, 25),//Crimson
            new Color(255, 125, 25)//Warped
    };

    public Color color3 = new Color(25, 255, 100);
    public Color color2 = new Color(85, 255, 0);
    public Color color = new Color(125, 255, 5);
    public Color colorMain = new Color(200, 255, 25);

    public static final IntegerProperty COLORS = IntegerProperty.create("colors", 0, colors3.length);

    public FlameBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.FLAME);
        registerDefaultState(defaultBlockState().setValue(COLORS, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COLORS);
        super.createBlockStateDefinition(builder);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(COLORS, 0);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        for (int i = 0; i < items.length; i++) {
            if(player.getItemInHand(hand).getItem().equals(Arrays.stream(items).toList().get(i))) {
                player.swing(hand);
                ItemStack itemStack = player.getItemInHand(hand);
                if(!player.isCreative()) {
                    if(itemStack.getCount() <= 1) {
                        player.setItemInHand(hand, ItemStack.EMPTY);
                    } else {
                        itemStack.setCount(itemStack.getCount()-1);
                        player.setItemInHand(hand, itemStack);
                    }
                }
                this.color3 =  Stream.of(colors3).toList().get(i);
                this.color2 = Stream.of(colors2).toList().get(i);
                this.color = Stream.of(colors).toList().get(i);
                this.colorMain = Stream.of(colorsMain).toList().get(i);
                level.setBlockAndUpdate(pos, state.setValue(COLORS, i));
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, level, pos, player, hand, ray);
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter getter, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 15;
    }
}
