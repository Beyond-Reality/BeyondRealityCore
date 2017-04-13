package com.beyondrealitygaming.core.proxy;


import com.beyondrealitygaming.core.event.TooltipEvent;
import com.beyondrealitygaming.core.material.BRMaterial;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        for (BRMaterial material : BRMaterial.materialList){
            material.registerModels();
        }
        MinecraftForge.EVENT_BUS.register(new TooltipEvent());
    }
}
