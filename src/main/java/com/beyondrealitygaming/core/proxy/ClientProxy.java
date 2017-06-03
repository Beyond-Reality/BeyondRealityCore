package com.beyondrealitygaming.core.proxy;

import com.beyondrealitygaming.core.block.BROre;
import com.beyondrealitygaming.core.block.BRPedestal;
import com.beyondrealitygaming.core.block.BRUnbreakeableBlock;
import com.beyondrealitygaming.core.event.PlayerInEvent;
import com.beyondrealitygaming.core.item.BRColoredItem;
import com.beyondrealitygaming.core.material.BRMaterial;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class ClientProxy extends CommonProxy {

    private static final String info = "https://raw.githubusercontent.com/Beyond-Reality/Beyond-Realty-Farscapes/master/capes/capeinfo.json";

    @Override
    public void preInit(FMLPreInitializationEvent event) throws IOException {
        super.preInit(event);

        MinecraftForge.EVENT_BUS.register(new PlayerInEvent());

        pedestalList.forEach(BRPedestal::registerModels);
        BRMaterial.materialList.forEach(BRMaterial::registerModels);
        unbreakeableBlocks.forEach(BRUnbreakeableBlock::registerModels);
    }

    @Override
    public void init() {
        super.init();

        for (BRMaterial material : BRMaterial.materialList) {
            registerColor(material.getIngot(), 0);
            registerColor(material.getChunkItem(), 1);
            registerColor(material.getSparseItem(), 1);
            registerColor(material.getNugget(), 0);
            registerColor(material.getDust(), 0);
            registerColor(material.getTinyDust(), 0);
            registerColor(material.getOre(), 0);
            registerColor(material.getSporadic(), 0);
            registerColor(material.getSparse(), 0);
        }

    }

    @Override
    public void postInit() throws IOException {
        super.postInit();
        downloadInfoCapes();
        RenderManager manager = Minecraft.getMinecraft().getRenderManager();
        Map<String, RenderPlayer> map = manager.getSkinMap();
        map.get("default").addLayer(new CapeRendererLayer(map.get("default")));
        map.get("slim").addLayer(new CapeRendererLayer(map.get("slim")));
    }

    private void downloadInfoCapes() {
        try {
            File capeInfo = new File("./tempinfo.json");
            capeInfo.createNewFile();
            FileUtils.copyURLToFile(new URL(info), capeInfo);
            FileReader reader = new FileReader(capeInfo);
            CapeInfo.capeInfos = new Gson().fromJson(new JsonReader(reader), CapeInfo[].class);
            reader.close();
            FileUtils.forceDelete(capeInfo);
            for (CapeInfo info : CapeInfo.capeInfos) {
                info.downloadCape();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerColor(BRColoredItem coloredItem, int index) {
        if (coloredItem != null) registerColor(coloredItem, coloredItem.getColor(), index);
    }

    private void registerColor(BROre ore, int index) {
        if (ore != null) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                if (tintIndex == index && stack.getItem().equals(Item.getItemFromBlock(ore))) {
                    return ore.getColor();
                }
                return 0xFFFFFF;
            }, ore);
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
                if (tintIndex == index && state.getBlock().equals(ore)) {
                    return ore.getColor();
                }
                return 0xFFFFFF;
            }, ore);
        }

    }

    private void registerColor(Item item, int color, int index) {
        if (item != null) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                if (tintIndex == index && stack.getItem().equals(item)) {
                    return color;
                }
                return 0xFFFFFF;
            }, item);
        }
    }

    public static void registerBlockModel(Block block, String type) {
        ModelLoader.setCustomStateMapper(block, blockIn -> {
            Map<IBlockState, ModelResourceLocation> map = Maps.newHashMap();
            map.put(blockIn.getDefaultState(), new ModelResourceLocation(new ResourceLocation("beyondreality", "blocks/metal" + type), "normal"));
            return map;
        });
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(block), new SimpleItemMeshDefinition("blocks/metal" + type, "normal"));
    }

    public static void registerVariantItem(Item item, String type) {
        ModelLoader.setCustomMeshDefinition(item, new SimpleItemMeshDefinition("items/metalitem", "type=" + type));
        registerItem(item, "items/metalItem", "type=" + type);
    }

    public static void registerItem(Item item, String name, String variants) {
        ModelLoader.registerItemVariants(item, new ModelResourceLocation(new ResourceLocation("beyondreality", name), variants));
    }
}
