package com.mcbeyondreality.beyondrealitycore.gui;

import com.mcbeyondreality.beyondrealitycore.tileentity.TileAim;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import scala.Int;
import scala.collection.parallel.ParIterableLike;

import java.util.ArrayList;
import java.util.List;

public class GuiAim extends GuiContainer {

    private ResourceLocation backgroundimage = new ResourceLocation("beyondrealitycore:textures/gui/aim.png");
    private TileAim tileAim;
    private float mouseX, mouseY;

    public GuiAim(InventoryPlayer invPlayer, TileAim entity) {
        super(new ContainerAim(invPlayer, entity));

        xSize = 176;
        ySize = 166;
        float test = entity.getPowerScaled();
        this.tileAim = entity;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float f, int j, int i) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
       this.mc.getTextureManager().bindTexture(backgroundimage);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        //draw power bar
        int power = (int) this.tileAim.getPowerScaled();

        int progress = this.tileAim.processedPercent == 0 ? 23 : Math.round(((this.tileAim.processedPercent / 100F) * 23));
        //int progress = Math.round(((this.tileAim.processedPercent / 100F) * 23));
        drawTexturedModalRect(guiLeft + 11 , guiTop + (71 - power), 176, 88 - power, 17, power);
        //draw progress bar
        drawTexturedModalRect(guiLeft + 78 , guiTop + 35, 176 , 14, 23 - progress, 16 );
    }

    @Override
    public void drawGuiContainerForegroundLayer(int i, int j) {
        String s1 = "Usage: 80 RF/T";
        String s2 =  Integer.toString(tileAim.getEnergyStored()) + "/" + Integer.toString(this.tileAim.MAXENERGY);
        String s3 = "Progress: " + Integer.toString((100 - tileAim.processedPercent)) + "%";
        this.fontRendererObj.drawString(s1, this.xSize / 2 - this.fontRendererObj.getStringWidth(s1) / 2, 6, 0x000000);

        int k = (this.width - this.xSize) /2;
        int l = (this.height - this.ySize) /2;
        if(mouseX > guiLeft + 10 && mouseX < guiLeft + 28) {
            if(mouseY > guiTop + 15 && mouseY < guiTop + 71) {
                List list = new ArrayList();
                list.add(s2);
                this.drawHoveringText(list, (int)mouseX - k, (int)mouseY - l, this.fontRendererObj);
            }
        }
        if (tileAim.processedPercent != 0) {
            if (mouseX > guiLeft + 80 && mouseX < guiLeft + 100) {
                if (mouseY > guiTop + 32 && mouseY < guiTop + 50) {
                    List list = new ArrayList();
                    list.add(s3);
                    this.drawHoveringText(list, (int) mouseX - k, (int) mouseY - l, this.fontRendererObj);
                }
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        this.mouseX = (float)x;
        this.mouseY = (float)y;
        super.drawScreen(x, y, f);
    }

}
