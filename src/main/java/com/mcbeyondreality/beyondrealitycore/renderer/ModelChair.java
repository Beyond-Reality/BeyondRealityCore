package com.mcbeyondreality.beyondrealitycore.renderer;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelChair {
    private IModelCustom modelChair;

    public ModelChair() {
        modelChair = AdvancedModelLoader.loadModel(new ResourceLocation("beyondrealitycore" + ":models/chair.obj"));
    }

    public void render() {
        modelChair.renderAll();
    }
}
