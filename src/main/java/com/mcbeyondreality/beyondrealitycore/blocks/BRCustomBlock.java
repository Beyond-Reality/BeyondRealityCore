package com.mcbeyondreality.beyondrealitycore.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BRCustomBlock extends Block {
    public BRCustomBlock(String name) {
        super(Material.rock);
        this.setBlockName("beyondrealitycore:" + name);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon((this.getUnlocalizedName().substring(5)));
    }


}
