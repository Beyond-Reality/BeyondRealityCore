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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BRUnbreakeableBlock extends BRBlock {

    public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 15);

    private String name;

    public BRUnbreakeableBlock(String name, CreativeTabs creativeTabs) {
        super(Material.ROCK, name, creativeTabs);
        this.name = name;
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));
        setBlockUnbreakable();
        setHardness(6000000000F);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TYPE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
        for (int x : TYPE.getAllowedValues()) {
            list.add(new ItemStack(itemIn, 1, x));
        }
    }

    public void registerModels() {
        TYPE.getAllowedValues().forEach(integer -> ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), integer, new ModelResourceLocation(this.getRegistryName().toString(), "inventory")));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, state.withProperty(TYPE, stack.getMetadata()));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, getMetaFromState(state));
    }
}