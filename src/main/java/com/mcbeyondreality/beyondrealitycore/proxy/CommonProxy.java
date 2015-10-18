package com.mcbeyondreality.beyondrealitycore.proxy;

import com.mcbeyondreality.beyondrealitycore.tiles.TileDimGuardKiller;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void register() {

        GameRegistry.registerTileEntity(TileDimGuardKiller.class, "BRC:DimGuardFixer");

    }

    public void registerHandlers() {

    }

}
