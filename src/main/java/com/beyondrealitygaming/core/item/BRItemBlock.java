package com.beyondrealitygaming.core.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BRItemBlock extends ItemBlock {

    public BRItemBlock(Block block, CreativeTabs tabs) {
        super(block);
        setUnlocalizedName(block.getUnlocalizedName());
        System.out.println(block.getRegistryName());
        setRegistryName(block.getRegistryName());
        setCreativeTab(tabs);
    }
}
