package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.common.blockentity.IgnisVoidBlockEntity;
import com.Imphuls3.abigne.core.systems.block.AbIgneBlock;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;

public class IgnisVoidBlock extends AbIgneBlock<IgnisVoidBlockEntity> {
    public IgnisVoidBlock(Properties properties)
    {
        super(properties);
        setBlockEntity(BlockEntityRegistry.VOID);
    }
}
