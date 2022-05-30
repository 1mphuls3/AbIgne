package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.common.block.entity.IgnisVoidBlockEntity;
import com.Imphuls3.abigne.common.block.entity.InfuserBlockEntity;
import com.Imphuls3.abigne.common.block.utils.ModWaterLoggableBlock;
import com.Imphuls3.abigne.core.init.BlockEntityInit;

public class IgnisVoidBlock extends ModWaterLoggableBlock<IgnisVoidBlockEntity> {
    public IgnisVoidBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
        setBlockEntity(BlockEntityInit.VOID);
    }
}
