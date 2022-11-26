package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class FluidRegistry {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister
            .create(ForgeRegistries.FLUIDS, AbIgne.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AbIgne.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_MOLTEN_SILVER = FLUIDS.register("molten_alchemical_silver_fluid",
            () -> new ForgeFlowingFluid.Source(FluidRegistry.MOLTEN_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_MOLTEN_SILVER = FLUIDS.register("flowing_alchemical_molten_silver",
            () -> new ForgeFlowingFluid.Flowing(FluidRegistry.MOLTEN_PROPERTIES));

    public static final RegistryObject<LiquidBlock> MOLTEN_SILVER = BLOCKS.register("molten_alchemical_silver_block",
            () -> new LiquidBlock(FluidRegistry.SOURCE_MOLTEN_SILVER, BlockBehaviour.Properties.copy(Blocks.LAVA)));

    public static final ForgeFlowingFluid.Properties MOLTEN_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeRegistry.MOLTEN_ALUMINUM, SOURCE_MOLTEN_SILVER, FLOWING_MOLTEN_SILVER)
            .slopeFindDistance(4).levelDecreasePerBlock(2).block(FluidRegistry.MOLTEN_SILVER).bucket(ItemRegistry.MOLTEN_SILVER_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
