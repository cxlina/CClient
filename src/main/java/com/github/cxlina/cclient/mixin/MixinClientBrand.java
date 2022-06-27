package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.util.Constants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.obfuscate.DontObfuscate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

public class MixinClientBrand {
    @Mixin(ClientBrandRetriever.class)
    public static class MixinClientBrandRetriever {
        /**
         * @author Cxlina
         * @reason Add own brand.
         */
        @DontObfuscate
        @Overwrite(remap = false)
        public static String getClientModName() {
            return "Fabric [CClient 1.19 " + Constants.CLIENT_VERSION + "]";
        }
    }
}
