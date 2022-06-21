package com.github.cxlina.cclient.util;

import net.fabricmc.loader.impl.FabricLoaderImpl;

public class Constants {

    public static String CLIENT_VERSION = "1.1.0_" + (FabricLoaderImpl.INSTANCE.isDevelopmentEnvironment() ? "development" : "release");
}
