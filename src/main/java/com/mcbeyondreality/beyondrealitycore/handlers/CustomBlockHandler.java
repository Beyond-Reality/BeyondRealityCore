package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.blocks.BRCustomBlock;
import com.mcbeyondreality.beyondrealitycore.blocks.BlockDimGuardKiller;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CustomBlockHandler {
    public static List<Block> blocks = new ArrayList<Block>();
    public static Block dimGuardKiller;
    public static void init() {
        dimGuardKiller = new BlockDimGuardKiller();
        GameRegistry.registerBlock(dimGuardKiller, "dimGuardKiller");
        for (int i = 0; i < BeyondRealityCore.customBlocksCount; i++)
            registerBlock("customBlock_" + i, i);
    }
    public static void registerBlock(String name, int i)
    {
        blocks.add(new BRCustomBlock(name));
        GameRegistry.registerBlock(blocks.get(i), name);
    }
}
