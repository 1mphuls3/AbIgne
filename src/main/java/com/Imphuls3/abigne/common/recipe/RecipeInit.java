package com.Imphuls3.abigne.common.recipe;

import com.Imphuls3.abigne.AbIgne;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AbIgne.MODID);

/*    public static final RegistryObject<RecipeSerializer<InfusionRecipe>> INFUSION_SERIALIZER =
            SERIALIZERS.register("infusion", () -> InfusionRecipe.Serializer.INSTANCE);*/

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
