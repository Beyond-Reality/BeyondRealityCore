package com.mcbeyondreality.beyondrealitycore;

import com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = "Beyond Reality Core", modid = "beyondrealitycore", version = "@VERSION@")


public class BeyondRealityCore {


    @Mod.Instance("beyondrealitycore")
    public static BeyondRealityCore instance;

    @SidedProxy(clientSide = "com.mcbeyondreality.beyondrealitycore.proxy.ClientProxy", serverSide = "com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
