package com.mcbeyondreality.beyondrealitycore.IGW;

import igwmod.gui.GuiWiki;
import igwmod.gui.tabs.BaseWikiTab;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class BeyondRealityTab extends BaseWikiTab {
    private static RenderItem itemRenderer;
    private static ItemStack drawingStack;

    public BeyondRealityTab() {
        itemRenderer = new RenderItem();
        itemRenderer.setRenderManager(RenderManager.instance);
        pageEntries.add("changelog");
        pageEntries.add("gregguide");
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
    protected String getPageName(String s) {
        return I18n.format("tab.entry." + s);
    }

    @Override
    protected String getPageLocation(String s) {
        return s;
    }
}
