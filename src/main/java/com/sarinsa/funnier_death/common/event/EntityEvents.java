package com.sarinsa.funnier_death.common.event;

import com.sarinsa.funnier_death.common.core.FunnierDeath;
import com.sarinsa.funnier_death.common.core.config.FDClientConfig;
import com.sarinsa.funnier_death.common.util.mixin.RandomFlipDegrees;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class EntityEvents {

    public static boolean LOGGED_ERROR = false;

    /**
     * Calculates random death flip degrees for
     * living entities if random degrees is enabled
     * in the client config.
     */
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!event.getWorld().isClientSide) return;

        if (event.getEntity() instanceof LivingEntity livingEntity) {

            if (FDClientConfig.CLIENT.randomizeDegreesEnabled()) {
                float maxDegrees = FDClientConfig.CLIENT.getMaxRandomDegrees();
                float minDegrees = FDClientConfig.CLIENT.getMinRandomDegrees();

                if (maxDegrees <= minDegrees && !LOGGED_ERROR) {
                    FunnierDeath.LOGGER.error("Invalid max or min random flip degrees value specified in client config.");
                    LOGGED_ERROR = true;
                    return;
                }

                try {
                    Random random = event.getWorld().getRandom();
                     float randomDegrees = minDegrees + random.nextInt((int) maxDegrees - (int) minDegrees);
                    ((RandomFlipDegrees) livingEntity).setRandomFlipDegrees(random.nextBoolean() ? randomDegrees : -randomDegrees);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
