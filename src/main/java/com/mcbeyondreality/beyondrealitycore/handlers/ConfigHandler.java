package com.mcbeyondreality.beyondrealitycore.handlers;

import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;
    public static String[] bannedEnderBlocks, bannedNetherBlocks, rightClickBlackList, bottomLeftBranding;
    public static int aggrorangeEnd, aggrorangeNether, customBlocksCount;
    public static boolean fastLeafDecay, rightClick, enableOreBlocks, enableCustomBlocks;
    public static String strMainMenuBackground;

    public static void init() {

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
        strMainMenuBackground = config.get("Main Menu Settings", "Main Menu Screen", "background.png").getString();

        enableOreBlocks = config.get("Custom Blocks", "Enable Ore Blocks?", false).getBoolean();
        enableCustomBlocks = config.get("Custom Blocks", "Enable Custom Blocks?", false).getBoolean();
        customBlocksCount = config.get("Custom Blocks", "Number of custom blocks", 1).getInt();
        config.save();

    }


}
