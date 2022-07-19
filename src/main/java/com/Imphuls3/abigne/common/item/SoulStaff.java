package com.Imphuls3.abigne.common.item;

import com.Imphuls3.abigne.common.entity.SoulEntity;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulStaff extends Item {
    SoulEntity soul;

    public SoulStaff(Properties properties) {
        super(properties);
    }

    public void setSoul(SoulEntity soul, Player player, InteractionHand hand) {
        this.soul = soul;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        CompoundTag tag = stack.getOrCreateTag();
        if (!player.isCrouching()) {
            HitResult hit = player.pick(6, 0, false);
            /*List<ItemEntity> items = level.getEntities(ItemEntity, new AABB(5D, 5D, 5D, 0D, 0D, 0D));*/
            tag.putString("item_aabb", hit.getLocation().toString());
            TextComponent tooltipComponent = new TextComponent("");
        }
        return InteractionResultHolder.pass(stack);
    }
}
