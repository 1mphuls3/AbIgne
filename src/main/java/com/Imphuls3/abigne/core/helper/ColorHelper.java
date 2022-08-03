package com.Imphuls3.abigne.core.helper;

import com.Imphuls3.abigne.core.Easing;
import net.minecraft.util.Mth;

import java.awt.*;
import java.util.List;

public class ColorHelper {
    public static Color colorLerp(Easing easing, float delta, Color startColor, Color endColor) {
        delta = Mth.clamp(delta, 0, 1);
        int br = startColor.getRed(), bg = startColor.getGreen(), bb = startColor.getBlue();
        int dr = endColor.getRed(), dg = endColor.getGreen(), db = endColor.getBlue();
        float ease = easing.ease(delta, 0, 1, 1);
        int red = (int) Mth.lerp(ease, br, dr);
        int green = (int) Mth.lerp(ease, bg, dg);
        int blue = (int) Mth.lerp(ease, bb, db);
        return new Color(Mth.clamp(red, 0, 255), Mth.clamp(green, 0, 255), Mth.clamp(blue, 0, 255));
    }
    public static Color multicolorLerp(Easing easing, float delta, Color... colors) {
        return multicolorLerp(easing, delta, List.of(colors));
    }
    public static Color multicolorLerp(Easing easing, float delta, List<Color> colors) {
        delta = Mth.clamp(delta, 0, 1);
        int colorCount = colors.size() - 1;
        float lerp = easing.ease(delta, 0, 1, 1);
        float colorIndex = colorCount * lerp;
        int index = (int) Mth.clamp(colorIndex, 0, colorCount);
        Color color = colors.get(index);
        Color nextColor = index == colorCount ? color : colors.get(index + 1);
        return ColorHelper.colorLerp(easing, colorIndex - (int) (colorIndex), color, nextColor);
    }
}
