package com.Imphuls3.abigne.common.item;

import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import vazkii.patchouli.api.PatchouliAPI;

import javax.annotation.Nonnull;
import java.util.List;

public class PhilosophiaItem extends Item {
    public PhilosophiaItem(Properties properties) {
        super(properties);
    }

    public static boolean isOpen() {
        return Registry.ITEM.getKey(ItemInit.PHILOSOPHIA.get()).equals(PatchouliAPI.get().getOpenBookGui());
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);

        if (playerIn instanceof ServerPlayer player) {
            PatchouliAPI.get().openBookGUI((ServerPlayer) playerIn, Registry.ITEM.getKey(this));
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    public static Component getTitle(ItemStack stack) {
        Component title = stack.getHoverName();
        return title;
    }

    public static BlockHitResult doRayTrace(Level world, Player player, ClipContext.Fluid fluidMode) {
        return Item.getPlayerPOVHitResult(world, player, fluidMode);
    }
}
