package com.Imphuls3.abigne.common.block.pipe;

import com.Imphuls3.abigne.api.ignis.AbstractIgnisMachine;
import com.Imphuls3.abigne.api.ignis.IIgnis;
import com.Imphuls3.abigne.common.block.PedestalBlock;
import com.Imphuls3.abigne.common.block.entity.PedestalBlockEntity;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.core.init.BlockInit;
import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PipeBlockEntity extends AbstractIgnisMachine {

    public PipeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.PIPE.get(), pos, state);
    }

    int num = 0;
    public void tick() {
        for(Direction facing : Direction.values()){
           BlockEntity blockEntity = level.getBlockEntity(this.getBlockPos().relative(facing));
           if(blockEntity instanceof PipeBlockEntity) {
               if(((PipeBlockEntity) blockEntity).canAcceptIgnis()){
                   this.transferIgnis(this, (PipeBlockEntity) blockEntity);
               }
           } else if(blockEntity instanceof AbstractIgnisMachine) {
               if(((AbstractIgnisMachine) blockEntity).canAcceptIgnis()){
                   this.transferIgnis(this, (AbstractIgnisMachine) blockEntity);
               }
           }
        }
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(player.getItemInHand(hand).getItem() == ItemInit.PYROLITE_SHARD.get()){
            this.addIgnis(100);
        }
        player.swing(hand);

        String ignisText = "Ignis amount: " + this.getIgnis();
        TextComponent msg = new TextComponent(ignisText);
        player.sendMessage(msg, player.getUUID());

        return InteractionResult.SUCCESS;
/*        return super.onUse(player, hand);*/
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        super.onDataPacket(net, packet);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public static ConnectionType getConnection(BlockGetter world, BlockPos pos, Direction face) {
        return getConnection(world, pos, face, false);
    }

    private static ConnectionType getConnection(BlockGetter world, BlockPos pos, Direction face, boolean recursed) {
        BlockPos truePos = pos.relative(face);

        if(world.getBlockState(truePos).getBlock() instanceof WorldlyContainerHolder)
            return ConnectionType.MACHINE;

        BlockEntity tile = world.getBlockEntity(truePos);

        if(tile != null) {
            if(tile instanceof PipeBlockEntity)
                return ConnectionType.PIPE;
            else if(tile instanceof AbstractIgnisMachine /*|| tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite()).isPresent()*/)
                return tile instanceof AbstractIgnisMachine ? ConnectionType.MACHINE_OFFSET : ConnectionType.MACHINE;
        }

        return ConnectionType.NONE;
    }
    @Override
    public int transferIgnis(IIgnis from, IIgnis to){
        int transferRate = getTransferRate(from, to);
        from.removeIgnis(transferRate);
        to.addIgnis(transferRate);
        return transferRate;
    }

    @Override
    public int getTransferRate() {
        return this.getMaxIgnis();
    }

    @Override
    public int getMaxIgnis() {
        int maxIgnis = 500;
        return maxIgnis;
    }

    public enum ConnectionType {

        NONE(false, false, false, 0),
        PIPE(true, true, false, 0),
        MACHINE(true, true, true, 0.125),
        MACHINE_OFFSET(true, true, true, 0.1875);

        ConnectionType(boolean isSolid, boolean allowsItems, boolean isFlared, double flareShift) {
            this.isSolid = isSolid;
            this.allowsItems = allowsItems;
            this.isFlared = isFlared;
            this.flareShift = flareShift;
        }

        public final boolean isSolid, allowsItems, isFlared;
        public final double flareShift;

    }


}
