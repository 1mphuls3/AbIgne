package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.common.blockentity.CopperPotBlockEntity;
import com.github.Imphuls3.abigne.common.blockentity.FluidEmitterBlockEntity;
import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class FluidEmitterBlock extends AbIgneBlock<FluidEmitterBlockEntity> {
    public FluidEmitterBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.FLUID_EMITTER);
    }
}
