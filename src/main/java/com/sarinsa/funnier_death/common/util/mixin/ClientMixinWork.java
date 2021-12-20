package com.sarinsa.funnier_death.common.util.mixin;

import com.sarinsa.funnier_death.common.core.config.FDClientConfig;

public class ClientMixinWork {

    public static float getRendererFlipDegrees(float original) {
        if (FDClientConfig.CLIENT.deathFlipDegreesEnabled()) {
            return FDClientConfig.CLIENT.getDeathFlipDegrees();
        }
        return original;
    }
}
