package com.github.cxlina.cclient.mixin;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityModelLayers.class)
public interface IMixinEntityModelLayers {
    @Invoker("registerMain")
    static EntityModelLayer cclient$registerMain(String id) {
        throw new AssertionError();
    }
}