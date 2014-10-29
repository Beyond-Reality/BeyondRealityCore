package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.notification.Notification;
import com.mcbeyondreality.beyondrealitycore.notification.NotificationTickHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;

public class PlayerLogInHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc.theWorld != null) {
            if (BeyondRealityCore.displayWikiHelp && mc.theWorld.isRemote) {
                NotificationTickHandler.guiNotification.queueNotification(new Notification(new ItemStack(Items.writable_book), EnumChatFormatting.DARK_BLUE + "Press 'I' for help", EnumChatFormatting.BLUE + "Contains Pack Info"));
                BeyondRealityCore.displayWikiHelp = false;
                BeyondRealityCore.set(Configuration.CATEGORY_GENERAL, "Display 'Press I to open In Game Wiki' on log in", false);
            }
        }
    }
}
