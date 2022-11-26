package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.fluid.BaseFluidType;
import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

import static com.github.Imphuls3.abigne.AbIgne.modPath;

public class FluidTypeRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister
            .create(ForgeRegistries.Keys.FLUID_TYPES, AbIgne.MODID);

    public static final RegistryObject<BaseFluidType> MOLTEN_ALUMINUM = registerMoltenFluid("silver", new Color(78, 136, 199), false);

    private static RegistryObject<BaseFluidType> registerMoltenFluid(String name, Color color, boolean tint) {
        return FLUID_TYPES.register("molten_" + name + "_type", () -> new BaseFluidType(FluidType.Properties.create().viscosity(6000),
                modPath("block/fluid/molten_" + name + "_still"), modPath("block/fluid/molten_" + name + "_flow"),
                modPath("block/fluid/molten_" + name + "_still"), tint ? color.getRGB() : Color.WHITE.getRGB(),
                new Vector3f(color.getRed(), color.getGreen(), color.getBlue())));
    }

    private static RegistryObject<BaseFluidType> registerFluid(String name, Color color, boolean tint) {
        return FLUID_TYPES.register(name + "_type", () -> new BaseFluidType(FluidType.Properties.create(),
                modPath("block/fluid/" + name + "_still"), modPath("block/fluid/" + name + "_flow"),
                modPath("block/fluid/" + name + "_still"), tint ? color.getRGB() : Color.WHITE.getRGB(),
                new Vector3f(color.getRed(), color.getGreen(), color.getBlue())));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
