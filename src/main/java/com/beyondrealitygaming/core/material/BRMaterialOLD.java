package com.beyondrealitygaming.core.material;

import com.beyondrealitygaming.core.block.BRBlock;
import com.beyondrealitygaming.core.block.BROre;
import com.beyondrealitygaming.core.item.BRItem;
import com.beyondrealitygaming.core.item.BRItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class BRMaterialOLD {

    public static CreativeTabs materialTab;
    public static List<BRMaterialOLD> materialList = new ArrayList<BRMaterialOLD>();

    public static void registerMaterials() {//24
        new BRMaterialOLD("ruby", true, true, true);
        new BRMaterialOLD("tin", true, true, true);
        new BRMaterialOLD("copper", true, true, true);
        new BRMaterialOLD("nickel", true, true, true);
        new BRMaterialOLD("platinum", true, true, true);
        new BRMaterialOLD("lead", true, true, true);
        new BRMaterialOLD("sapphire", true, true, true);
        new BRMaterialOLD("iridium", true, true, true);
        new BRMaterialOLD("silver", true, true, true);
        new BRMaterialOLD("peridot", true, true, true);
        new BRMaterialOLD("coal", false, true, true, Blocks.COAL_ORE);
        new BRMaterialOLD("diamond", false, true, true, Blocks.DIAMOND_ORE);
        new BRMaterialOLD("emerald", false, true, true, Blocks.EMERALD_ORE);
        new BRMaterialOLD("gold", false, true, true, Blocks.GOLD_ORE);
        new BRMaterialOLD("iron", false, true, true, Blocks.IRON_ORE);
        new BRMaterialOLD("lapis", false, true, true, Blocks.LAPIS_ORE);
        new BRMaterialOLD("redstone", false, true, true, Blocks.REDSTONE_ORE);
        new BRMaterialOLD("thorium", true, true, true);
        new BRMaterialOLD("uranium", true, true, true);
        new BRMaterialOLD("boron", true, true, true);
        new BRMaterialOLD("lithium", true, true, true);
        new BRMaterialOLD("magnesium", true, true, true);
        new BRMaterialOLD("bauxite", true, true, true);
        new BRMaterialOLD("tungsten", true, true, true);
    }

    private BRBlock ore;
    private BROre sparse;
    private BRItem sparseItem;
    private BROre sporadic;


    public BRMaterialOLD(String type, boolean createOre, boolean createSparse, boolean createSporadic, Block sparseRecipe) {
        if (materialTab == null) materialTab = new CreativeTabs("brMaterial") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(Blocks.IRON_ORE);
            }
        };
        if (createOre) {
            GameRegistry.register(ore = new BRBlock(Material.ROCK, "ore" + type, materialTab));
            GameRegistry.register(new BRItemBlock(ore, materialTab));
            OreDictionary.registerOre("ore" + WordUtils.capitalize(type), ore);
        }
        if (createSparse) {
            GameRegistry.register(sparse = new BROre(Material.ROCK, "sparse" + type, materialTab, 0, 0));
            GameRegistry.register(new BRItemBlock(sparse, materialTab));
            GameRegistry.register(sparseItem = new BRItem("tiny" + type, materialTab));
            sparse.setDrop(sparseItem);
            GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(sparseRecipe == null ? ore : sparseRecipe)), new Object[]{"##", "##", '#', sparseItem});
            OreDictionary.registerOre("oreSparse" + WordUtils.capitalize(type), sparse);
        }
        if (createSporadic) {
            GameRegistry.register(sporadic = new BROre(Material.ROCK, "sporadic" + type, materialTab, 0, 0));
            GameRegistry.register(new BRItemBlock(sporadic, materialTab));
            sporadic.setDrop(sparseItem);
            OreDictionary.registerOre("oreSporadic" + WordUtils.capitalize(type), sporadic);
        }
        materialList.add(this);
    }

    public BRMaterialOLD(String type, boolean createOre, boolean createSparse, boolean createSporadic) {
        this(type, createOre, createSparse, createSporadic, null);
    }

    public void registerModels() {
        if (ore != null) registerBlockModel(ore);
        if (sparse != null) {
            registerBlockModel(sparse);
            registerItem(sparseItem);
        }
        if (sporadic != null) {
            registerBlockModel(sporadic);
        }
    }

    public void registerBlockModel(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(block.getRegistryName().toString().toLowerCase()), "normal"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(block.getRegistryName().toString().toLowerCase()), "inventory"));
    }

    public void registerItem(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(new ResourceLocation(item.getRegistryName().toString().toLowerCase()), "inventory"));
    }
}