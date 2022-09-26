package com.github.Imphuls3.abigne.common.item.util;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LoreItem extends Item {
    private final String langName;

    public LoreItem(Properties properties, String langName) {
        super(properties);
        this.langName = langName;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag advanced) {
        components.add(new TranslatableComponent("item." + AbIgne.MODID + ".lore." + langName).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_PURPLE));
        super.appendHoverText(stack, level, components, advanced);
    }
}
