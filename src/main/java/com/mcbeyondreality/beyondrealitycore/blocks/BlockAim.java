package com.mcbeyondreality.beyondrealitycore.blocks;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
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

import javax.swing.text.html.parser.Entity;

public class BlockAim extends Block implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private IIcon top, front;

    public BlockAim() {
        super(Material.rock);
        this.setHardness(10000.0F);

    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon("beyondrealitycore:aim_side");
        //this.front = iconregister.registerIcon(this.isBurning2 ? "beyondrealitycore:aim_front_on" : "beyondrealitycore:aim_front_off");
        this.front = iconregister.registerIcon("beyondrealitycore:aim_front_off");
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
        if (world.isRemote) {
            if (world.getTileEntity(x, y, z) != null) {
                player.openGui(BeyondRealityCore.instance, BeyondRealityCore.GUIs.AIM.ordinal(), world, x, y, z);
                return true;
            }
        }
        return true;
    }
}
