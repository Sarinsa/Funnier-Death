package com.sarinsa.funnier_death.common.core;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FunnierDeath.MODID)
public class FunnierDeath {

    public static final String MODID = "funnier_death";


    public FunnierDeath() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
