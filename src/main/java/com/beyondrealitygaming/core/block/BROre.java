package com.beyondrealitygaming.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BROre extends BRBlock {

    private Item drop;
    private int minDrop;
    private int maxDrop;

    public BROre(Material material, String name, CreativeTabs tabs, int minDrop, int maxDrop) {
        super(material, name, tabs);
        this.minDrop = minDrop;
        this.maxDrop = maxDrop;
    }

    public void setDrop(Item drop) {
        this.drop = drop;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return drop == null ? Item.getItemFromBlock(this) : drop;
    }


    public int quantityDropped(Random random)
    {
        return minDrop+new Random().nextInt(maxDrop-minDrop+1);
    }
}
