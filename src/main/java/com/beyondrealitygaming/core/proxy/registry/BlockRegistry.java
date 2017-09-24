package com.beyondrealitygaming.core.proxy.registry;

import com.beyondrealitygaming.core.block.BRBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockRegistry {

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> ev) {
        BRBlock.blocks.forEach(brBlock -> brBlock.registerBlock(ev.getRegistry()));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> ev) {
        BRBlock.blocks.forEach(brBlock -> {
            brBlock.registerItem(ev.getRegistry());
            if (FMLCommonHandler.instance().getSide().isClient()) {
                brBlock.registerModels();
            }
        });

    }
}
