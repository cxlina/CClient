package com.github.cxlina.cclient.util;

import net.minecraft.client.MinecraftClient;

public class Util {

    public static String getFormattedDimensionName() {
        return switch (MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath()) {
            case "overworld" -> "Overworld";
            case "the_nether" -> "Nether";
            case "the_end" -> "The End";
            default -> MinecraftClient.getInstance().world.getRegistryKey().getValue().toString();
        };
    }
}
