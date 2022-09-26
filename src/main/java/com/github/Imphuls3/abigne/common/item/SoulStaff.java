package com.github.Imphuls3.abigne.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class SoulStaff extends Item {
    public BlockPos pos;

    public SoulStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        pos = context.getClickedPos();
        return super.useOn(context);
    }
}
