package com.github.Imphuls3.abigne.core.data;

import com.github.Imphuls3.abigne.common.item.util.ILoreItem;
import com.github.Imphuls3.abigne.common.item.util.LoreItem;
import com.github.Imphuls3.abigne.core.registry.BlockRegistry;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class EnUsProvider extends LanguageProvider {
    public EnUsProvider(DataGenerator generator, String modid) {
        super(generator, modid, "en_us");
    }

    @Override
    protected void addTranslations() {
        Set<RegistryObject<Block>> blocks = new HashSet<>(BlockRegistry.BLOCKS.getEntries());
        Set<RegistryObject<Item>> items = new HashSet<>(ItemRegistry.ITEMS.getEntries());
        add("itemGroup.abigne", "AbIgne");

        blocks.forEach(b -> {
            String name = b.get().getDescriptionId().replaceFirst("block.abigne.", "");
            name = name.replace('_', ' ');
            name = capitalizeWords(name);
            add(b.get().getDescriptionId(), name);
        });
        items.forEach(i -> {
            if(!(i.get() instanceof BlockItem)) {
                String name = i.get().getDescriptionId().replaceFirst("item.abigne.", "");
                name = capitalizeWords(name);
                add(i.get().getDescriptionId(), name);
                if(i.get() instanceof ILoreItem lore) {
                    add(i.get().getDescriptionId().replace("item", "lore"), lore.getLore());
                }
            }
        });
        add("curio.abigne.ringheader", "When worn as ring:");
        add("curio.abigne.charmheader", "When worn as charm:");
        add("curio.abigne.beltheader", "When worn as belt:");
        add("curio.abigne.necklaceheader", "When worn as necklace:");
    }

    public String capitalizeWords(String string) {
        string = string.replace('_', ' ');
        String[] array = string.split(" ");
        String ret = "";
        for(String str : array) {
            if(str != "of") {
                String cap = str.substring(0, 1).toUpperCase();
                str = cap + str.substring(1);
                ret = ret + "" + str + " ";
            }
        }
        return ret.substring(0, ret.length()-1);
    }
}