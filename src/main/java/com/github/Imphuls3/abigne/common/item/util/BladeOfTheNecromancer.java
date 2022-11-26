package com.github.Imphuls3.abigne.common.item.util;

import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class BladeOfTheNecromancer extends LargeSwordItem implements ILoreItem {
    private final float attackSpeed;
    public float dmgAddition;
    public int dmgTick;

    public BladeOfTheNecromancer(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.attackSpeed = attackSpeed;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(getUUID(stack), "Weapon Modifier", this.getDamage()+dmgAddition, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon Modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(entity instanceof LivingEntity livingEntity && livingEntity.getMobType() != MobType.UNDEAD) {
            /*if(player.getItemInHand(InteractionHand.MAIN_HAND) == ItemRegistry.BLADE_OF_THE_NECROMANCER.get().getDefaultInstance()) {
                BladeOfTheNecromancer blade = (BladeOfTheNecromancer)player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
                blade.dmgAddition += livingEntity.getMaxHealth()/3F;
                blade.dmgTick += 40;
            }*/
            this.dmgAddition += (this.getDamage()+dmgAddition)/livingEntity.getArmorValue();
            this.dmgTick += 40;
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean selected) {
        if(this.dmgAddition > 0) {
            this.dmgTick -= 1;
            if(this.dmgAddition <= 1) {
                this.dmgAddition = 0;
            } else if(this.dmgTick % 20 == 0) {
                this.dmgAddition -= 1;
            }
        }
        if(this.dmgTick == 0 && this.dmgAddition != 0) {
            this.dmgAddition = 0;
        }
        super.inventoryTick(stack, level, entity, slotId, selected);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag advanced) {
        components.add(Component.translatable(this.getDescriptionId().replace("item", "lore")).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_RED));
        super.appendHoverText(stack, level, components, advanced);
    }

    @Override
    public String getLore() {
        return "It longs for vengeance.";
    }

    public static UUID getUUID(ItemStack stack) {
        var tag = stack.getOrCreateTag();
        if (!tag.hasUUID("uuid")) {
            UUID uuid = UUID.randomUUID();
            tag.putUUID("uuid", uuid);
        }
        return tag.getUUID("uuid");
    }
}
