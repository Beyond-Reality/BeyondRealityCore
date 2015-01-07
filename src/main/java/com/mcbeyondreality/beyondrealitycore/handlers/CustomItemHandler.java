package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.items.BRCustomItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class CustomItemHandler {

    static Item gemApatite;

    public static void init() {

        gemApatite = new BRCustomItem("gemApatite");

        GameRegistry.registerItem(gemApatite, "gemApatite");

    }
}
