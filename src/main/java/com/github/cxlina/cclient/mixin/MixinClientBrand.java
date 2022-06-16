package com.github.cxlina.cclient.mixin;

import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.obfuscate.DontObfuscate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * I don't want to hide anything, so i change the client-brand.
 *
 * @author Cxlina
 */

public class MixinClientBrand {
    @Mixin(ClientBrandRetriever.class)
    public static class MixinClientBrandRetriever {
        /**
         * @author Cxlina
         * @reason Add own brand, because i don't intend to hide anything.
         */
        @DontObfuscate
        @Overwrite(remap = false)
        public static String getClientModName() {
            return "CClient";
        }
    }
}
