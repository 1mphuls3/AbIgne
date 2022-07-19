package com.Imphuls3.abigne.client.screen.book;

import com.Imphuls3.abigne.client.screen.book.objects.EntryObject;
import com.Imphuls3.abigne.client.screen.book.pages.BookPage;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;

public class BookEntry {
    public final ItemStack icon;
    public final String identifier;
    public final int xOffset;
    public final int yOffset;
    public ArrayList<BookPage> pages = new ArrayList<>();
    public EntryObjectSupplier objectSupplier = EntryObject::new;

    public BookEntry(String identifier, Item item, int xOffset, int yOffset) {
        this.icon = item.getDefaultInstance();
        this.identifier = identifier;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public String translationKey() {
        return "abigne.gui.book.entry." + identifier;
    }

    public String descriptionTranslationKey() {
        return "abigne.gui.book.entry." + identifier + ".description";
    }

    public BookEntry addPage(BookPage page) {
        if (page.isValid()) {
            pages.add(page);
        }
        return this;
    }

    public BookEntry addModCompatPage(BookPage page, String modId) {
        if (ModList.get().isLoaded(modId)) {
            pages.add(page);
        }
        return this;
    }
    public BookEntry setObjectSupplier(EntryObjectSupplier objectSupplier) {
        this.objectSupplier = objectSupplier;
        return this;
    }

    public interface EntryObjectSupplier {
        EntryObject getBookObject(BookEntry entry, int x, int y);
    }
}
