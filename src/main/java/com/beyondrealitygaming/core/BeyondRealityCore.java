package com.beyondrealitygaming.core;


import com.beyondrealitygaming.core.material.BRMaterial;
import com.beyondrealitygaming.core.proxy.CommonProxy;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


import java.util.Arrays;

@Mod(name = "Beyond Reality Core", modid = "beyondreality", version = "2.0.2")
public class BeyondRealityCore {

    @Mod.Instance("beyondreality")
    public static BeyondRealityCore instance;

    @SidedProxy(clientSide = "com.beyondrealitygaming.core.proxy.ClientProxy", serverSide = "com.beyondrealitygaming.core.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BRMaterial.registerMaterials();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()){
            if (recipe instanceof ShapedRecipes){
                ShapedRecipes shapedRecipes = (ShapedRecipes) recipe;
                for (IRecipe r : CraftingManager.getInstance().getRecipeList()){
                    if (r instanceof ShapedRecipes){
                        ShapedRecipes shaped = (ShapedRecipes) r;
                        if (Arrays.equals(shaped.recipeItems, shapedRecipes.recipeItems)){
                            System.out.println(shaped.getRecipeOutput().toString() +" conflicts "+shapedRecipes.getRecipeOutput().toString());
                        }
                    }
                }
            }
        }
    }

}
