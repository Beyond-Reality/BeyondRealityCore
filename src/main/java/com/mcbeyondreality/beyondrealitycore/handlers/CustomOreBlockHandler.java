package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.blocks.BRCustomOreBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

public class CustomOreBlockHandler {

    static Block oreApatite, oreCopper, oreTin, oreLead, oreSilver, oreNickel, oreAluminium, orePlatinum;
    static Block oreCadmium, oreIndium, oreUranium, orePhosphate, oreZinc;

    public static void init() {
        oreApatite = new BRCustomOreBlock("oreApatite", "pickaxe", 2, BeyondRealityCore.gemApatite, 1, 4);
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
    }
}
