package com.mcbeyondreality.beyondrealitycore.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ChairRenderer extends TileEntitySpecialRenderer {

    public static final ResourceLocation texture = new ResourceLocation("beyondrealitycore" + ":textures/models/chair.png");

    private ModelChair model;

    public ChairRenderer() {
        this.model = new ModelChair();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
        GL11.glScaled(0.6, 0.6, 0.6);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        this.bindTexture(texture);

        this.model.render();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }
}
