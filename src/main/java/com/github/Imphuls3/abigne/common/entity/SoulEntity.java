package com.github.Imphuls3.abigne.common.entity;

import com.github.Imphuls3.abigne.core.blockentity.ExtendedItemStackHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SoulEntity extends PathfinderMob implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public ExtendedItemStackHandler inventory = new ExtendedItemStackHandler(1, 64) {
        @Override
        public void onContentsChanged(int slot)  {
            super.onContentsChanged(slot);
        }
    };

    public SoulEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.navigation = this.createNavigation(level);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.ATTACK_DAMAGE, 3F)
                .add(Attributes.ATTACK_SPEED, 2F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FLYING_SPEED, 0.6F)
                .add(Attributes.FOLLOW_RANGE, Integer.MAX_VALUE/16D)
                .build();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        final var navigation = new GroundPathNavigation(this, level);
        return navigation;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BlockItem item) {
            ItemStack stack = new ItemStack(item);
            this.inventory.setStackInSlot(0, stack);
        }
        return super.mobInteract(player, hand);
    }

    boolean run = true;
    int k = 0;
    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        /*if(!event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.soul.idle"));
        }*/
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean save(CompoundTag nbt) {
        inventory.save(nbt);
        return super.save(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt);
        super.load(nbt);
    }
}
