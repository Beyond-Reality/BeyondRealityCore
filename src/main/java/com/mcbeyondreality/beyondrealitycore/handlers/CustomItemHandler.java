package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.items.BRCustomItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class CustomItemHandler {

    static Item gemApatite, tinyApatite, tinyCopper, tinyTin, tinyLead, tinySilver, tinyNickel, tinyAluminium;
    static Item tinyPlatinum, tinyCadmium, tinyIndium, tinyMagnetite, tinyUranium, tinyZinc;
    static Item tinyIron, tinyGold, tinyDiamond, tinyEmerald, tinyRedstone, tinyLapis, tinyCoal;

    public static void init() {

        gemApatite = new BRCustomItem("gemApatite");
        tinyApatite = new BRCustomItem("tinyApatite");
        tinyCopper = new BRCustomItem("tinyCopper");
        tinyTin = new BRCustomItem("tinyTin");
        tinyLead = new BRCustomItem("tinyLead");
        tinySilver = new BRCustomItem("tinySilver");
        tinyNickel = new BRCustomItem("tinyNickel");
        tinyAluminium = new BRCustomItem("tinyAluminium");
        tinyPlatinum = new BRCustomItem("tinyPlatinum");
        tinyCadmium = new BRCustomItem("tinyCadmium");
        tinyIndium = new BRCustomItem("tinyIndium");
        tinyMagnetite = new BRCustomItem("tinyMagnetite");
        tinyUranium = new BRCustomItem("tinyUranium");
        tinyZinc = new BRCustomItem("tinyZinc");
        tinyIron = new BRCustomItem("tinyIron");
        tinyGold = new BRCustomItem("tinyGold");
        tinyDiamond = new BRCustomItem("tinyDiamond");
        tinyEmerald = new BRCustomItem("tinyEmerald");
        tinyRedstone = new BRCustomItem("tinyRedstone");
        tinyLapis = new BRCustomItem("tinyLapis");
        tinyCoal = new BRCustomItem("tinyCoal");


        GameRegistry.registerItem(gemApatite, "gemApatite");
        GameRegistry.registerItem(tinyApatite, "tinyApatite");
        GameRegistry.registerItem(tinyCopper, "tinyCopper");
        GameRegistry.registerItem(tinyTin, "tinyTin");
        GameRegistry.registerItem(tinyLead, "tinyLead");
        GameRegistry.registerItem(tinySilver, "tinySilver");
        GameRegistry.registerItem(tinyNickel, "tinyNickel");
        GameRegistry.registerItem(tinyAluminium, "tinyAluminium");
        GameRegistry.registerItem(tinyPlatinum, "tinyPlatinum");
        GameRegistry.registerItem(tinyCadmium, "tinyCadmium");
        GameRegistry.registerItem(tinyIndium, "tinyIndium");
        GameRegistry.registerItem(tinyMagnetite, "tinyMagnetite");
        GameRegistry.registerItem(tinyUranium, "tinyUranium");
        GameRegistry.registerItem(tinyZinc, "tinyZinc");
        GameRegistry.registerItem(tinyIron, "tinyIron");
        GameRegistry.registerItem(tinyGold, "tinyGold");
        GameRegistry.registerItem(tinyDiamond, "tinyDiamond");
        GameRegistry.registerItem(tinyEmerald, "tinyEmerald");
        GameRegistry.registerItem(tinyRedstone, "tinyRedstone");
        GameRegistry.registerItem(tinyLapis, "tinyLapis");
        GameRegistry.registerItem(tinyCoal, "tinyCoal");

    }
}
