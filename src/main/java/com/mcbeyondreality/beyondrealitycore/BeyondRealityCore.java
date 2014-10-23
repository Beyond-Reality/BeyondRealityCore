package com.mcbeyondreality.beyondrealitycore;

import com.mcbeyondreality.beyondrealitycore.commands.CommandGetUUID;
import com.mcbeyondreality.beyondrealitycore.event.BeyondRealityCoreEvent;
import com.mcbeyondreality.beyondrealitycore.event.LeafDecayEvent;
import com.mcbeyondreality.beyondrealitycore.event.RightClickEvent;
import com.mcbeyondreality.beyondrealitycore.handlers.CustomBlockHandler;
import com.mcbeyondreality.beyondrealitycore.handlers.GuiHandler;
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
import net.minecraft.init.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

@Mod(name = "Beyond Reality Core", modid = "beyondrealitycore", version = "@VERSION@")


public class BeyondRealityCore {

    @Instance("beyondrealitycore")
    public static BeyondRealityCore instance;

    @SidedProxy( clientSide="com.mcbeyondreality.beyondrealitycore.proxy.ClientProxy", serverSide="com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static String[] bannedEnderBlocks, bannedNetherBlocks, rightClickBlackList, bottomLeftBranding;
    public static int aggrorangeEnd, aggrorangeNether, customBlocksCount;
    public static boolean fastLeafDecay, rightClick;


    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
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

        proxy.register();

        Configuration config = new Configuration(new File("config/BeyondRealityCore/beyondrealitycore.cfg"));
        config.load();

        bannedEnderBlocks = config.get("End Settings", "Blocks the Endermen Don't want you to take", new String[] {"minecraft:end_stone", "gregtech:gt.blockores"}).getStringList();
        aggrorangeEnd = config.get("End Settings", "Enderman Range for block breaks", 16).getInt();

        bannedNetherBlocks = config.get("Nether Settings", "Blocks the Pigmen Don't want you to take", new String[] {"gregtech:gt.blockores"}).getStringList();
        aggrorangeNether = config.get("Nether Settings", "Pigmen Range for block breaks", 16).getInt();

        rightClickBlackList = config.get(Configuration.CATEGORY_GENERAL, "Black Listed Items for right click", new String[] {Items.golden_shovel.getUnlocalizedName(), "another unlocalized name"}).getStringList();
        customBlocksCount = config.get(Configuration.CATEGORY_GENERAL, "Number of custom blocks", 1).getInt();
        bottomLeftBranding = config.get("main menu settings", "Bottom Left Branding", new String[] {"Beyond Reality"}).getStringList();

        rightClick = config.get(Configuration.CATEGORY_GENERAL, "Use right click handler?", true).getBoolean();
        fastLeafDecay = config.get(Configuration.CATEGORY_GENERAL, "Overwrite leaf decay?", false).getBoolean();

        config.save();

        CustomBlockHandler.init();
        MinecraftForge.EVENT_BUS.register(new BeyondRealityCoreEvent());
        MinecraftForge.EVENT_BUS.register(new RightClickEvent());
        MinecraftForge.EVENT_BUS.register(new LeafDecayEvent());
        if(event.getSide() == Side.CLIENT)
            MinecraftForge.EVENT_BUS.register(new GuiHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

}
