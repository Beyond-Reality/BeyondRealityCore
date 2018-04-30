package com.beyondrealitygaming.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

public class BRUnbreakableBlock extends BRBlock {

    static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 15);

    protected String name;

    protected BRUnbreakableBlock(String name, CreativeTabs creativeTabs) {
        super(Material.ROCK, name, creativeTabs);
        this.name = name;
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));
        setBlockUnbreakable();
        setHardness(6000000000F);
    }

    @Override
    @Deprecated
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TYPE);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int x : TYPE.getAllowedValues()) {
            items.add(new ItemStack(this, 1, x));
        }
    }

    @Override
    public void registerModels() {
        TYPE.getAllowedValues().forEach((integer) -> {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), integer, new ModelResourceLocation(this.getRegistryName().toString(), "inventory"));
        });
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, state.withProperty(TYPE, stack.getMetadata()));
    }

    @Override
    @Nonnull
    public ItemStack getPickBlock(@Nonnull IBlockState state, RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, getMetaFromState(state));
    }
}
