package com.github.Imphuls3.abigne.client.event;

import com.github.Imphuls3.abigne.common.item.util.BladeOfTheNecromancer;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber
public class ModEventBus {
    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event) {
        /*if(event.getSource().getEntity() instanceof LivingEntity entity) {
            if(entity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BladeOfTheNecromancer blade) {
                if (event.getEntity() instanceof Mob mob && mob.getMobType() != MobType.UNDEAD) {
                    blade.dmgAddition += 1;
                    blade.dmgTick += 40;
                }
            }
        }*/
    }

    @SubscribeEvent
    public static void onEntityDamaged(LivingDamageEvent event) {
        DamageSource damageSource = event.getSource();
        Entity source = event.getSource().getEntity();
        LivingEntity target = event.getEntity();

        if (source instanceof Player player) {
            if(!CuriosApi.getCuriosHelper().findCurios(player, ItemRegistry.MOONSTONE_RING.get()).isEmpty()) {
                if(target.getMobType() == MobType.UNDEAD) {
                    event.setAmount(event.getAmount()*1.5F);
                }
            }

        }
    }
}
