package com.mcbeyondreality.beyondrealitycore.proxy;

import com.mcbeyondreality.beyondrealitycore.renderer.ChairRenderer;
import com.mcbeyondreality.beyondrealitycore.tileentity.TileEntityChair;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInterModComms;

public class ClientProxy extends CommonProxy {

    @Override
    public void register() {
        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChair.class, new ChairRenderer());
    }

    @Override
    public void registerHandlers()
    {
        FMLInterModComms.sendMessage("IGWMod", "com.mcbeyondreality.beyondrealitycore.IGW.IGWHandler", "init");
    }
}
