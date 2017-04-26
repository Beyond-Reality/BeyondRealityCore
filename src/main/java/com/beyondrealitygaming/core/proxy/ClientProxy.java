package com.beyondrealitygaming.core.proxy;

import com.beyondrealitygaming.core.event.TooltipEvent;
import com.beyondrealitygaming.core.material.BRMaterial;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.io.FileUtils;
import org.lwjgl.Sys;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class ClientProxy extends CommonProxy {

    private static final String info = "https://raw.githubusercontent.com/Beyond-Reality/Beyond-Realty-Farscapes/master/capes/capeinfo.json";

    @Override
    public void init() {
        for (BRMaterial material : BRMaterial.materialList){
            material.registerModels();
        }
        MinecraftForge.EVENT_BUS.register(new TooltipEvent());
    }

    @Override
    public void postInit() {
        super.postInit();
        downloadInfoCapes();
        RenderManager manager = Minecraft.getMinecraft().getRenderManager();
        Map<String, RenderPlayer> map = manager.getSkinMap();
        map.get("default").addLayer(new CapeRendererLayer( map.get("default")));
        map.get("slim").addLayer(new CapeRendererLayer( map.get("slim")));
    }

    private void downloadInfoCapes(){
        try {
            File capeInfo = new File("./tempinfo.json");
            capeInfo.createNewFile();
            FileUtils.copyURLToFile(new URL(info),capeInfo);
            FileReader reader = new FileReader(capeInfo);
            CapeInfo.capeInfos = new Gson().fromJson(new JsonReader(reader),CapeInfo[].class);
            reader.close();
            FileUtils.forceDelete(capeInfo);
            for (CapeInfo info : CapeInfo.capeInfos) {
                for (String name : info.getPlayerNames()) System.out.println(name);
                info.downloadCape();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
