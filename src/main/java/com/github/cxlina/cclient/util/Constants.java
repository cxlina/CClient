package com.github.cxlina.cclient.util;

import net.fabricmc.loader.impl.FabricLoaderImpl;

public class Constants {

    public static String CLIENT_VERSION = "v1.1.0" + (FabricLoaderImpl.INSTANCE.isDevelopmentEnvironment() ? "_dev" : "");
}
