package com.beyondrealitygaming.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class BRBlock extends Block {

    public static List<BRBlock> blocks = new ArrayList<>();

    public BRBlock(Material material, String name, CreativeTabs tabs) {
        super(material);
        setUnlocalizedName("beyondreality:" + name);
        if (name != null) setRegistryName("beyondreality", name);
        setHardness(3.0F);
        setCreativeTab(tabs);
        blocks.add(this);
    }

    public void registerBlock(IForgeRegistry<Block> blockIForgeRegistry){
        blockIForgeRegistry.register(this);
    }

    public void registerItem(IForgeRegistry<Item> itemIForgeRegistry){
        itemIForgeRegistry.register(new ItemBlock(this));
    }

    public abstract void registerModels();
}
