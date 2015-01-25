package com.mcbeyondreality.beyondrealitycore;

import com.mcbeyondreality.beyondrealitycore.commands.CommandGetUUID;
import com.mcbeyondreality.beyondrealitycore.commands.CommandToggleToolRightClick;
import com.mcbeyondreality.beyondrealitycore.event.BeyondRealityCoreEvent;
import com.mcbeyondreality.beyondrealitycore.event.LeafDecayEvent;
import com.mcbeyondreality.beyondrealitycore.event.RightClickEvent;
import com.mcbeyondreality.beyondrealitycore.handlers.*;
import com.mcbeyondreality.beyondrealitycore.notification.NotificationTickHandler;
import com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy;
import com.mcbeyondreality.beyondrealitycore.tileentity.TileAim;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
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
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Mod(name = "Beyond Reality Core", modid = "beyondrealitycore", version = "@VERSION@", dependencies = "after:CoFHCore")


public class BeyondRealityCore {

    //TODO - Add thaumcraft aspects to ore

    @Instance("beyondrealitycore")
    public static BeyondRealityCore instance;

    @SidedProxy( clientSide="com.mcbeyondreality.beyondrealitycore.proxy.ClientProxy", serverSide="com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy")
    public static CommonProxy proxy;

    //public static FMLEventChannel NetworkCon;

    public static File pngMainMenu, pngTitle;

    public enum GUIs {
        AIM
    }

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

        ConfigHandler.init();
        try {
            AIMMachineRecipeHandler.loadAIMRecipes();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        pngTitle = new File("config/BeyondRealityCore/images", ConfigHandler.strMainMenuTitle);
        if(!pngTitle.exists()) {
            URL inputUrl = getClass().getResource("/assets/beyondrealitycore/textures/title.png");
            try {
                FileUtils.copyURLToFile(inputUrl, pngTitle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        pngMainMenu = new File("config/BeyondRealityCore/images", ConfigHandler.strMainMenuBackground);
        if(!pngMainMenu.exists()) {
            URL inputUrl = getClass().getResource("/assets/beyondrealitycore/textures/background.png");
            try {
                FileUtils.copyURLToFile(inputUrl, pngMainMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(ConfigHandler.enableOreBlocks) {
            CustomItemHandler.init();
            CustomBlockHandler.initOre();
        }

        if(ConfigHandler.enableCustomBlocks) {
            CustomBlockHandler.init();
        }

        if(ConfigHandler.enableAIM) {
            CustomBlockHandler.initAIM();
        }

        if(ConfigHandler.enableUnbreakableBlocks) {
            CustomBlockHandler.initUnbreakable();
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
        if(ConfigHandler.enableOreBlocks) {
            CustomBlockHandler.oreDictInit();
            CraftingHandler.init();
        }

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileAim.class, "beyondrealitycore:tileAim");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    public static void set(String categoryName, String propertyName, boolean newValue) {


        ConfigHandler.config.load();
        if (ConfigHandler.config.getCategoryNames().contains(categoryName)) {
            if (ConfigHandler.config.getCategory(categoryName).containsKey(propertyName)) {
                ConfigHandler.config.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }
        ConfigHandler.config.save();
    }
}
