package com.sarinsa.funnier_death.common.util.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sarinsa.funnier_death.common.core.config.FDClientConfig;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ClientMixinWork {

    public static <T extends LivingEntity> float getRendererFlipDegrees(float original, T livingEntity) {
        if (FDClientConfig.CLIENT.deathFlipDegreesEnabled()) {
            if (FDClientConfig.CLIENT.randomizeDegreesEnabled()) {
                return ((RandomFlipDegrees) livingEntity).getRandomFlipDegrees();
            }
            return FDClientConfig.CLIENT.getDeathFlipDegrees();
        }
        return original;
    }

    public static <T extends LivingEntity> void onScale(T entity, PoseStack poseStack, float partialTick, CallbackInfo ci) {
        if (FDClientConfig.CLIENT.scaleEnabled()) {

            if (entity.deathTime > 0) {
                float f = ((float) entity.deathTime + partialTick - 1.0F) / 20.0F * 1.6F;

                f = Mth.sqrt(f);

                if (f > 1.0F) {
                    f = 1.0F;
                }
                float scale = f * FDClientConfig.CLIENT.getScale();

                poseStack.scale(scale, scale, scale);

                if (entity.isDeadOrDying())
                    ci.cancel();
            }
        }
    }
}
