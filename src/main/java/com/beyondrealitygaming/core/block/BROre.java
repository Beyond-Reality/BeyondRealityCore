package com.beyondrealitygaming.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BROre extends BRBlock {

    private Item drop;

    public BROre(Material material, String name, CreativeTabs tabs) {
        super(material, name, tabs);
    }

    public void setDrop(Item drop) {
        this.drop = drop;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return drop == null ? Item.getItemFromBlock(this) : drop;
    }

    public int quantityDroppedWithBonus(int fortune, Random random) {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }
}
