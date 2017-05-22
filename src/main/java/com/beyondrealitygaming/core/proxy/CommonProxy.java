package com.beyondrealitygaming.core.proxy;


import buildcraft.api.recipes.AssemblyRecipe;
import buildcraft.api.recipes.BuildcraftRecipeRegistry;
import buildcraft.api.recipes.StackDefinition;
import buildcraft.lib.inventory.filter.ArrayStackFilter;
import buildcraft.lib.inventory.filter.OreStackFilter;
import com.beyondrealitygaming.core.material.BRMaterial;
import com.beyondrealitygaming.core.recipe.BRAssemblyRecipe;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CommonProxy {

    public static File configFolder;

    public void preInit(FMLPreInitializationEvent event) throws IOException {
        BRMaterial.registerMaterials();
        configFolder = new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + "brcore");
        if (!configFolder.exists()) configFolder.mkdir();
    }

    public void init() {
    }

    public void postInit() throws IOException {
        File assemblyRecipes = new File(configFolder.getAbsolutePath() + File.separator + "assemblyRecipes.json");
        if (assemblyRecipes.exists() && Loader.isModLoaded("buildcraftsilicon")) {
            FileReader reader = new FileReader(assemblyRecipes);
            BRAssemblyRecipe[] recipes = new Gson().fromJson(new JsonReader(reader), BRAssemblyRecipe[].class);
            int amount = 0;
            for (BRAssemblyRecipe brAssemblyRecipe : recipes) {
                if (Item.getByNameOrId(brAssemblyRecipe.output.item) != null) {
                    long power = brAssemblyRecipe.power;
                    ItemStack output = new ItemStack(Item.getByNameOrId(brAssemblyRecipe.output.item), brAssemblyRecipe.output.amount, brAssemblyRecipe.output.metadata);
                    ImmutableSet.Builder<StackDefinition> inputs = ImmutableSet.<StackDefinition>builder();
                    for (BRAssemblyRecipe.Item i : brAssemblyRecipe.input) {
                        if (i.item.contains(":") && Item.getByNameOrId(i.item) != null) {
                            inputs.add(ArrayStackFilter.definition(new ItemStack(Item.getByNameOrId(i.item), 1, i.metadata)));
                        } else {
                            inputs.add(OreStackFilter.definition(i.item));
                        }
                    }
                    BuildcraftRecipeRegistry.assemblyRecipes.addRecipe(new AssemblyRecipe("brcustomrecipe" + amount, power, inputs.build(), output));
                }
                ++amount;
            }
            reader.close();
        }
    }
}
