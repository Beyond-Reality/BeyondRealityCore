package com.beyondrealitygaming.core;


import com.beyondrealitygaming.core.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.IOException;


@Mod(name = "Beyond Reality Core", modid = "beyondreality", version = "2.0.2")
public class BeyondRealityCore {

    @Mod.Instance("beyondreality")
    public static BeyondRealityCore instance;

    @SidedProxy(clientSide = "com.beyondrealitygaming.core.proxy.ClientProxy", serverSide = "com.beyondrealitygaming.core.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) throws IOException {
        proxy.postInit();
//        if (true){
//            System.out.println("-----------------------------------Loading BC Recipes");
//            BuildcraftRecipeRegistry.assemblyRecipes.addRecipe(new AssemblyRecipe("test", 1000, ImmutableSet.<StackDefinition>builder().add(ArrayStackFilter.definition(Items.SADDLE)).build() ,new ItemStack(Items.STICK)));
//        }
    }

}
