package com.mcbeyondreality.beyondrealitycore.blocks;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.handlers.CustomBlockHandler;
import com.mcbeyondreality.beyondrealitycore.tileentity.TileAim;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockAim extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon top, front;

    public boolean isRunning = false;

    public BlockAim(boolean isRunning) {
        super(Material.rock);
        //this.setHardness(10000.0F);
        this.setBlockUnbreakable();
        this.setResistance(2000.0F);
        this.isRunning = isRunning;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon("beyondrealitycore:aim_side");
        this.front = iconregister.registerIcon(isRunning ? "beyondrealitycore:aim_front_on" : "beyondrealitycore:aim_front_off");
        this.top = iconregister.registerIcon("beyondrealitycore:aim_top");
    }

    public IIcon getIcon(int side, int meta) {
         if (side == 1) return this.top;
        else if (side == 0) return this.top;
        else if (meta == 2 && side == 2) return this.front;
        else if (meta == 3 && side == 5) return this.front;
        else if (meta == 0 && side == 3) return this.front;
        else if (meta == 1 && side == 4) return this.front;
        else return this.blockIcon;

    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {

        int whichDirectionFacing = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, whichDirectionFacing, 2);
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return false;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileAim();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta,
                                    float hitX, float hitY, float hitZ) {

            if (world.getTileEntity(x, y, z) != null) {
                player.openGui(BeyondRealityCore.instance, BeyondRealityCore.GUIs.AIM.ordinal(), world, x, y, z);
                return true;
            }

        return true;
    }


    public static void updateFurnaceBlockState(boolean active, World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getTileEntity(x, y, z);

        if (active)
        {
            world.setBlock(x, y, z, CustomBlockHandler.blockAimActive);
        }
        else
        {
            world.setBlock(x, y, z, CustomBlockHandler.blockAim);
        }

        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        super.breakBlock(world, x, y, z, block, metadata);
    }
}
