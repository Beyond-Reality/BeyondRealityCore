package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.blocks.BRCustomGravelBlock;
import com.mcbeyondreality.beyondrealitycore.blocks.BRCustomOreBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class CustomOreBlockHandler {

    static Block oreApatite, oreCopper, oreTin, oreLead, oreSilver, oreNickel, oreAluminium, orePlatinum;
    static Block oreCadmium, oreIndium, oreUranium, orePhosphate, oreZinc, oreMagnetite;
    static Block gemApatite, tinyoreApatite, tinyoreCopper, tinyoreTin, tinyoreLead, tinyoreSilver, tinyoreNickel, tinyoreAluminium;
    static Block tinyorePlatinum, tinyoreCadmium, tinyoreIndium, tinyoreMagnetite, tinyoreUranium, tinyorePhosphate, tinyoreZinc;
    static Block tinyoreIron, tinyoreGold, tinyoreDiamond, tinyoreEmerald, tinyoreRedstone, tinyoreLapis, tinyoreCoal;

    public static void init() {
        //Normal Ore Blocks
        oreApatite = new BRCustomOreBlock("oreApatite", "pickaxe", 2, CustomItemHandler.gemApatite, 1, 4);
        oreCopper = new BRCustomOreBlock("oreCopper", "pickaxe", 2);
        oreTin = new BRCustomOreBlock("oreTin", "pickaxe", 2);
        oreLead = new BRCustomOreBlock("oreLead", "pickaxe", 2);
        oreSilver = new BRCustomOreBlock("oreSilver", "pickaxe", 2);
        oreNickel = new BRCustomOreBlock("oreNickel", "pickaxe", 2);
        oreAluminium = new BRCustomOreBlock("oreAluminium", "pickaxe", 2);
        orePlatinum = new BRCustomOreBlock("orePlatinum", "pickaxe", 2);
        oreCadmium = new BRCustomOreBlock("oreCadmium", "pickaxe", 2);
        oreIndium = new BRCustomOreBlock("oreIndium", "pickaxe", 2);
        oreUranium = new BRCustomOreBlock("oreUranium", "pickaxe", 2);
        orePhosphate = new BRCustomOreBlock("orePhosphate", "pickaxe", 2);
        oreZinc = new BRCustomOreBlock("oreZinc", "pickaxe", 2);
        oreMagnetite = new BRCustomOreBlock("oreMagnetite", "pickaxe", 2);

        //Small Ore Blocks
        tinyoreApatite = new BRCustomGravelBlock("tinyoreApatite", "pickaxe", 1, CustomItemHandler.tinyApatite);
        tinyoreCopper = new BRCustomGravelBlock("tinyoreCopper", "shovel", 1, CustomItemHandler.tinyCopper);
        tinyoreTin = new BRCustomGravelBlock("tinyoreTin", "shovel", 1, CustomItemHandler.tinyTin);
        tinyoreLead = new BRCustomGravelBlock("tinyoreLead", "shovel", 1, CustomItemHandler.tinyLead);
        tinyoreSilver = new BRCustomGravelBlock("tinyoreSilver", "shovel", 1, CustomItemHandler.tinySilver);
        tinyoreNickel = new BRCustomGravelBlock("tinyoreNickel", "shovel", 1, CustomItemHandler.tinyNickel);
        tinyoreAluminium = new BRCustomGravelBlock("tinyoreAluminium", "shovel", 1, CustomItemHandler.tinyAluminium);
        tinyorePlatinum = new BRCustomGravelBlock("tinyorePlatinum", "shovel", 1, CustomItemHandler.tinyPlatinum);
        tinyoreCadmium = new BRCustomGravelBlock("tinyoreCadmium", "shovel", 1, CustomItemHandler.tinyCadmium);
        tinyoreIndium = new BRCustomGravelBlock("tinyoreIndium", "shovel", 1, CustomItemHandler.tinyIndium);
        tinyoreMagnetite = new BRCustomGravelBlock("tinyoreMagnetite", "shovel", 1, CustomItemHandler.tinyMagnetite);
        tinyoreUranium = new BRCustomGravelBlock("tinyoreUranium", "shovel", 1, CustomItemHandler.tinyUranium);
        tinyorePhosphate = new BRCustomGravelBlock("tinyorePhosphate", "shovel", 1, CustomItemHandler.tinyPhosphate);
        tinyoreZinc = new BRCustomGravelBlock("tinyoreZinc", "shovel", 1, CustomItemHandler.tinyZinc);
        tinyoreIron = new BRCustomGravelBlock("tinyoreIron", "shovel", 1, CustomItemHandler.tinyIron);
        tinyoreGold = new BRCustomGravelBlock("tinyoreGold", "shovel", 1, CustomItemHandler.tinyGold);
        tinyoreDiamond = new BRCustomGravelBlock("tinyoreDiamond", "shovel", 1, CustomItemHandler.tinyDiamond);
        tinyoreEmerald = new BRCustomGravelBlock("tinyoreEmerald", "shovel", 1, CustomItemHandler.tinyEmerald);
        tinyoreRedstone = new BRCustomGravelBlock("tinyoreRedstone", "shovel", 1, CustomItemHandler.tinyRedstone);
        tinyoreLapis = new BRCustomGravelBlock("tinyoreLapis", "shovel", 1, CustomItemHandler.tinyLapis);
        tinyoreCoal = new BRCustomGravelBlock("tinyoreCoal", "shovel", 1, CustomItemHandler.tinyCoal);

        GameRegistry.registerBlock(oreApatite, "oreApatite");
        GameRegistry.registerBlock(oreCopper, "oreCopper");
        GameRegistry.registerBlock(oreTin, "oreTin");
        GameRegistry.registerBlock(oreLead, "oreLead");
        GameRegistry.registerBlock(oreSilver, "oreSilver");
        GameRegistry.registerBlock(oreNickel, "oreNickel");
        GameRegistry.registerBlock(oreAluminium, "oreAluminium");
        GameRegistry.registerBlock(orePlatinum, "orePlatinum");
        GameRegistry.registerBlock(oreCadmium, "oreCadmium");
        GameRegistry.registerBlock(oreIndium, "oreIndium");
        GameRegistry.registerBlock(oreUranium, "oreUranium");
        GameRegistry.registerBlock(orePhosphate, "orePhosphate");
        GameRegistry.registerBlock(oreZinc, "oreZinc");
        GameRegistry.registerBlock(oreMagnetite, "oreMagnetite");
        //Tiny Ores
        GameRegistry.registerBlock(tinyoreApatite, "tinyoreApatite");
        GameRegistry.registerBlock(tinyoreCopper, "tinyoreCopper");
        GameRegistry.registerBlock(tinyoreTin, "tinyoreTin");
        GameRegistry.registerBlock(tinyoreLead, "tinyoreLead");
        GameRegistry.registerBlock(tinyoreSilver, "tinyoreSilver");
        GameRegistry.registerBlock(tinyoreNickel, "tinyoreNickel");
        GameRegistry.registerBlock(tinyoreAluminium, "tinyoreAluminium");
        GameRegistry.registerBlock(tinyorePlatinum, "tinyorePlatinum");
        GameRegistry.registerBlock(tinyoreCadmium, "tinyoreCadmium");
        GameRegistry.registerBlock(tinyoreIndium, "tinyoreIndium");
        GameRegistry.registerBlock(tinyoreMagnetite, "tinyoreMagnetite");
        GameRegistry.registerBlock(tinyoreUranium, "tinyoreUranium");
        GameRegistry.registerBlock(tinyorePhosphate, "tinyorePhosphate");
        GameRegistry.registerBlock(tinyoreZinc, "tinyoreZinc");
        GameRegistry.registerBlock(tinyoreIron, "tinyoreIron");
        GameRegistry.registerBlock(tinyoreGold, "tinyoreGold");
        GameRegistry.registerBlock(tinyoreDiamond, "tinyoreDiamond");
        GameRegistry.registerBlock(tinyoreEmerald, "tinyoreEmerald");
        GameRegistry.registerBlock(tinyoreRedstone, "tinyoreRedstone");
        GameRegistry.registerBlock(tinyoreLapis, "tinyoreLapis");
        GameRegistry.registerBlock(tinyoreCoal, "tinyoreCoal");
    }

    public static void oreDictInit() {

        OreDictionary.registerOre("oreApatite", oreApatite);
        OreDictionary.registerOre("oreCopper", oreCopper);
        OreDictionary.registerOre("oreTin", oreTin);
        OreDictionary.registerOre("oreLead", oreLead);
        OreDictionary.registerOre("oreSilver", oreSilver);
        OreDictionary.registerOre("oreNickel", oreNickel);
        OreDictionary.registerOre("oreAluminium", oreAluminium);
        OreDictionary.registerOre("orePlatinum", orePlatinum);
        OreDictionary.registerOre("oreCadmium", oreCadmium);
        OreDictionary.registerOre("oreIndium", oreIndium);
        OreDictionary.registerOre("oreUranium", oreUranium);
        OreDictionary.registerOre("orePhosphate", orePhosphate);
        OreDictionary.registerOre("oreZinc", oreZinc);
        OreDictionary.registerOre("oreMagnetite", oreMagnetite);
    }
}
