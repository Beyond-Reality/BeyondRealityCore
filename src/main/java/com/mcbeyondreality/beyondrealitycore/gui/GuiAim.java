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
import scala.collection.parallel.ParIterableLike;

public class GuiAim extends GuiContainer {

    private ResourceLocation backgroundimage = new ResourceLocation("beyondrealitycore:textures/gui/aim.png");
    public TileAim tileAim;

    public GuiAim(InventoryPlayer invPlayer, TileAim entity) {
        super(new ContainerAim(invPlayer, entity));

        xSize = 176;
        ySize = 166;

        this.tileAim = entity;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float f, int j, int i) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
       this.mc.getTextureManager().bindTexture(backgroundimage);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        //draw powerbar
        float power = this.tileAim.getPowerScaled();
        drawTexturedModalRect(guiLeft + 11 , guiTop + 16, 176, 33 , 17, (int)(55 * power));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
