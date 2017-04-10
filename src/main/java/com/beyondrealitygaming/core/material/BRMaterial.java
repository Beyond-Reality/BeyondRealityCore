package com.beyondrealitygaming.core.material;

import com.beyondrealitygaming.core.block.BRBlock;
import com.beyondrealitygaming.core.item.BRItem;
import com.beyondrealitygaming.core.item.BRItemBlock;
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

import java.util.ArrayList;
import java.util.List;

public class BRMaterial {

    public static CreativeTabs materialTab;
    public static List<BRMaterial> materialList = new ArrayList<BRMaterial>();

    private BRBlock ore;
    private BRBlock sparse;
    private BRItem sparseItem;
    private BRItem dust;
    private BRItem nugget;
    private BRItem ingot;

    public BRMaterial(String type, boolean createOre, boolean createSparse, boolean createDust, boolean createNugget, boolean createIngot) {
        if (materialTab == null) materialTab = new CreativeTabs("brMaterial") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(Blocks.IRON_ORE);
            }
        };
        if (createOre) {
            GameRegistry.register(ore = new BRBlock(Material.ROCK, "ore" + type, materialTab));
            GameRegistry.register(new BRItemBlock(ore, materialTab));
        }
        if (createSparse) {
            GameRegistry.register(sparse = new BRBlock(Material.ROCK, "sparse" + type, materialTab));
            GameRegistry.register(new BRItemBlock(sparse, materialTab));
            GameRegistry.register(sparseItem = new BRItem("sparseItem"+type,materialTab));
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

    public void registerModels(){
        if (ore != null) Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(ore),0, new ModelResourceLocation(new ResourceLocation(ore.getRegistryName().toString().toLowerCase()), "inventory"));
    }
}
