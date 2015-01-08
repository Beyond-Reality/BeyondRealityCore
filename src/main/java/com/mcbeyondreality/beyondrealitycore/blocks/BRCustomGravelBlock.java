package com.mcbeyondreality.beyondrealitycore.blocks;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import java.util.Random;

public class BRCustomGravelBlock extends BlockFalling {

    private Item drop;
    private int meta;
    private int least_qty;
    private int most_qty;

    public BRCustomGravelBlock(String name, String tool, int hardness,
                               Item drop, int meta, int least_qty, int most_qty) {
        super(Material.sand);
        this.setBlockName("beyondrealitycore:" + name);
        this.setCreativeTab(BeyondRealityCore.tabBeyondReality);
        this.setHardness(1.5F);
        this.setHarvestLevel(tool, hardness);
        this.drop = drop;
        this.meta = meta;
        this.least_qty = least_qty;
        this.most_qty = most_qty;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon((this.getUnlocalizedName().substring(5)));
    }

    public BRCustomGravelBlock(String name, String tool, int hardness, Item drop, int least_qty, int most_qty) {
        this(name, tool, hardness, drop, 0, least_qty, most_qty);
    }

    public BRCustomGravelBlock(String name, String tool, int hardness, Item drop) {
        this(name, tool, hardness, drop, 0, 1, 1);
    }

    public BRCustomGravelBlock(String name, String tool, int hardness) {
        this(name, tool, hardness, null, 0, 1, 1);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        if(this.drop == null) {
            return Item.getItemFromBlock(this);
        } else {
            return this.drop;
        }
    }

    @Override
    public int damageDropped(int metadata) {
        return this.meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        if (this.least_qty >= this.most_qty)
            return this.least_qty;
        return this.least_qty + random.nextInt(this.most_qty - this.least_qty + fortune + 1);
    }
}
