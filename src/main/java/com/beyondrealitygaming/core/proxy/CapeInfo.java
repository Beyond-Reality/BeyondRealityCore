package com.beyondrealitygaming.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class CapeInfo {

    public static CapeInfo[] capeInfos;

    public static CapeInfo getPlayerCape(String name){
        for (CapeInfo capeInfo : capeInfos) if (Arrays.asList(capeInfo.getPlayerNames()).contains(name))return capeInfo;
        return null;
    }

    private String name;
    private String url;
    private String[] playerNames;
    private ResourceLocation resourceLocation;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public void downloadCape(){
        resourceLocation = new ResourceLocation("beyondreality",name);
        TextureManager manager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject textureObject = manager.getTexture(resourceLocation);
        IImageBuffer imageBuffer = new IImageBuffer() {

            ImageBufferDownload download = new ImageBufferDownload();

            @Override
            public BufferedImage parseUserSkin(BufferedImage image) {
                int imageWidth = 64;
                int imageHeight = 32;
                BufferedImage src = image;
                int srcWidth = src.getWidth();
                int srcHeight = src.getHeight();
                while ((imageWidth < srcWidth) || (imageHeight < srcHeight)){
                    imageWidth *= 2;
                    imageHeight *=2;
                }
                BufferedImage bufferedImage = new BufferedImage(imageWidth,imageHeight,2);
                Graphics graphics = bufferedImage.getGraphics();
                graphics.drawImage(image,0,0,null);
                graphics.dispose();
                return bufferedImage;
            }

            @Override
            public void skinAvailable() {

            }
        };
        ThreadDownloadImageData texture = new ThreadDownloadImageData(null,url,null,imageBuffer);
        manager.loadTexture(resourceLocation,texture);
    }

}
