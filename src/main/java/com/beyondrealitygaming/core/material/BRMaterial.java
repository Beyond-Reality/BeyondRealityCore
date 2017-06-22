package com.beyondrealitygaming.core.material;

import com.beyondrealitygaming.core.block.BROre;
import com.beyondrealitygaming.core.item.BRColoredItem;
import com.beyondrealitygaming.core.item.BRItemBlock;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.text.WordUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.beyondrealitygaming.core.proxy.ClientProxy.registerBlockModel;
import static com.beyondrealitygaming.core.proxy.ClientProxy.registerVariantItem;

public class BRMaterial {

    public static CreativeTabs materialTab;
    public static List<BRMaterial> materialList = new ArrayList<>();

    public static void registerMaterials(File materials) throws IOException {
        FileReader reader = new FileReader(materials);
        MaterialInfo[] materialInfos = new Gson().fromJson(new JsonReader(reader), MaterialInfo[].class);
        for (MaterialInfo materialInfo : materialInfos) new BRMaterial(materialInfo);
        reader.close();
    }

    private BROre ore;
    private BROre sporadic;
    private BROre sparse;
    private BRColoredItem sparseItem;
    private BRColoredItem chunkItem;
    private BRColoredItem ingot;
    private BRColoredItem nugget;
    private BRColoredItem dust;
    private BRColoredItem tinyDust;
    private String mat;


    public BRMaterial(MaterialInfo info) {
        if (materialTab == null) materialTab = new CreativeTabs("brMaterial") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(Blocks.IRON_ORE);
            }
        };
        String type = info.name;
        this.mat = info.type;
        int color = (int) Long.parseLong(info.color, 16);
        //BLOCKS
        if (info.ore) {
            GameRegistry.register(ore = new BROre(Material.ROCK, "ore" + type, materialTab, color, info.mining, mat));
            GameRegistry.register(new BRItemBlock(ore, materialTab).setHasSubtypes(true));
            if (info.oredict == null) OreDictionary.registerOre("ore" + WordUtils.capitalize(type), ore);
            else {
                for (String s : info.oredict) {
                    OreDictionary.registerOre(s, ore);
                }
            }
        }
        if (info.sporadic) {
            GameRegistry.register(sporadic = new BROre(Material.ROCK, "sporadic" + type, materialTab, color, info.mining, mat));
            GameRegistry.register(new BRItemBlock(sporadic, materialTab).setHasSubtypes(true));
            OreDictionary.registerOre("sporadic" + WordUtils.capitalize(type), sporadic);
        }

        if (info.sparse) {
            GameRegistry.register(sparse = new BROre(Material.ROCK, "sparse" + type, materialTab, color, info.mining, mat));
            GameRegistry.register(new BRItemBlock(sparse, materialTab).setHasSubtypes(true));
            OreDictionary.registerOre("sparse" + WordUtils.capitalize(type), sparse);
        }
        //ITEMS
        if (info.sporadic) {
            GameRegistry.register(chunkItem = new BRColoredItem((mat.equals("METAL") ? "chunk" : "shard") + type, materialTab, color));
            sporadic.setDrop(chunkItem);
        }
        if (info.sparse) {
            GameRegistry.register(sparseItem = new BRColoredItem((mat.equals("METAL") ? "tiny" : "tiny_shard") + type, materialTab, color));
            sparse.setDrop(sparseItem);
        }
        if (info.ingot) {
            GameRegistry.register(ingot = new BRColoredItem((mat.equals("METAL") ? "ingot" : "gem") + type, materialTab, color));
            OreDictionary.registerOre((mat.equals("METAL") ? "ingot" : "gem") + WordUtils.capitalize(type), ingot);
            GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "nnn", "nnn", "nnn", 'n', "nugget" + WordUtils.capitalize(type)));
            if (mat.equals("GEM")) {
                ore.setDrop(ingot);
            }
        }
        if (info.nugget) {
            GameRegistry.register(nugget = new BRColoredItem("nugget" + type, materialTab, color));
            OreDictionary.registerOre("nugget" + WordUtils.capitalize(type), nugget);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), "ingot" + WordUtils.capitalize(type)));
        }
        if (info.dust) {
            GameRegistry.register(dust = new BRColoredItem("dust" + type, materialTab, color));
            OreDictionary.registerOre("dust" + WordUtils.capitalize(type), dust);
            GameRegistry.addRecipe(new ShapedOreRecipe(dust, "ttt", "ttt", "ttt", 't', "tinyDust" + WordUtils.capitalize(type)));
            if (info.ingot) {
                GameRegistry.addSmelting(dust, new ItemStack(ingot), 0);
            }
        }
        if (info.tinyDust) {
            GameRegistry.register(tinyDust = new BRColoredItem("tinyDust" + type, materialTab, color));
            OreDictionary.registerOre("tinyDust" + WordUtils.capitalize(type), tinyDust);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(tinyDust, 9), "dust" + WordUtils.capitalize(type)));
        }

        materialList.add(this);
    }


    public void registerModels() {
        if (ore != null) registerBlockModel(ore, "ore");
        if (sporadic != null) {
            registerBlockModel(sporadic, "sporadic");
            registerVariantItem(chunkItem, mat.equals("METAL") ? "chunk" : "shard");
        }
        if (sparse != null) {
            registerBlockModel(sparse, "sparse");
            registerVariantItem(sparseItem, mat.equals("METAL") ? "sparse" : "tiny_shard");
        }
        if (ingot != null) {
            registerVariantItem(ingot, mat.equals("METAL") ? "ingot" : "gem");
        }
        if (nugget != null) {
            registerVariantItem(nugget, "nugget");
        }
        if (dust != null) {
            registerVariantItem(dust, "dust");
        }
        if (tinyDust != null) {
            registerVariantItem(tinyDust, "tiny_dust");
        }
    }


    public BRColoredItem getIngot() {
        return ingot;
    }

    public BRColoredItem getSparseItem() {
        return sparseItem;
    }

    public BRColoredItem getChunkItem() {
        return chunkItem;
    }

    public BRColoredItem getNugget() {
        return nugget;
    }

    public BRColoredItem getDust() {
        return dust;
    }

    public BRColoredItem getTinyDust() {
        return tinyDust;
    }

    public BROre getOre() {
        return ore;
    }

    public BROre getSporadic() {
        return sporadic;
    }

    public BROre getSparse() {
        return sparse;
    }
}
