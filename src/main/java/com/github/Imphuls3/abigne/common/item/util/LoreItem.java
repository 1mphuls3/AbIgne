package com.github.Imphuls3.abigne.common.item.util;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LoreItem extends Item implements ILoreItem {
    private final String lore;
    public LoreItem(Properties properties, String lore) {
        super(properties);
        this.lore = lore;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag advanced) {
        components.add(Component.translatable(this.getDescriptionId().replace("item", "lore")).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_PURPLE));
        super.appendHoverText(stack, level, components, advanced);
    }

    public String getLore() {
        return lore;
    }
}
