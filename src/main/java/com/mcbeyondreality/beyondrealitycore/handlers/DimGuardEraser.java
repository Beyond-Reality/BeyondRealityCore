package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class DimGuardEraser {

    @SubscribeEvent
	public void removeDimensionGuardTags(PlayerEvent.PlayerLoggedInEvent loggedInEvent) {
		EntityPlayer player = loggedInEvent.player;

		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			ItemStack itemStack = player.inventory.getStackInSlot(i);
			
			if (itemStack != null && itemStack.hasTagCompound()) {
				NBTTagCompound compound = itemStack.stackTagCompound;					
				
				if(compound.hasKey("DimensionGuard")) {
					compound.removeTag("DimensionGuard");
					BeyondRealityCore.logger.log(Level.INFO, "Removed DimensionGuard Tags from: " + itemStack.getDisplayName());					
					if(compound.hasNoTags()) {
						// This might be a teeny bit risky.
						BeyondRealityCore.logger.log(Level.INFO, "This should display Empty Brackets {}: " + compound.toString());
						itemStack.setTagCompound(null);
					}
				}
			}
		}
	}
}
