package com.beyondrealitygaming.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BROre extends BRBlock {

    private Item drop;
    private int color;
    private String type;

    public BROre(Material material, String name, CreativeTabs tabs, int color, int mining, String type) {
        super(material, name, tabs);
        this.color = color;
        this.setHarvestLevel("pickaxe", mining);
        this.type = type;
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

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        return drop == null ? 0 : MathHelper.getInt(rand, 1, 4);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return quantityDroppedWithBonus(fortune, random);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return quantityDropped(random) +  (drop == null || fortune == 0  ?  0 : random.nextInt(fortune) );
    }
}
