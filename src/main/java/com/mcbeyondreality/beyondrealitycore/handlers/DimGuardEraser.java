package com.mcbeyondreality.beyondrealitycore.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class DimGuardEraser {

    public static void scanInventoryAndRemove(EntityPlayer player){
        for (int i=0;i<player.inventory.getSizeInventory();i++) {
            ItemStack itemStack=player.inventory.getStackInSlot(i);
            if( itemStack != null && itemStack.stackTagCompound != null && itemStack.stackTagCompound.hasKey("DimensionGuard")) {
                itemStack.stackTagCompound.removeTag("DimensionGuard");
            }
        }
    }

    private int tickCount=0;
    private int triggerTick=20;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (tickCount++==triggerTick){
            scanInventoryAndRemove(event.player);
            tickCount=0;
        }
    }
}
