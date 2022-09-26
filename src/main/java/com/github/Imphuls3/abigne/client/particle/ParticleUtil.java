package com.github.Imphuls3.abigne.client.particle;

import java.util.concurrent.ThreadLocalRandom;

public class ParticleUtil {
    public static double inRange(double min, double max){
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
    public static ParticleColor defaultParticleColor(){
        return new ParticleColor(255, 25, 180);
    }

    public static ParticleColor.IntWrapper defaultParticleColorWrapper(){
        return new ParticleColor.IntWrapper(255, 25, 180);
    }
}
