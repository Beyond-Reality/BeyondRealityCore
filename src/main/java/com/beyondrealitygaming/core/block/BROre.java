package com.beyondrealitygaming.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BROre extends BRBlock {


    private Item drop;
    private int color;

    public BROre(Material material, String name, CreativeTabs tabs, int color, int mining) {
        super(material, name, tabs);
        this.color = color;
        this.setHarvestLevel("pickaxe", mining);
    }

    public void setDrop(Item drop) {
        this.drop = drop;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return drop == null ? Item.getItemFromBlock(this) : drop;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    public int getColor() {
        return color;
    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }



}
