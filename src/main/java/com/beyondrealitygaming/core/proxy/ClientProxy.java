package com.beyondrealitygaming.core.proxy;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
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
