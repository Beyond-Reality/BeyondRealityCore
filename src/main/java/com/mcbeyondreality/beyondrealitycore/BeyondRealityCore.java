package com.mcbeyondreality.beyondrealitycore;

import com.mcbeyondreality.beyondrealitycore.commands.CommandGetUUID;
import com.mcbeyondreality.beyondrealitycore.handlers.BeyondRealityCoreEvent;
import com.mcbeyondreality.beyondrealitycore.handlers.GuiHandler;
import com.mcbeyondreality.beyondrealitycore.handlers.RightClickHandler;
import com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

@Mod(name = "Beyond Reality Core", modid = "beyondrealitycore", version = "1.4")


public class BeyondRealityCore {
	
	@Instance("beyondrealitycore")
	public static BeyondRealityCore instance;
	
	@SidedProxy( clientSide="com.mcbeyondreality.beyondrealitycore.proxy.ClientProxy", serverSide="com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static String[] bannedEnderBlocks, bannedNetherBlocks, bottomLeftBranding;
	public static int aggrorangeEnd, aggrorangeNether;
    public static boolean fastLeafDecay;

	
	@EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        MinecraftServer server = MinecraftServer.getServer();
        ICommandManager command = server.getCommandManager();
        ServerCommandManager manager = (ServerCommandManager) command;
        manager.registerCommand(new CommandGetUUID());
    }
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){

        if(event.getSide() == Side.CLIENT) {
            try {
                GuiHandler.addCustomServers(Minecraft.getMinecraft());
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }

        Configuration config = new Configuration(new File("config/BeyondRealityCore/beyondrealitycore.cfg"));
		config.load();
		
		bannedEnderBlocks = config.get("End Settings", "Blocks the Endermen Don't want you to take", new String[] {"minecraft:end_stone", "gregtech:gt.blockores"}).getStringList();
		aggrorangeEnd = config.get("End Settings", "Enderman Range for block breaks", 16).getInt();
		
		bannedNetherBlocks = config.get("Nether Settings", "Blocks the Pigmen Don't want you to take", new String[] {"gregtech:gt.blockores"}).getStringList();
		aggrorangeNether = config.get("Nether Settings", "Pigmen Range for block breaks", 16).getInt();

		bottomLeftBranding = config.get("main menu settings", "Bottom Left Branding", new String[] {"Beyond Reality"}).getStringList();

        fastLeafDecay = config.get(Configuration.CATEGORY_GENERAL, "Overwrite leaf decay?", false).getBoolean();

        config.save();

		MinecraftForge.EVENT_BUS.register(new BeyondRealityCoreEvent());
        MinecraftForge.EVENT_BUS.register(new RightClickHandler());

        if(event.getSide() == Side.CLIENT)
        MinecraftForge.EVENT_BUS.register(new GuiHandler());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

}
