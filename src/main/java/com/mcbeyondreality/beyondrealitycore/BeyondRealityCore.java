package com.mcbeyondreality.beyondrealitycore;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import com.mcbeyondreality.beyondrealitycore.handlers.BeyondRealityCoreEvent;
import com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(name = "Beyond Reality Core", modid = "beyondrealitycore", version = "1.3")


public class BeyondRealityCore {
	
	@Instance("beyondrealitycore")
	public static BeyondRealityCore instance;
	
	@SidedProxy( clientSide="com.mcbeyondreality.beyondrealitycore.proxy.ClientProxy", serverSide="com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static String[] bannedEnderBlocks, bannedNetherBlocks;
	public static int aggrorangeEnd, aggrorangeNether;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){	
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		bannedEnderBlocks = config.get("End Settings", "Blocks the Endermen Don't want you to take", new String[] {"minecraft:end_stone", "gregtech:gt.blockores"}).getStringList();
		aggrorangeEnd = config.get("End Settings", "Enderman Range for block breaks", 16).getInt();
		
		bannedNetherBlocks = config.get("Nether Settings", "Blocks the Pigmen Don't want you to take", new String[] {"gregtech:gt.blockores"}).getStringList();
		aggrorangeNether = config.get("Nether Settings", "Pigmen Range for block breaks", 16).getInt();
		
		config.save();

		MinecraftForge.EVENT_BUS.register(new BeyondRealityCoreEvent());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

}
