package com.mcbeyondreality.beyondrealitycore.renderer;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemChairRenderer implements IItemRenderer {

    private ModelChair model;

    public ItemChairRenderer() {
        this.model = new ModelChair();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case ENTITY: {
                GL11.glScalef(0.6F, 0.6F, 0.6F);
                renderCoil(0.0F, 0.0F, 0.0F, item.getItemDamage());
                break;
            }
            case EQUIPPED: {
                GL11.glScalef(0.6F, 0.6F, 0.6F);
                renderCoil(0.0F, 0.0F, 0.0F, item.getItemDamage());
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                GL11.glScalef(0.6F, 0.6F, 0.6F);
                renderCoil(0.0F, -0, 0.6F, item.getItemDamage());
                break;
            }
            case INVENTORY: {
                GL11.glScalef(0.6F, 0.4F, 0.6F);
                renderCoil(0.0F, -1.8F, 0.0F, item.getItemDamage());
                break;
            }
            default:
                break;
        }
    }

    public void renderCoil(float x, float y, float z, int metaData)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("beyondrealitycore" + ":textures/models/chair.png"));

        GL11.glPushMatrix(); //start
        GL11.glTranslatef(x, y, z); //size
        model.render();
        GL11.glPopMatrix(); //end
    }
}
