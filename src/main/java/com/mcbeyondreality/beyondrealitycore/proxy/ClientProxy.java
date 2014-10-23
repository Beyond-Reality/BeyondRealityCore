package com.mcbeyondreality.beyondrealitycore.proxy;

import cpw.mods.fml.common.event.FMLInterModComms;

public class ClientProxy extends CommonProxy {

    @Override
    public void register()
    {
        FMLInterModComms.sendMessage("IGWMod", "com.mcbeyondreality.beyondrealitycore.IGW.IGWHandler", "init");
    }

}
