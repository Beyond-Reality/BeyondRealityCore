package com.mcbeyondreality.beyondrealitycore.event;

import com.mcbeyondreality.beyondrealitycore.data.BannedBlocksForDimension;
import com.mcbeyondreality.beyondrealitycore.gui.GuiColor;
import com.mcbeyondreality.beyondrealitycore.notification.Notification;
import com.mcbeyondreality.beyondrealitycore.notification.NotificationTickHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class RightClickEvent {

    @SubscribeEvent
    public void onBlockPlace(PlayerInteractEvent event)
    {
        if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
        {
            if(event.entityPlayer.getCurrentEquippedItem() != null) {
                GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(event.entityPlayer.getCurrentEquippedItem().getItem());
                String idName = id.modId + ":" + id.name + ":" + event.entityPlayer.getCurrentEquippedItem().getItemDamage();
                if (BannedBlocksForDimension.isBlockBanned(event.entity.dimension, idName)) {
                    if(event.entityPlayer.worldObj.isRemote)
                        NotificationTickHandler.guiNotification.queueNotification(new Notification(event.entityPlayer.getCurrentEquippedItem(), EnumChatFormatting.RED + "Object Banned", EnumChatFormatting.YELLOW + "You can't use that here"));
                    event.setCanceled(true);
                }

            }
        }
    }

    @SubscribeEvent
    public void onToolTip(ItemTooltipEvent event)
    {
        if (BannedBlocksForDimension.isBlockBanned(event.entity.dimension, event.itemStack.getItem().getUnlocalizedName())) {
            event.toolTip.add(GuiColor.RED + "Banned in this Dimension");
        }
    }
}
