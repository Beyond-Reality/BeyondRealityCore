package com.beyondrealitygaming.core.proxy;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SimpleItemMeshDefinition implements ItemMeshDefinition {
    protected String modelName;
    protected String variants;

    public SimpleItemMeshDefinition(String modelname, String vars) {
        modelName = modelname;
        variants = vars;
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        return new ModelResourceLocation(new ResourceLocation("beyondreality:" + modelName), variants);
    }
}