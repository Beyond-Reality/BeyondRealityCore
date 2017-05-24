package com.beyondrealitygaming.core.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class BRPedestal extends BRUnbreakeableBlock {


    public BRPedestal(String name) {
        super(name);
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

    @Override
    public void registerModels() {
        for (int x : TYPE.getAllowedValues()){
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), x, new ModelResourceLocation(new ResourceLocation(this.getRegistryName().toString().toLowerCase()), "normal"));
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), x, new ModelResourceLocation(new ResourceLocation(this.getRegistryName().toString().toLowerCase()), "inventory"));
        }
    }
}
