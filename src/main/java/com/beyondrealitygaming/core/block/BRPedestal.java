package com.beyondrealitygaming.core.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BRPedestal extends BRUnbreakeableBlock {

    private static final AxisAlignedBB bounds = new AxisAlignedBB(0.0d, 0.0d, 0.0d, 1.0d, 2.0d,1.0d);

    public BRPedestal(String name, CreativeTabs tabs) {
        super(name, tabs);
    }

    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        return bounds;
    }

    @Deprecated
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state) {
        return bounds;
    }

    @Override
    @Deprecated
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean somebool) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, bounds);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess access, BlockPos blockpos) {
        return 15;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }


}
