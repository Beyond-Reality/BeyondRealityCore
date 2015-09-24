package com.mcbeyondreality.beyondrealitycore.blocks;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.handlers.ConfigHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockUnbreakable1 extends Block {

    private IIcon[] textures;

    public BlockUnbreakable1() {
        super(Material.rock);
        this.setBlockName("beyondreality:unbreakableBlock1");
        this.setCreativeTab(BeyondRealityCore.tabBeyondReality);
        this.setBlockUnbreakable();
        this.setResistance(2000.0F);
    }

/*    @SideOnly(Side.CLIENT)
    public int getBlockTextureFromSideAndMetadata (int side, int metadata) {
        return 16 + metadata;
    }*/

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        int blockCount = ConfigHandler.unbreakableBlocksCount1;

        this.textures = new IIcon[blockCount];
        for (int i = 0; i < blockCount; i++) {
            this.textures[i] = iconregister.registerIcon("beyondrealitycore:blockUnbreakable1_" + i);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        //TODO this might not work
        if (meta >= (ConfigHandler.unbreakableBlocksCount1)) meta = 0;

        return this.textures[meta];
    }

    @Override
    public int damageDropped (int metadata) {
        return metadata;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems) {
        for (int ix = 0; ix < ConfigHandler.unbreakableBlocksCount1; ix++) {
            subItems.add(new ItemStack(this, 1, ix));
        }
    }
}
