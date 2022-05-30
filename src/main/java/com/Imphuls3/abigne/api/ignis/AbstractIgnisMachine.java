package com.Imphuls3.abigne.api.ignis;

import com.Imphuls3.abigne.common.block.entity.utils.ModBlockEntity;
import com.Imphuls3.abigne.common.block.entity.utils.ModInventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractIgnisMachine extends ModBlockEntity implements IIgnis {
    private int ignis = 0;
    private int maxIgnis = 0;
    public static String IGNIS_TAG = "ignis";
    public static String MAX_IGNIS_TAG ="max_ignis";

    public AbstractIgnisMachine(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void load(CompoundTag nbt) {
        ignis = nbt.getInt(IGNIS_TAG);
        maxIgnis = nbt.getInt(MAX_IGNIS_TAG);
        super.load(nbt);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putInt(IGNIS_TAG, getIgnis());
        nbt.putInt(MAX_IGNIS_TAG, getMaxIgnis());
    }

    @Override
    public int setIgnis(int ignis) {
        this.ignis = ignis;
        if(this.ignis > this.getMaxIgnis())
            this.ignis = this.getMaxIgnis();
        if(this.ignis < 0)
            this.ignis = 0;
        update();
        return this.ignis;
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
        update();
        return this.getIgnis();
    }

    @Override
    public void setMaxIgnis(int max) {
        this.maxIgnis = max;
        update();
    }

    public boolean update(){
        if(this.worldPosition != null && this.level != null){
            level.sendBlockUpdated(this.worldPosition, level.getBlockState(worldPosition),  level.getBlockState(worldPosition), 2);
            return true;
        }
        return false;
    }

    @Override
    public int getMaxIgnis() {
        return maxIgnis;
    }

    public boolean canAcceptIgnis() {
        return this.getIgnis() < this.getMaxIgnis();
    }

    public boolean canAcceptIgnis(int ignis) {
        return this.getIgnis() + ignis <= this.getMaxIgnis();
    }

    /**
     * Transfers the maximum possible amount of ignis from one tile to another.
     * Takes the maximum transfer rate of the two tiles into account, and the space remaining.
     * @return The amount of ignis that was transferred.
     */
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
}
