package com.Imphuls3.abigne.client.screen.book.objects;

import com.Imphuls3.abigne.client.screen.book.BookEntry;
import com.Imphuls3.abigne.client.screen.book.EntryScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.Arrays;

import static com.Imphuls3.abigne.client.screen.book.BookScreen.*;

public class EntryObject extends BookObject{
    public final BookEntry entry;
    public EntryObject(BookEntry entry, int posX, int posY)
    {
        super(posX, posY, 32, 32);
        this.entry = entry;
    }

    @Override
    public void click(float xOffset, float yOffset, double mouseX, double mouseY) {
        EntryScreen.openScreen(this);
    }

    @Override
    public void render(Minecraft minecraft, PoseStack stack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
        int posX = offsetPosX(xOffset);
        int posY = offsetPosY(yOffset);
        renderTransparentTexture(FADE_TEXTURE, stack, posX-13, posY-13, 1, 252, 58, 58, 512, 512);
        renderTexture(FRAME_TEXTURE, stack, posX, posY, 1, 252, width, height, 512, 512);
        minecraft.getItemRenderer().renderAndDecorateItem(entry.icon, posX + 8, posY + 8);
    }

    @Override
    public void lateRender(Minecraft minecraft, PoseStack stack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
        if (isHovering) {
            screen.renderComponentTooltip(stack, Arrays.asList(new TranslatableComponent(entry.translationKey()), new TranslatableComponent(entry.descriptionTranslationKey()).withStyle(ChatFormatting.GRAY)), mouseX, mouseY, minecraft.font);
        }
    }
}
