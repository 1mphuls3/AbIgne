package com.Imphuls3.abigne.common.blockentity;

import com.Imphuls3.abigne.core.systems.blockentity.AbIgneBlockEntity;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WallLampEntity extends AbIgneBlockEntity {
    public WallLampEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegistry.WALL.get(), pWorldPosition, pBlockState);
    }
}
