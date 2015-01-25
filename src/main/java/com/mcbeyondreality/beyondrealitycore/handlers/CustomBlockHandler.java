package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.blocks.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.ArrayList;
import java.util.List;

public class CustomBlockHandler {
    public static List<Block> blocks = new ArrayList<Block>();
    public static Block dimGuardKiller;
    public static Block oreApatite, oreCopper, oreTin, oreLead, oreSilver, oreNickel, oreAluminium, orePlatinum;
    public static Block oreCadmium, oreIndium, oreUranium, oreZinc, oreMagnetite;
    public static Block gemApatite, tinyoreApatite, tinyoreCopper, tinyoreTin, tinyoreLead, tinyoreSilver, tinyoreNickel, tinyoreAluminium;
    public static Block tinyorePlatinum, tinyoreCadmium, tinyoreIndium, tinyoreMagnetite, tinyoreUranium, tinyoreZinc;
    public static Block tinyoreIron, tinyoreGold, tinyoreDiamond, tinyoreEmerald, tinyoreRedstone, tinyoreLapis, tinyoreCoal;
    public static Block blockAim, blockAimActive, blockUnbreakable;

    public static void init() {
        dimGuardKiller = new BlockDimGuardKiller();
        GameRegistry.registerBlock(dimGuardKiller, "dimGuardKiller");
        for (int i = 0; i < ConfigHandler.customBlocksCount; i++)
            registerBlock("customBlock_" + i, i);
    }
    public static void registerBlock(String name, int i)
    {
        blocks.add(new BRCustomBlock(name));
        GameRegistry.registerBlock(blocks.get(i), name);
    }

    public static void initOre() {

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
        GameRegistry.registerBlock(tinyoreZinc, "tinyoreZinc");
        GameRegistry.registerBlock(tinyoreIron, "tinyoreIron");
        GameRegistry.registerBlock(tinyoreGold, "tinyoreGold");
        GameRegistry.registerBlock(tinyoreDiamond, "tinyoreDiamond");
        GameRegistry.registerBlock(tinyoreEmerald, "tinyoreEmerald");
        GameRegistry.registerBlock(tinyoreRedstone, "tinyoreRedstone");
        GameRegistry.registerBlock(tinyoreLapis, "tinyoreLapis");
        GameRegistry.registerBlock(tinyoreCoal, "tinyoreCoal");

        //register thaumcraft aspects
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreApatite), (new AspectList()).add(Aspect.CROP, 2).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreCopper), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreTin), (new AspectList()).add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreLead), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.ORDER, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreSilver), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.GREED, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreNickel), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreAluminium), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.FLIGHT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.orePlatinum), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreCadmium), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.METAL, 1).add(Aspect.ENERGY, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreIndium), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreUranium), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreZinc), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.HEAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.oreMagnetite), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.TOOL, 1));

        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreApatite), (new AspectList()).add(Aspect.CROP, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreCopper), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreTin), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreLead), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.ORDER, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreSilver), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.GREED, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreNickel), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreAluminium), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.FLIGHT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyorePlatinum), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreCadmium), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.POISON, 1).add(Aspect.ENERGY, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreIndium), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreUranium), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreZinc), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.HEAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreMagnetite), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.TOOL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreIron), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreGold), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.GREED, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreDiamond), (new AspectList()).add(Aspect.GREED, 2).add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreEmerald), (new AspectList()).add(Aspect.GREED, 2).add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreRedstone), (new AspectList()).add(Aspect.ENERGY, 1).add(Aspect.EARTH, 1).add(Aspect.MECHANISM, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreLapis), (new AspectList()).add(Aspect.SENSES, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomBlockHandler.tinyoreCoal), (new AspectList()).add(Aspect.ENERGY, 1).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));

        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyApatite), (new AspectList()).add(Aspect.CROP, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyCopper), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyTin), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyLead), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.ORDER, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinySilver), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.GREED, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyNickel), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyAluminium), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.FLIGHT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyPlatinum), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyCadmium), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.POISON, 1).add(Aspect.ENERGY, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyIndium), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyUranium), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyZinc), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.HEAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyMagnetite), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1).add(Aspect.TOOL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyIron), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyGold), (new AspectList()).add(Aspect.METAL, 1).add(Aspect.GREED, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyDiamond), (new AspectList()).add(Aspect.GREED, 1).add(Aspect.CRYSTAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyEmerald), (new AspectList()).add(Aspect.GREED, 1).add(Aspect.CRYSTAL, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyRedstone), (new AspectList()).add(Aspect.ENERGY, 1).add(Aspect.EARTH, 1).add(Aspect.MECHANISM, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyLapis), (new AspectList()).add(Aspect.SENSES, 1).add(Aspect.EARTH, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(CustomItemHandler.tinyCoal), (new AspectList()).add(Aspect.ENERGY, 1).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));


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
        OreDictionary.registerOre("oreZinc", oreZinc);
        OreDictionary.registerOre("oreMagnetite", oreMagnetite);
    }

    public static void initAIM() {
        blockAimActive = new BlockAim(true).setBlockName("beyondrealitycore:blockAimActive").setLightLevel(10F);
        blockAim = new BlockAim(false).setBlockName("beyondrealitycore:blockAim").setCreativeTab(BeyondRealityCore.tabBeyondReality);

        GameRegistry.registerBlock(blockAimActive, "blockAimActive");
        GameRegistry.registerBlock(blockAim, "blockAim");
    }

    public static void initUnbreakable() {

        blockUnbreakable = new BlockUnbreakable();

        GameRegistry.registerBlock(blockUnbreakable, MultiBlockUnbreakable.class, "blockUnbreakable");

        /*for (int x = 0; x < amount; x++) {
            ItemStack multiBlockStack = new ItemStack(blockUnbreakable, 1, x);
            //LanguageRegistry.addName(multiBlockStack, ConfigHandler.unBreakableNames[x]);
        }*/

    }
}
