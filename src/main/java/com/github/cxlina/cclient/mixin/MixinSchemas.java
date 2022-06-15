package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.util.FastDataFixerBuilder;
import com.mojang.datafixers.DataFixerBuilder;
import net.minecraft.datafixer.Schemas;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Schemas.class)
public class MixinSchemas {

    /*
    @Redirect(method = "create", at = @At(value = "NEW", target = "com/mojang/datafixers/DataFixerBuilder"))
    private static DataFixerBuilder cclient$replaceDataFixer(int i) {
        return new FastDataFixerBuilder(i);
    }

     */
}
