package com.beyondrealitygaming.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;

public class BRPedestal extends BRUnbreakeableBlock {

    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool UP = PropertyBool.create("up");
    public BRPedestal(String name, CreativeTabs tabs) {
        super(name, tabs);
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(DOWN, false)
                        .withProperty(UP, false)
        );
    }

    private boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        return block instanceof BRPedestal;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, DOWN, UP, TYPE);
    }

    @Override
    public void registerModels()
    {
        TYPE.getAllowedValues().forEach((integer) -> {
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(TYPE).build());
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), integer, new ModelResourceLocation((this.getRegistryName().toString()), "inventory"));
        });
    }

    /**
     * Get the actual Block state of this Block at the given position.
     * This applies properties not visible in the metadata,
     * such as pedestal UP and DOWN connections.
     */
    @Override
    @Deprecated
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(UP, canPedestalConnectTo(worldIn, pos, EnumFacing.UP))
                .withProperty(DOWN, canPedestalConnectTo(worldIn, pos, EnumFacing.DOWN));
    }

    private boolean canPedestalConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos other = pos.offset(facing);
        Block block = world.getBlockState(other).getBlock();
        return block.canBeConnectedTo(world, other, facing.getOpposite()) || canConnectTo(world, other, facing.getOpposite());
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess access, BlockPos pos) {
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
