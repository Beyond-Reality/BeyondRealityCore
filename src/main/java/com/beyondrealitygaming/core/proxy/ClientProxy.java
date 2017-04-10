package com.beyondrealitygaming.core.proxy;


import com.beyondrealitygaming.core.material.BRMaterial;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        System.out.println("Registering models");
        for (BRMaterial material : BRMaterial.materialList){
            material.registerModels();
        }
    }
}
