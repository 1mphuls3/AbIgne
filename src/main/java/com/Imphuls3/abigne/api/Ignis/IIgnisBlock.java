package com.Imphuls3.abigne.api.Ignis;

import net.minecraft.world.level.block.entity.BlockEntity;

public interface IIgnisBlock{

        int getCurrentIgnis();

        default BlockEntity tileEntity() {
            return (BlockEntity) this;
        }
}
