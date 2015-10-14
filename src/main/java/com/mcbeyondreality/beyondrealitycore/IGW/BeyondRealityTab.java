package com.mcbeyondreality.beyondrealitycore.IGW;

import com.mcbeyondreality.beyondrealitycore.handlers.CustomBlockHandler;
import igwmod.gui.GuiWiki;
import igwmod.gui.tabs.BaseWikiTab;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class BeyondRealityTab extends BaseWikiTab {
    private static RenderItem itemRenderer;
    private static ItemStack drawingStack;

    public BeyondRealityTab() {
        itemRenderer = new RenderItem();
        itemRenderer.setRenderManager(RenderManager.instance);
        pageEntries.add("changelog");
        pageEntries.add("Tutorial");
    }

    @Override
    public String getName(){
        return "Beyond Reality Wiki";
    }

    @Override
    public ItemStack renderTabIcon(GuiWiki gui){
        return new ItemStack(CustomBlockHandler.wikiBlock);
    }

    @Override
    protected String getPageName(String s) {
        return I18n.format("tab.entry." + s);
    }

    @Override
    protected String getPageLocation(String s) {
        return s;
    }
}