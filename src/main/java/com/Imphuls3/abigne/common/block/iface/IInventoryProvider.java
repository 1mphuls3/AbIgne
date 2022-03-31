package com.Imphuls3.abigne.common.block.iface;

import com.Imphuls3.abigne.common.inventory.ModBlockEntityInventory;
import net.minecraft.world.phys.Vec3;

public interface IInventoryProvider {
    public ModBlockEntityInventory providedInv();
    public Vec3 providedIPos();
}
