package com.mcbeyondreality.beyondrealitycore.gui;

import com.mcbeyondreality.beyondrealitycore.data.AIMMachineRecipe;
import com.mcbeyondreality.beyondrealitycore.handlers.AIMMachineRecipeHandler;
import com.mcbeyondreality.beyondrealitycore.tileentity.TileAim;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerAim extends Container {

    private TileAim aim;
    private int lastPower = 0;
    private int lastProgress = 0;

    public ContainerAim(InventoryPlayer invPlayer, TileAim entity) {
        this.aim = entity;

        //Players HotBar
        for(int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
        }
        //Players Inventory
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
            }
        }

        //Aim Slots
        this.addSlotToContainer(new Slot(entity, 0, 56, 34));
        this.addSlotToContainer(new SlotFurnace(invPlayer.player, entity, 1, 116, 35));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return aim.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        Slot slot = getSlot(i);

        if(slot != null && slot.getHasStack()) {

            if(!this.aim.isItemValidForSlot(i, slot.getStack())) return null;
            ItemStack itemstack = slot.getStack();
            ItemStack result = itemstack.copy();

            if(i >= 36) {
                if(!mergeItemStack(itemstack, 0, 36, false)) {
                    return null;
                }
            } else if(!mergeItemStack(itemstack, 36, 36 + aim.getSizeInventory(), false)) {
                return null;
            }

            if(itemstack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            slot.onPickupFromSlot(player, itemstack);
            return result;
        }
        return null;

    }

    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);
        crafter.sendProgressBarUpdate(this, 0, this.aim.getEnergyStored());
        crafter.sendProgressBarUpdate(this, 1, this.aim.processedPercent);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if (this.lastPower != this.aim.getEnergyStored())
                icrafting.sendProgressBarUpdate(this, 0, this.aim.getEnergyStored());

             if (this.lastProgress != this.aim.processedPercent)
                icrafting.sendProgressBarUpdate(this, 1, this.aim.processedPercent);

        }

        this.lastPower = this.aim.getEnergyStored();
        this.lastProgress = this.aim.processedPercent;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int i, int j) {
        switch(i) {
            case 0:
                this.aim.setEnergyStored(j);
                break;
            case 1:
                this.aim.processedPercent = j;
                break;
        }
    }

}
