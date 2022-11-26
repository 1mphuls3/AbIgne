package com.github.Imphuls3.abigne.common.item;

import com.github.Imphuls3.abigne.common.item.util.ILoreItem;
import com.github.Imphuls3.abigne.common.item.util.ModCurioItem;
import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

@Mod.EventBusSubscriber
public class SunstoneRing extends Item implements ILoreItem, ICurioItem {
    public static final List<DamageSource> DAMAGE_SOURCES = ImmutableList.of(DamageSource.LAVA, DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.HOT_FLOOR);

    public SunstoneRing(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onEntityDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player) {
            if (DAMAGE_SOURCES.contains(source)) {
                event.setCanceled(true);
                return;
            }
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag unused) {
        return new ModCurioItem(stack) {
            @Override
            public List<Component> getAttributesTooltip(List<Component> tooltips) {
                tooltips.add(Component.literal(""));
                tooltips.add(Component.translatable("curio.abigne.ringheader").withStyle(ChatFormatting.GOLD));
                tooltips.add(Component.translatable("lore.abigne.sunstone_ring").withStyle(ChatFormatting.BLUE));
                return super.getAttributesTooltip(tooltips);
            }

            @Override
            public boolean canEquipFromUse(SlotContext slotContext) {
                return true;
            }
        };
    }

    @Override
    public String getLore() {
        return "Gain immunity to fire and lava damage";
    }
}
