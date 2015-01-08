package com.mcbeyondreality.beyondrealitycore.blocks;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BRCustomBlock extends Block {
    public BRCustomBlock(String name) {
        super(Material.rock);
        this.setBlockName("beyondrealitycore:" + name);
        this.setCreativeTab(BeyondRealityCore.tabBeyondReality);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon((this.getUnlocalizedName().substring(5)));
    }
}
