package com.github.Imphuls3.abigne.common.item;

import com.github.Imphuls3.abigne.core.registry.EntityRegistry;
import com.github.Imphuls3.abigne.common.entity.LifeStealProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireBallCaster extends Item {

    public FireBallCaster(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!player.swinging) {
            if (!level.isClientSide) {
                Vec3 pos = player.position().add(player.getLookAngle().scale(0.5)).add(0.5 * Math.sin(Math.toRadians(225 - player.yHeadRot)), player.getBbHeight() * 2 / 3, 0.5 * Math.cos(Math.toRadians(225 - player.yHeadRot)));
                Vec3 vel = player.getEyePosition(0).add(player.getLookAngle().scale(40)).subtract(pos).scale(1.0 / 20);
                level.addFreshEntity(new LifeStealProjectile(EntityRegistry.FIREBALL.get(), level).shoot(
                        pos.x, pos.y, pos.z, vel.x, vel.y, vel.z, player.getUUID()
                ));
                stack.hurtAndBreak(1, player, (entity) -> {
                    entity.broadcastBreakEvent(hand);
                });
            }
            player.swing(hand);
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }
}
