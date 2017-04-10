package com.beyondrealitygaming.core.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BRItem extends Item {

    public BRItem(String name, CreativeTabs tabs) {
        setUnlocalizedName("beyondreality:" + name);
        setRegistryName("beyondreality", name);
        setCreativeTab(tabs);
    }
}
