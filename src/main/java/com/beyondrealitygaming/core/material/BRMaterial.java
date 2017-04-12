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
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class BRMaterial {

    public static CreativeTabs materialTab;
    public static List<BRMaterial> materialList = new ArrayList<BRMaterial>();

    public static void registerMaterials(){
//        new BRMaterial("aluminium", true, true, true,true,true);
//        new BRMaterial("apatite", true, true, true,true,true);
//        new BRMaterial("cadmium", true, true, true,true,true);
//        new BRMaterial("copper", true, true, true,true,true);
//        new BRMaterial("indium", true, true, true,true,true);
//        new BRMaterial("lead", true, true, true,true,true);
//        new BRMaterial("magnetite", true, true, true,true,true);
//        new BRMaterial("nickel", true, true, true,true,true);
//        new BRMaterial("platinum", true, true, true,true,true);
//        new BRMaterial("silver", true, true, true,true,true);
//        new BRMaterial("tin", true, true, true,true,true);
//        new BRMaterial("uranium", true, true, true,true,true);
        new BRMaterial("ruby", true, true, false,false,false);
        new BRMaterial("tin", true, true, false,false,false);
        new BRMaterial("copper", true, true, false,false,false);
        new BRMaterial("nickel", true, true, false,false,false);
        new BRMaterial("platinum", true, true, false,false,false);
        new BRMaterial("lead", true, true, false,false,false);
        new BRMaterial("sapphire", true, true, false,false,false);
        new BRMaterial("iridium", true, true, false,false,false);
        new BRMaterial("silver", true, true, false,false,false);
        new BRMaterial("peridot", true, true, false,false,false);
        new BRMaterial("coal", false, true, false,false,false, Blocks.COAL_ORE);
        new BRMaterial("diamond", false, true, false,false,false,Blocks.DIAMOND_ORE);
        new BRMaterial("emerald", false, true, false,false,false, Blocks.EMERALD_ORE);
        new BRMaterial("gold", false, true, false,false,false, Blocks.GOLD_ORE);
        new BRMaterial("iron", false, true,false,false,false,Blocks.IRON_ORE);
        new BRMaterial("lapis", false, true, false,false,false,Blocks.LAPIS_ORE);
        new BRMaterial("redstone", false,true,false,false,false,Blocks.REDSTONE_ORE);
        new BRMaterial("thorium", true, true, false,false,false);
        new BRMaterial("uranium", true, true, false,false,false);
        new BRMaterial("boron", true, true, false,false,false);
        new BRMaterial("lithium", true, true, false,false,false);
        new BRMaterial("magnesium", true, true, false,false,false);
        new BRMaterial("bauxite", true, true, false,false,false);
        new BRMaterial("tungsten", true, true, false,false,false);
    }

    private BRBlock ore;
    private BROre sparse;
    private BRItem sparseItem;
    private BRItem dust;
    private BRItem nugget;
    private BRItem ingot;

    public BRMaterial(String type, boolean createOre, boolean createSparse, boolean createDust, boolean createNugget, boolean createIngot, Block sparseRecipe) {
        if (materialTab == null) materialTab = new CreativeTabs("brMaterial") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(Blocks.IRON_ORE);
            }
        };
        if (createOre) {
            GameRegistry.register(ore = new BRBlock(Material.ROCK, "ore" + type, materialTab));
            GameRegistry.register(new BRItemBlock(ore, materialTab));
            OreDictionary.registerOre("ore"+ WordUtils.capitalize(type),ore);
        }
        if (createSparse) {
            GameRegistry.register(sparse = new BROre(Material.ROCK, "sparse" + type, materialTab));
            GameRegistry.register(new BRItemBlock(sparse, materialTab));
            GameRegistry.register(sparseItem = new BRItem("tiny"+type,materialTab));
            sparse.setDrop(sparseItem);
            GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(sparseRecipe == null ? ore : sparseRecipe)), new Object[] {"##", "##", '#',sparseItem});
        }
        if (createDust) {
            GameRegistry.register(dust = new BRItem("dust" + type, materialTab));
        }
        if (createNugget) {
            GameRegistry.register(nugget = new BRItem("nugget" + type, materialTab));
        }
        if (createIngot) {
            GameRegistry.register(ingot = new BRItem("ingot" + type, materialTab));
        }
        materialList.add(this);
    }
    public BRMaterial(String type, boolean createOre, boolean createSparse, boolean createDust, boolean createNugget, boolean createIngot) {
        this(type,createOre,createSparse,createDust,createNugget,createIngot,null);
    }

    public void registerModels(){
        if (ore != null) registerBlockModel(ore);
        if (sparse != null) {
            registerBlockModel(sparse);
            registerItem(sparseItem);
        }
    }

    public void registerBlockModel(Block block){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),0, new ModelResourceLocation(new ResourceLocation(block.getRegistryName().toString().toLowerCase()), "normal"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),0,new ModelResourceLocation(new ResourceLocation(block.getRegistryName().toString().toLowerCase()),"inventory"));
    }

    public void registerItem(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,0, new ModelResourceLocation(new ResourceLocation(item.getRegistryName().toString().toLowerCase()), "inventory"));
    }
}
