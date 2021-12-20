package com.sarinsa.funnier_death.common.core;

import com.sarinsa.funnier_death.common.core.config.FDClientConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FunnierDeath.MODID)
public class FunnierDeath {

    public static final String MODID = "funnier_death";

    public static final Logger LOGGER = LogManager.getLogger(MODID);


    public FunnierDeath() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::onCommonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, FDClientConfig.CLIENT_SPEC);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Please stand by while we make mobs dying mega funnie!");
    }
}
