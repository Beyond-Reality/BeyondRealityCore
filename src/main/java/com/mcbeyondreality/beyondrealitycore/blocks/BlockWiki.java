package com.mcbeyondreality.beyondrealitycore.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWiki extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon top;
    public BlockWiki() {
        super(Material.rock);
        setBlockName("beyondrealitycore:wiki");
        setCreativeTab(null);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int j)
    {
        return side == 1 ? top : blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon("beyondrealitycore:wikiSide");
        top = par1IconRegister.registerIcon("beyondrealitycore:wikiTop");
    }
}