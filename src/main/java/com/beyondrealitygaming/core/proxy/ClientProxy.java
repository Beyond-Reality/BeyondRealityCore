package com.beyondrealitygaming.core.proxy;


import com.beyondrealitygaming.core.block.BRBlock;
import com.beyondrealitygaming.core.block.BRPedestal;
import com.beyondrealitygaming.core.item.BRColoredItem;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
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
        pedestalList.forEach(pedestal -> ModelLoader.setCustomStateMapper(pedestal, new StateMap.Builder().ignore(BRPedestal.TYPE).build()));
    }

    @Override
    public void init() {
        super.init();
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

}
