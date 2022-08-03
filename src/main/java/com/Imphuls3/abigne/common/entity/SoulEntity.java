package com.Imphuls3.abigne.common.entity;


import com.Imphuls3.abigne.core.systems.ignis.IIgnis;
import com.Imphuls3.abigne.core.systems.blockentity.AbIgneInventory;
import com.Imphuls3.abigne.common.entity.ai.SoulRechargeGoal;
import com.Imphuls3.abigne.common.entity.ai.SoulWanderGoal;
import com.Imphuls3.abigne.common.item.SoulStaff;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SoulEntity extends Animal implements IAnimatable, IIgnis, FlyingAnimal {

    private AnimationFactory factory = new AnimationFactory(this);

    public AbIgneInventory inventory = new AbIgneInventory(1, 64) {
        @Override
        public void onContentsChanged(int slot)  {
            super.onContentsChanged(slot);
        }
    };

    Vec3 casterPos;
    Vec3 targetPos;
    int ignis;
    int maxIgnis = 1000;

    protected PathNavigation createNavigation(Level worldIn) {
        FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation(this, worldIn) {
            public boolean isStableDestination(BlockPos pos) {
                return !this.level.getBlockState(pos.below()).isAir();
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(false);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }

    public SoulEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SoulRechargeGoal(this, 1, 32));
        this.goalSelector.addGoal(1, new SoulWanderGoal(this, 1));
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.ATTACK_DAMAGE, 3F)
                .add(Attributes.ATTACK_SPEED, 2F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FLYING_SPEED, (double)0.6F)
                .build();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(player.getItemInHand(hand).getItem() instanceof SoulStaff staff) {
            staff.setSoul(this, player, hand);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void tick() {
        super.tick();
/*        inventory.setStackInSlot(0, Items.IRON_INGOT.getDefaultInstance());
        Vec3 motion = getDeltaMovement();
        Vec3 pos = position();
        Vec3 norm = motion.normalize().scale(0.025f);
        for (int i = 0; i < 8; i ++) {
            double lerpX = Mth.lerp(i / 8.0f, xo, pos.x);
            double lerpY = Mth.lerp(i / 8.0f, yo, pos.y);
            double lerpZ = Mth.lerp(i / 8.0f, zo, pos.z);
            level.addParticle(ParticleTypes.FLAME, lerpX, lerpY, lerpZ, -norm.x, -norm.y, -norm.z);
        }*/
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
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
    public int addIgnis(int ignis) {
        return this.setIgnis(this.getIgnis() + ignis);
    }

    @Override
    public int getIgnis() {
        return this.ignis;
    }

    @Override
    public int removeIgnis(int ignis) {
        this.setIgnis(this.getIgnis() - ignis);
        return this.getIgnis();
    }

    @Override
    public void setMaxIgnis(int max) {
        this.maxIgnis = max;
    }

    @Override
    public int setIgnis(int amountSet) {
        int ret = amountSet;
        ignis = amountSet;
        return ret;
    }

    @Override
    public int getMaxIgnis() {
        return maxIgnis;
    }

    @Override
    public int getTransferRate() {
        return maxIgnis;
    }

    public boolean canAcceptIgnis() {
        return this.getIgnis() < this.getMaxIgnis();
    }

    public boolean canAcceptIgnis(int ignis) {
        return this.getIgnis() + ignis <= this.getMaxIgnis();
    }

    public int transferIgnis(IIgnis from, IIgnis to){
        int transferRate = getTransferRate(from, to);
        from.removeIgnis(transferRate);
        to.addIgnis(transferRate);
        return transferRate;
    }

    public int getTransferRate(IIgnis from, IIgnis to){
        return Math.min(Math.min(from.getTransferRate(), from.getIgnis()), to.getMaxIgnis() - to.getIgnis());
    }

    public int transferIgnis(IIgnis from, IIgnis to, int fromTransferRate){
        int transferRate = getTransferRate(from, to, fromTransferRate);
        if(transferRate == 0)
            return 0;
        from.removeIgnis(transferRate);
        to.addIgnis(transferRate);
        return transferRate;
    }

    public int getTransferRate(IIgnis from, IIgnis to, int fromTransferRate){
        return Math.min(Math.min(fromTransferRate, from.getIgnis()), to.getMaxIgnis() - to.getIgnis());
    }

    @Override
    public boolean save(CompoundTag nbt) {
        nbt.putInt("ignis", ignis);
        return super.save(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        nbt.get("ignis");
        super.load(nbt);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    @Override
    public boolean isFlying() {
        return !this.onGround;
    }
}
