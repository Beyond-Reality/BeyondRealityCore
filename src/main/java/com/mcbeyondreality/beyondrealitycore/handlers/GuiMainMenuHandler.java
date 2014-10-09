package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.BRCGuiMainMenu;
import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.ArrayList;
import java.util.List;

public class GuiMainMenuHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) throws NoSuchFieldException {
        if (event.gui instanceof GuiMainMenu) {
            Minecraft.getMinecraft().displayGuiScreen(new BRCGuiMainMenu());
        }
    }

    public static List<String> getBrandings()
    {
        List<String>brands = new ArrayList<String>();
        for(int i = 0; i < BeyondRealityCore.bottomLeftBranding.length; i++)
            brands.add(BeyondRealityCore.bottomLeftBranding[i]);
        return brands;
    }
}