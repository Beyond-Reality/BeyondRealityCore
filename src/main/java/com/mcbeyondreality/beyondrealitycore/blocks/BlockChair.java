package com.mcbeyondreality.beyondrealitycore.blocks;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.tileentity.TileEntityChair;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockChair extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BlockChair(int i) {
        super(Material.iron);
		setHardness(-1F);
        setBlockName("beyondrealitycore:chair:" + i);
        setCreativeTab(BeyondRealityCore.tabBeyondReality);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.icon = iconregister.registerIcon("beyondrealitycore:lock");
    }

    public IIcon getIcon(int side, int meta) {
        return this.icon;
    }

    /*@Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }*/

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
        if(world.isRemote)
            sitOnBlock(world, x, y, z, player, 9 * 0.0625);
        return true;
    }

    public static boolean sitOnBlock(World par1World, double x, double y, double z, EntityPlayer par5EntityPlayer, double par6) {
        if (!checkForExistingEntity(par1World, x, y, z, par5EntityPlayer)) {
            EntitySeat nemb = new EntitySeat(par1World, x, y, z, par6);
            par1World.spawnEntityInWorld(nemb);
            par5EntityPlayer.mountEntity(nemb);
        }
        return true;
    }

    public static boolean checkForExistingEntity(World par1World, double x, double y, double z, EntityPlayer par5EntityPlayer) {
        List<EntitySeat> listEMB = par1World.getEntitiesWithinAABB(EntitySeat.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
        for (EntitySeat mount : listEMB) {
            if (mount.blockPosX == x && mount.blockPosY == y && mount.blockPosZ == z) {
                if (mount.riddenByEntity == null) {
                    par5EntityPlayer.mountEntity(mount);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityChair();
    }
}
