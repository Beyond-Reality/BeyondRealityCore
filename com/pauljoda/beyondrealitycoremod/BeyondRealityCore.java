package com.pauljoda.beyondrealitycoremod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import com.pauljoda.beyondrealitycoremod.handlers.BeyondRealityCoreCapeEvent;
import com.pauljoda.beyondrealitycoremod.handlers.BeyondRealityCoreEvent;
import com.pauljoda.beyondrealitycoremod.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;


@Mod(name = "Beyond Reality Core", modid = "beyondrealitycore", version = "1.1")
//@NetworkMod(channels = { "Beyond Reality Core" },clientSideRequired=true, serverSideRequired=false)


public class BeyondRealityCore {
	
	@Instance("beyondrealitycore")
	public static BeyondRealityCore instance;
	
	@SidedProxy( clientSide="com.pauljoda.beyondrealitycoremod.proxy.ClientProxy", serverSide="com.pauljoda.beyondrealitycoremod.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static String[] bannedEnderBlocks;
	public static int aggroRange;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){	
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		bannedEnderBlocks = config.get("Settings", "Blocks the Endermen Don't want you to take", new String[] {"tile.whiteStone"}).getStringList();
		aggroRange = config.get("Settings", "Enderman Range for block breaks", 16).getInt();
		
		config.save();

		MinecraftForge.EVENT_BUS.register(new BeyondRealityCoreEvent());

		if (event.getSide() == Side.CLIENT)
        {
		MinecraftForge.EVENT_BUS.register(new BeyondRealityCoreCapeEvent());
        }
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

}
