package com.mcbeyondreality.beyondrealitycore.handlers;

import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;
    public static String[] bannedEnderBlocks, bannedNetherBlocks, rightClickWhiteList, bottomLeftBranding, menuSayings;
    public static int aggrorangeEnd, aggrorangeNether, customBlocksCount, unbreakableBlocksCount;
    public static boolean customOreItems, spawnBuilding, fastLeafDecay, rightClick, enableOreBlocks, enableCustomBlocks, enableUnbreakableBlocks, enableAIM;
    public static String strMainMenuBackground, strMainMenuTitle, spawnBuildingName;
    public static int chairs, items;

    public static void init() {

        config = new Configuration(new File("config/BeyondRealityCore/beyondrealitycore.cfg"));
        config.load();

        bannedEnderBlocks = config.get("End Settings", "Blocks the Endermen Don't want you to take",
                new String[] {"minecraft:end_stone", "gregtech:gt.blockores"}).getStringList();
        aggrorangeEnd = config.get("End Settings", "Enderman Range for block breaks", 16).getInt();

        bannedNetherBlocks = config.get("Nether Settings", "Blocks the Pigmen Don't want you to take",
                new String[] {"gregtech:gt.blockores"}).getStringList();
        aggrorangeNether = config.get("Nether Settings", "Pigmen Range for block breaks", 16).getInt();

        rightClickWhiteList = config.get(Configuration.CATEGORY_GENERAL, "Whitelisted Items for right click",
                new String[] {Items.stone_pickaxe.getUnlocalizedName(), "another unlocalized name"}).getStringList();
        rightClick = config.get(Configuration.CATEGORY_GENERAL, "Use right click handler?", true).getBoolean();
        fastLeafDecay = config.get(Configuration.CATEGORY_GENERAL, "Overwrite leaf decay?", false).getBoolean();

        bottomLeftBranding = config.get("Main Menu Settings", "Bottom Left Branding",
                new String[] {"Beyond Reality"}).getStringList();
        strMainMenuBackground = config.get("Main Menu Settings", "Main Menu Screen", "background.png").getString();
        strMainMenuTitle = config.get("Main Menu Settings", "Main Menu Title", "title.png").getString();
        menuSayings = config.get("Main Menu Settings", "Main Menu Sayings",
                new String[] {"Welcome to Beyond Reality", "This Pack is HARD!"}).getStringList();

        enableOreBlocks = config.get("Custom Blocks", "Enable Ore Blocks?", false).getBoolean();
        enableCustomBlocks = config.get("Custom Blocks", "Enable Custom Blocks?", false).getBoolean();
        customBlocksCount = config.get("Custom Blocks", "Number of custom blocks", 1).getInt();
        enableUnbreakableBlocks = config.get("Custom Blocks", "Enable Unbreakable Blocks?", false).getBoolean();
        unbreakableBlocksCount = config.get("Custom Blocks", "Number of Unbreakable blocks (1-16)", 1).getInt();
        enableAIM = config.get("Custom Blocks", "Enable AIM?", false).getBoolean();

        spawnBuilding = config.get("Items", "Enable Spawn Building Item? (Requires RecurrentComplex)", false).getBoolean();
        spawnBuildingName = config.get("Items", "Name of schematic to spawn", "test").getString();
        customOreItems = config.get("Items", "Enable Custom Ore Items?", false).getBoolean();

        chairs = config.getInt("Chair Total", "How many chairs", 1, 0, 1000, "X amount");
        items = config.getInt("Item Total", "How many items", 1, 0, 1000, "X amount");

        config.save();
    }


}
