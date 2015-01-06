package com.mcbeyondreality.beyondrealitycore;

import com.mcbeyondreality.beyondrealitycore.commands.CommandGetUUID;
import com.mcbeyondreality.beyondrealitycore.commands.CommandToggleToolRightClick;
import com.mcbeyondreality.beyondrealitycore.event.BeyondRealityCoreEvent;
import com.mcbeyondreality.beyondrealitycore.event.LeafDecayEvent;
import com.mcbeyondreality.beyondrealitycore.event.RightClickEvent;
import com.mcbeyondreality.beyondrealitycore.handlers.CustomBlockHandler;
import com.mcbeyondreality.beyondrealitycore.handlers.CustomOreBlockHandler;
import com.mcbeyondreality.beyondrealitycore.handlers.DimensionBanHandler;
import com.mcbeyondreality.beyondrealitycore.handlers.GuiHandler;
import com.mcbeyondreality.beyondrealitycore.notification.NotificationTickHandler;
import com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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

    private static Configuration config;
    public static String[] bannedEnderBlocks, bannedNetherBlocks, rightClickBlackList, bottomLeftBranding;
    public static int aggrorangeEnd, aggrorangeNether, customBlocksCount;
    public static boolean fastLeafDecay, rightClick, enableOreBlocks, enableCustomBlocks;
    public static Item gemApatite;

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        MinecraftServer server = MinecraftServer.getServer();
        ICommandManager command = server.getCommandManager();
        ServerCommandManager manager = (ServerCommandManager) command;
        manager.registerCommand(new CommandGetUUID());
        manager.registerCommand(new CommandToggleToolRightClick());
    }

    public static CreativeTabs tabBeyondReality = new CreativeTabs("tabBeyondReality") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Items.diamond;
        }

    };

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

        try
        {
            DimensionBanHandler.loadBanedBlocks();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        proxy.register();

        config = new Configuration(new File("config/BeyondRealityCore/beyondrealitycore.cfg"));
        config.load();

        bannedEnderBlocks = config.get("End Settings", "Blocks the Endermen Don't want you to take",
                new String[] {"minecraft:end_stone", "gregtech:gt.blockores"}).getStringList();
        aggrorangeEnd = config.get("End Settings", "Enderman Range for block breaks", 16).getInt();

        bannedNetherBlocks = config.get("Nether Settings", "Blocks the Pigmen Don't want you to take",
                new String[] {"gregtech:gt.blockores"}).getStringList();
        aggrorangeNether = config.get("Nether Settings", "Pigmen Range for block breaks", 16).getInt();

        rightClickBlackList = config.get(Configuration.CATEGORY_GENERAL, "Black Listed Items for right click",
                new String[] {Items.golden_shovel.getUnlocalizedName(), "another unlocalized name"}).getStringList();
        rightClick = config.get(Configuration.CATEGORY_GENERAL, "Use right click handler?", true).getBoolean();
        fastLeafDecay = config.get(Configuration.CATEGORY_GENERAL, "Overwrite leaf decay?", false).getBoolean();

        bottomLeftBranding = config.get("Main Menu Settings", "Bottom Left Branding",
                new String[] {"Beyond Reality"}).getStringList();


        enableOreBlocks = config.get("Custom Blocks", "Enable Ore Blocks?", false).getBoolean();
        enableCustomBlocks = config.get("Custom Blocks", "Enable Custom Blocks?", false).getBoolean();
        customBlocksCount = config.get("Custom Blocks", "Number of custom blocks", 1).getInt();
        config.save();

        if(enableOreBlocks) {
            GameRegistry.registerItem(gemApatite = new Item()
                    .setUnlocalizedName("beyondrealitycore:gemApatite")
                    .setTextureName("beyondrealitycore:gemApatite")
                    .setCreativeTab(tabBeyondReality)
                    ,"gemApatite");
            CustomOreBlockHandler.init();
        }

        if(enableCustomBlocks) {
            CustomBlockHandler.init();
        }

        MinecraftForge.EVENT_BUS.register(new BeyondRealityCoreEvent());
        MinecraftForge.EVENT_BUS.register(new RightClickEvent());
        MinecraftForge.EVENT_BUS.register(new LeafDecayEvent());

        if(event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new GuiHandler());
            FMLCommonHandler.instance().bus().register(new NotificationTickHandler());
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if(enableOreBlocks) {
            CustomOreBlockHandler.oreDictInit();
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    public static void set(String categoryName, String propertyName, boolean newValue) {

        config.load();
        if (config.getCategoryNames().contains(categoryName)) {
            if (config.getCategory(categoryName).containsKey(propertyName)) {
                config.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }
        config.save();
    }
}
