package com.beyondrealitygaming.core.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BRPedestal extends BRUnbreakeableBlock {

    public BRPedestal(String name, CreativeTabs tabs) {
        super(name, tabs);
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
