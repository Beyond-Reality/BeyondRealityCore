package com.mcbeyondreality.beyondrealitycore.IGW;

import igwmod.api.WikiRegistry;

public class IGWHandler
{
    public static void init()
    {
        WikiRegistry.registerWikiTab(new BeyondRealityTab());
    }
}