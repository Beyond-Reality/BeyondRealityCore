package com.mcbeyondreality.beyondrealitycore.handlers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingHandler {

    public static void init() {

        GameRegistry.addRecipe(new ItemStack(CustomItemHandler.gemApatite),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyApatite));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreCopper),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyCopper));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreTin),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyTin));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreLead),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyLead));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreSilver),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinySilver));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreNickel),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyNickel));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreAluminium),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyAluminium));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.orePlatinum),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyPlatinum));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreCadmium),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyCadmium));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreIndium),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyIndium));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreMagnetite),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyMagnetite));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreUranium),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyUranium));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.orePhosphate),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyPhosphate));
        GameRegistry.addRecipe(new ItemStack(CustomOreBlockHandler.oreZinc),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyZinc));
        GameRegistry.addRecipe(new ItemStack(Blocks.iron_ore),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyIron));
        GameRegistry.addRecipe(new ItemStack(Blocks.gold_ore),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyGold));
        GameRegistry.addRecipe(new ItemStack(Items.diamond),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyDiamond));
        GameRegistry.addRecipe(new ItemStack(Items.emerald),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyEmerald));
        GameRegistry.addRecipe(new ItemStack(Items.redstone),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyRedstone));
        GameRegistry.addRecipe(new ItemStack(Items.dye, 1, 4),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyLapis));
        GameRegistry.addRecipe(new ItemStack(Items.coal),
                "xx", "xx", 'x', new ItemStack(CustomItemHandler.tinyCoal));
    }
}
