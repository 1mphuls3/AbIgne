package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.client.particle.*;
import com.github.Imphuls3.abigne.core.blockentity.AbIgneBlockEntity;
import com.github.Imphuls3.abigne.core.helper.VecHelper;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class FlameBlockEntity extends AbIgneBlockEntity {
    private int i = 0;
    private int k = 0;

    public Item[] items = {Items.LIME_DYE,
            Items.RED_DYE,
            Items.ORANGE_DYE,
            Items.YELLOW_DYE,
            Items.GREEN_DYE,
            Items.CYAN_DYE,
            Items.LIGHT_BLUE_DYE,
            Items.BLUE_DYE,
            Items.PURPLE_DYE,
            Items.MAGENTA_DYE,
            Items.PINK_DYE,
            Items.WHITE_DYE,
            Items.LIGHT_GRAY_DYE,
            Items.GRAY_DYE,
            Items.BLACK_DYE,
            Items.BROWN_DYE,
    };
    public static Color[] colors3 = {new Color(25, 255, 100),//lime
            new Color(255, 25, 75),//red
            new Color(255, 155, 15),//orange
            new Color(255, 255, 25),//yellow
            new Color(15, 255, 85),//green
            new Color(15, 255, 75),//cyan
            new Color(100, 255, 255),//light blue
            new Color(15, 25, 255),//blue
            new Color(75, 15, 255),//purple
            new Color(200, 50, 255),//magenta
            new Color(255, 200, 225),//pink
            new Color(110, 135, 135),//white
            new Color(65, 75, 75),//light grey
            new Color(35, 45, 45),//grey
            new Color(24, 25, 25),//black
            new Color(175, 125, 65),//brown
    };
    public static Color[] colors2 = {new Color(85, 255, 0),//lime
            new Color(255, 5, 5),//red
            new Color(255, 125, 15),//orange
            new Color(255, 255, 25),//yellow
            new Color(15, 255, 85),//green
            new Color(15, 255, 150),//cyan
            new Color(25, 225, 255),//light blue
            new Color(15, 75, 255),//blue
            new Color(50, 15, 255),//purple
            new Color(150, 25, 255),//magenta
            new Color(255, 155, 225),//pink
            new Color(175, 200, 200),//white
            new Color(115, 125, 125),//light grey
            new Color(65, 75, 75),//grey
            new Color(19, 20, 20),//black
            new Color(145, 90, 45),//brown
    };
    public static Color[] colors = {new Color(125, 255, 5),//lime
            new Color(255, 25, 5),//red
            new Color(255, 75, 15),//orange
            new Color(255, 200, 25),//yellow
            new Color(15, 255, 55),//green
            new Color(25, 175, 125),//cyan
            new Color(35, 225, 255),//light blue
            new Color(15, 100, 255),//blue
            new Color(50, 15, 255),//purple
            new Color(125, 25, 255),//magenta
            new Color(255, 100, 225),//pink
            new Color(225, 225, 225),//white
            new Color(90, 100, 100),//light grey
            new Color(43, 45, 45),//grey
            new Color(14, 15, 15),//black
            new Color(125, 75, 35),//brown
    };

    public Color color3 = new Color(25, 255, 100);
    public Color color2 = new Color(85, 255, 0);
    public Color color = new Color(125, 255, 5);

    public FlameBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLAME.get(), pos, state);
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
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
                return InteractionResult.SUCCESS;
            }
        }
        return super.onUse(player, hand);
    }

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        super.tick(level, state, pos);
        float alpha = 0.85F;
        Vec3 vec3 = VecHelper.vec3FromBlockPos(pos);
        vec3 = vec3.add(VecHelper.CENTER);
        float velocity = 0.04f + level.random.nextFloat() * 0.006f;

        if(i == 2) {
            ColorParticleOptions circle = (ColorParticleOptions) SparkleParticleData.createData(new Color(color.getRed(), color.getGreen(), color.getBlue()),
                    0.17F, 1F, 25);
            level.addParticle(circle, vec3.x, vec3.y, vec3.z, 0, 0, 0);
            level.addParticle(circle, vec3.x, vec3.y, vec3.z, 0, 0, 0);
            i = 0;
        } else {
            i++;
        }

        ColorParticleOptions main = (ColorParticleOptions) FlameParticleData.createData(new Color(color.getRed(), color.getGreen(), color.getBlue()), 0.25F, alpha, 36);
        level.addParticle(main, vec3.x - Mth.randomBetween(RandomSource.create(), -0.1F, 0.1F), vec3.y + 0.075, vec3.z + Mth.randomBetween(RandomSource.create(),
                -0.1F, 0.1F), 0, velocity/2.2, 0);

        ColorParticleOptions main2 = (ColorParticleOptions)FlameParticleData.createData(new Color(color2.getRed(), color2.getGreen(), color2.getBlue()), 0.2F, alpha, 36);
        level.addParticle(main2, vec3.x + Mth.randomBetween(RandomSource.create(), -0.1F, 0.1F), vec3.y + 0.075, vec3.z - Mth.randomBetween(RandomSource.create(),
                -0.1F, 0.1F), 0, velocity/1.5F, 0);

        ColorParticleOptions accent = (ColorParticleOptions)FlameParticleData.createData(new Color(color3.getRed(), color3.getGreen(), color3.getBlue()), 0.25F, alpha, 32);
        level.addParticle(accent, vec3.x + Mth.randomBetween(RandomSource.create(), -0.05F, 0.05F), vec3.y + 0.06, vec3.z - Mth.randomBetween(RandomSource.create(), -0.05F, 0.05F), 0, velocity, 0);

        if(level.getRandom().nextFloat() < 0.06F) {
            ColorParticleOptions wisp = (ColorParticleOptions) WispParticleData.createData(
                    new Color(color3.getRed(), color3.getGreen(), color3.getBlue()), 0.33F, 1F, 45);
            level.addParticle(wisp, vec3.x, vec3.y+0.1, vec3.z, 0, 0.016, 0);
            level.addParticle(wisp, vec3.x, vec3.y+0.1, vec3.z, 0, 0.016, 0);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("color", color.getRGB());
        nbt.putInt("color2", color2.getRGB());
        nbt.putInt("color3", color3.getRGB());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        color = new Color(nbt.getInt("color"));
        color2 = new Color(nbt.getInt("color2"));
        color3 = new Color(nbt.getInt("color3"));
        super.load(nbt);
    }
}
