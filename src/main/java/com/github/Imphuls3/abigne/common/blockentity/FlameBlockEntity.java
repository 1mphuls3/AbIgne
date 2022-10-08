package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.client.particle.ParticleColor;
import com.github.Imphuls3.abigne.client.particle.SparkleParticleData;
import com.github.Imphuls3.abigne.common.block.FlameBlock;
import com.github.Imphuls3.abigne.core.blockentity.AbIgneBlockEntity;
import com.github.Imphuls3.abigne.core.helper.VecHelper;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import com.github.Imphuls3.abigne.client.particle.ColorParticleTypeData;
import com.github.Imphuls3.abigne.client.particle.FlameParticleData;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class FlameBlockEntity extends AbIgneBlockEntity {

    public FlameBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLAME.get(), pos, state);
    }

    private Color color3 = new Color(25, 255, 100);
    private Color color2 = new Color(85, 255, 0);
    private Color color = new Color(125, 255, 5);
    private Color colorMain = new Color(200, 255, 25);

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        super.tick(level, state, pos);
        if(state.getBlock() instanceof FlameBlock block) {
            BlockState blockState = level.getBlockState(pos);
            if(block.color3 != null && block.color2 != null && block.color != null && block.colorMain != null) {
                this.color3 = Arrays.stream(FlameBlock.colors3).toList().get(blockState.getValue(FlameBlock.COLORS));
                this.color2 = Arrays.stream(FlameBlock.colors2).toList().get(blockState.getValue(FlameBlock.COLORS));
                this.color = Arrays.stream(FlameBlock.colors).toList().get(blockState.getValue(FlameBlock.COLORS));
                this.colorMain = Arrays.stream(FlameBlock.colorsMain).toList().get(blockState.getValue(FlameBlock.COLORS));
            }
            float alpha = 0.85F;

            Vec3 vec3 = VecHelper.vec3FromBlockPos(this.getBlockPos());
            vec3 = vec3.add(VecHelper.CENTER);
            float velocity = 0.04f + level.random.nextFloat() * 0.006f;
            if (level.random.nextFloat() < 0.2f) {

            }
            ColorParticleTypeData circle = (ColorParticleTypeData) SparkleParticleData.createData(new ParticleColor(colorMain.getRed(), colorMain.getGreen(), colorMain.getBlue()),
                    0.25F, 1F, 45);
            level.addParticle(circle, vec3.x, vec3.y, vec3.z, 0, 0, 0);
            for (int i = 0; i < 3; i++) {
                ColorParticleTypeData main = (ColorParticleTypeData) FlameParticleData.createData(new ParticleColor(color.getRed(), color.getGreen(), color.getBlue()), 0.25F, alpha, 36);
                ColorParticleTypeData main2 = (ColorParticleTypeData)FlameParticleData.createData(new ParticleColor(color2.getRed(), color2.getGreen(), color2.getBlue()), 0.2F, alpha, 36);
                level.addParticle(main, vec3.x - Mth.randomBetween(new Random(), -0.1F, 0.1F), vec3.y + 0.06, vec3.z + Mth.randomBetween(new Random(), -0.1F, 0.1F), 0, velocity/2, 0);
                level.addParticle(main2, vec3.x + Mth.randomBetween(new Random(), -0.1F, 0.1F), vec3.y + 0.06, vec3.z - Mth.randomBetween(new Random(), -0.1F, 0.1F), 0, velocity/1.3F, 0);
            }
            for (int i = 0; i < 1; i++) {
                ColorParticleTypeData accent = (ColorParticleTypeData)FlameParticleData.createData(new ParticleColor(color3.getRed(), color3.getGreen(), color3.getBlue()), 0.25F, alpha, 32);
                level.addParticle(accent, vec3.x + Mth.randomBetween(new Random(), -0.05F, 0.05F), vec3.y + 0.06, vec3.z - Mth.randomBetween(new Random(), -0.05F, 0.05F), 0, velocity, 0);
            }
        }
    }
}
