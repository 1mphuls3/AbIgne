package com.Imphuls3.abigne.common.item.wand;

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

public class Wand extends Item {
    public int tier;
    public int capacity;
    public int transferRate;
    public Item handle;
    public Item end;
    public Item extra;

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
        if(player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof WandPart part){

            inv.setStackInSlot(0, part.getDefaultInstance());

            if(part.getPartType().equals("handle") && handle == null){
                handle = part;

                player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);

                capacity += part.getCapacity();
                transferRate += part.getTransferRate();
            } else if(part.getPartType().equals("end") && end == null){
                end = part;

                player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);

                capacity += part.getCapacity();
                transferRate += part.getTransferRate();
            } else if(part.getPartType().equals("extra") && extra == null){
                extra = part;

                player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);

                capacity += part.getCapacity();
                transferRate += part.getTransferRate();
            }
/*            int[] tiers = {handle.getTier(), end.getTier(), extra.getTier()};
            tier = MathHelper.largestNum(tiers);*/
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        TranslatableComponent tierTxt = new TranslatableComponent("text.abigne.tier");
        tooltipComponents.add(new TextComponent("Tier: " + (tier)).withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltipComponents.add(new TextComponent("Capacity: " + (capacity)).withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(new TextComponent("Transfer Rate: " + (transferRate)).withStyle(ChatFormatting.GOLD));
/*        tooltipComponents.add(new TextComponent("Parts: " + (Objects.requireNonNull(handle.getRegistryName()).toString())).withStyle(ChatFormatting.LIGHT_PURPLE));*/
    }
/*
    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
               return ;
            }
        });
    }*/
}
