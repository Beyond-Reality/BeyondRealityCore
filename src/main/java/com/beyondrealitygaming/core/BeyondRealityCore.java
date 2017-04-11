package com.beyondrealitygaming.core;

import com.beyondrealitygaming.core.item.BRItem;
import com.beyondrealitygaming.core.material.BRMaterial;
import com.beyondrealitygaming.core.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = "Beyond Reality Core", modid = "beyondreality", version = "@VERSION@")


public class BeyondRealityCore {

    @Mod.Instance("beyondreality")
    public static BeyondRealityCore instance;

    @SidedProxy(clientSide = "com.beyondrealitygaming.core.proxy.ClientProxy", serverSide = "com.beyondrealitygaming.core.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BRMaterial.registerMaterials();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
