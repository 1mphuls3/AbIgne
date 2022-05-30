package com.Imphuls3.abigne.common.block.entity;

import com.Imphuls3.abigne.common.block.entity.utils.ModBlockEntity;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class WallLampEntity extends ModBlockEntity {
    public WallLampEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityInit.WALL.get(), pWorldPosition, pBlockState);
    }
}
