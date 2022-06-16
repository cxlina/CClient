package com.github.cxlina.cclient.mixin;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SharedConstants.class)
public class MixinSharedConstants {

    /**
     * @author Cxlina
     * @reason Doing the work for mojang i guess?
     */
    @Overwrite
    public static void method_43250() {
    }
}
