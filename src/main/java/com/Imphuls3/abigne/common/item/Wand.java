package com.Imphuls3.abigne.common.item;

import com.Imphuls3.abigne.common.block.entity.utils.ModInventory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Wand extends Item {
    int tier;
    int capacity;
    int regen;
    WandPart handle;
    WandPart end;
    WandPart extra;

    ModInventory inv;
    public Wand(Properties properties) {
        super(properties);
        inv = new ModInventory(3, 1) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
            }
        };
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof WandPart){
            WandPart part = (WandPart)player.getItemInHand(InteractionHand.OFF_HAND).getItem();
            player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
            inv.setStackInSlot(0, part.getDefaultInstance());

            if(part.getPartType() == "handle"){
                handle = part;
            }

            this.tier = part.getTier();
            this.capacity = part.getCapacity();
            this.regen = part.getRegen();
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        TranslatableComponent tierTxt = new TranslatableComponent("text.abigne.tier");
        tooltipComponents.add(new TextComponent("Tier: " + (tier)).withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltipComponents.add(new TextComponent("Capacity: " + (capacity)).withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(new TextComponent("Regen: " + (regen)).withStyle(ChatFormatting.GOLD));
/*        tooltipComponents.add(new TextComponent("Parts: " + (Objects.requireNonNull(handle.getRegistryName()).toString())).withStyle(ChatFormatting.LIGHT_PURPLE));*/
    }
}
