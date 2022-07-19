package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.common.block.entity.IgnisVoidBlockEntity;
import com.Imphuls3.abigne.common.block.utils.ModBlock;
import com.Imphuls3.abigne.core.init.BlockEntityInit;

public class IgnisVoidBlock extends ModBlock<IgnisVoidBlockEntity> {
    public IgnisVoidBlock(Properties properties)
    {
        super(properties);
        setBlockEntity(BlockEntityInit.VOID);
    }
}
