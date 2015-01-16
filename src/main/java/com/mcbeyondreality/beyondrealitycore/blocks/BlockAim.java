package com.mcbeyondreality.beyondrealitycore.blocks;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.handlers.CustomOreBlockHandler;
import com.mcbeyondreality.beyondrealitycore.tileentity.TileAim;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockAim extends Block implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private IIcon top, front;

    private static boolean isBurning;
    private final boolean isBurning2;


    public BlockAim(boolean isActive) {
        super(Material.rock);
        this.setHardness(10000.0F);
        isBurning2 = isActive;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon("beyondrealitycore:aim_side");
        this.front = iconregister.registerIcon(this.isBurning2 ? "beyondrealitycore:aim_front_on" : "beyondrealitycore:aim_front_off");
        this.top = iconregister.registerIcon("beyondrealitycore:aim_top");
    }

    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return top;
        } else if (side == 3) {
            return front;
        } else {
            return this.blockIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.direction(world, x, y, z);
    }

    private void direction(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block direction = world.getBlock(x, y, z - 1);
            Block direction1 = world.getBlock(x, y, z + 1);
            Block direction2 = world.getBlock(x - 1, y, z);
            Block direction3 = world.getBlock(x + 1, y, z);
            byte byte0 = 3;
            if (direction.func_149730_j() && direction.func_149730_j()) {
                byte0 = 3;
            }
            if (direction1.func_149730_j() && direction1.func_149730_j()) {
                byte0 = 2;
            }
            if (direction2.func_149730_j() && direction2.func_149730_j()) {
                byte0 = 5;
            }
            if (direction3.func_149730_j() && direction3.func_149730_j()) {
                byte0 = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, byte0, 2);
        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
        int direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (direction == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (direction == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (direction == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (direction == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        if (itemstack.hasDisplayName()) {
            ((TileAim) world.getTileEntity(x, y, z)).furnaceName(itemstack.getDisplayName());
        }
    }

    public static void updateBlockState(boolean burning, World world, int x, int y, int z) {
        int direction = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        isBurning = true;
        if (burning) {
            world.setBlock(x, y, z, CustomOreBlockHandler.blockAimActive);
        } else {
            world.setBlock(x, y, z, CustomOreBlockHandler.blockAim);
        }
        isBurning = false;
        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
        if (tileentity != null) {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
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
}
