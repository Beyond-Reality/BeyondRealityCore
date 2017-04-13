package com.beyondrealitygaming.core.event;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TooltipEvent {


    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event){
        if (!event.getItemStack().isEmpty() && event.getItemStack().getItem().getRegistryName().getResourcePath().contains("sparse")){
            event.getToolTip().add("Craft in a 2x2 to get a full ore");
        }
    }
}
