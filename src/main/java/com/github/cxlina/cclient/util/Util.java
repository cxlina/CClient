package com.github.cxlina.cclient.util;

import net.minecraft.client.MinecraftClient;

public class Util {

    private static long timeLastFrameMs;
    private static long averageFrameTimeMs;

    public static String getFormattedDimensionName() {
        return switch (MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath()) {
            case "overworld" -> "Overworld";
            case "the_nether" -> "Nether";
            case "the_end" -> "The End";
            default -> MinecraftClient.getInstance().world.getRegistryKey().getValue().toString();
        };
    }

    public static void frameStart() {
        long i = System.currentTimeMillis();
        long j = i - timeLastFrameMs;
        timeLastFrameMs = i;
        averageFrameTimeMs = (averageFrameTimeMs + j) / 2L;
        averageFrameTimeMs = limit(averageFrameTimeMs, 1L, 1000L);
    }

    public static float getAverageFrameTimeSec() {
        return (float) getAverageFrameTimeMs() / 1000.0F;
    }

    public static long getAverageFrameTimeMs() {
        return averageFrameTimeMs;
    }

    public static float limit(float f, float f1, float f2) {
        if (f < f1) {
            return f1;
        } else return Math.min(f, f2);
    }

    public static long limit(long l, long l1, long l2) {
        if (l < l1) {
            return l1;
        } else return Math.min(l, l2);
    }
}
