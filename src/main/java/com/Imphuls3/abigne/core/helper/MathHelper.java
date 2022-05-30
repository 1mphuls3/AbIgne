package com.Imphuls3.abigne.core.helper;

import net.minecraft.util.Mth;

public class MathHelper {
    public static float pythagorean(float a, float b){
        float a2 = a*a;
        float b2 = b*b;
        
        return Mth.sqrt(a2+b2);
    }

    public static float pythagorean3D(float a, float b, float c){
        float diag1 = pythagorean(a, b);
        
        return pythagorean(diag1, c);
    }
}
