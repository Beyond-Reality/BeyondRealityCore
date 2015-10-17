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
        pageEntries.add("page1");
        pageEntries.add("page2");
		pageEntries.add("page3");
		pageEntries.add("page4");
		pageEntries.add("page5");
		pageEntries.add("page6");
		pageEntries.add("page7");
		pageEntries.add("page8");
		pageEntries.add("page9");
		pageEntries.add("page10");
		pageEntries.add("page11");
		pageEntries.add("page12");
		pageEntries.add("page13");
		pageEntries.add("page14");
		pageEntries.add("page15");
		pageEntries.add("page16");
		pageEntries.add("page17");
		pageEntries.add("page18");
		pageEntries.add("page19");
		pageEntries.add("page20");
		pageEntries.add("page21");
		pageEntries.add("page22");
		pageEntries.add("page23");
		pageEntries.add("page24");
		pageEntries.add("page25");
		pageEntries.add("page26");
		pageEntries.add("page27");
		pageEntries.add("page28");
		pageEntries.add("page29");
		pageEntries.add("page30");
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