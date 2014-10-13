package com.mcbeyondreality.beyondrealitycore.IGW;

import igwmod.gui.GuiWiki;
import igwmod.gui.IPageLink;
import igwmod.gui.IReservedSpace;
import igwmod.gui.tabs.IWikiTab;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class BeyondRealityTab implements IWikiTab{
    private static RenderItem itemRenderer;
    private static ItemStack drawingStack;

    static {
        itemRenderer = new RenderItem();
        itemRenderer.setRenderManager(RenderManager.instance);
    }

    @Override
    public String getName(){
        return "Beyond Reality Wiki";
    }

    @Override
    public ItemStack renderTabIcon(GuiWiki gui){
        return new ItemStack(Blocks.crafting_table);
    }

    @Override
    public List<IReservedSpace> getReservedSpaces(){
        return null;
    }

    @Override
    public List<IPageLink> getPages(int[] indexes){
        return null;
    }

    @Override
    public int pagesPerTab(){
        return 16;
    }

    @Override
    public int pagesPerScroll(){
        return 2;
    }

    @Override
    public int getSearchBarAndScrollStartY(){
        return 61;
    }

    @Override
    public void renderForeground(GuiWiki gui, int mouseX, int mouseY){
        if(drawingStack != null) {
            if(drawingStack.getItem() instanceof ItemBlock) {
                gui.renderRotatingBlockIntoGUI(gui, drawingStack, 55, 33, 2.8F);
            } else {
                GL11.glPushMatrix();
                GL11.glTranslated(49, 20, 0);
                GL11.glScaled(2.2, 2.2, 2.2);
                itemRenderer.renderItemAndEffectIntoGUI(gui.getFontRenderer(), gui.mc.getTextureManager(), drawingStack, 0, 0);
                GL11.glPopMatrix();
            }
        }
    }

    @Override
    public void renderBackground(GuiWiki gui, int mouseX, int mouseY){}

    @Override
    public void onMouseClick(GuiWiki gui, int mouseX, int mouseY, int mouseKey){}

    @Override
    public void onPageChange(GuiWiki gui, String pageName, Object... metadata){
        if(metadata.length > 0 && metadata[0] instanceof ItemStack) {
            drawingStack = (ItemStack)metadata[0];
        }
    }
}
