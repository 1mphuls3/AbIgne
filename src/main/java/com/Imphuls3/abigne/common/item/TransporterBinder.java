package com.Imphuls3.abigne.common.item;

import com.Imphuls3.abigne.common.block.entity.ItemTransporterBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class TransporterBinder extends Item {

    public TransporterBinder(Properties properties) {
        super(properties);
    }

    static BlockPos pos;
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if(player.isCrouching()){
            pos = context.getClickedPos();
            String boundText = "Binding to block at: " + pos.getX() + ", " + + pos.getY() + ", " + + pos.getZ();
            TextComponent boundMsg = new TextComponent(boundText);
            player.sendMessage(boundMsg, player.getUUID());
        }
        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!player.isCrouching()){
            pos = BlockPos.ZERO;
            String zeroText = "Reset Bound Block";
            TextComponent zeroMsg = new TextComponent(zeroText);
            player.sendMessage(zeroMsg, player.getUUID());
        }
        return super.use(level, player, hand);
    }

    public static BlockPos getPos(){
        return pos;
    }
}
