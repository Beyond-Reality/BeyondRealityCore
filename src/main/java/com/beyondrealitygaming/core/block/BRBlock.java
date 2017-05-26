package com.beyondrealitygaming.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BRBlock extends Block {

    public BRBlock(Material material, String name, CreativeTabs tabs) {
        super(material);
        setUnlocalizedName("beyondreality:" + name);
        if (name != null) setRegistryName("beyondreality", name);
        setHardness(3.0F);
        setCreativeTab(tabs);
    }
}
