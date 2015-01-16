package com.mcbeyondreality.beyondrealitycore.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

public class TileAim extends TileEntity implements IEnergyReceiver, IInventory {

    private static EnergyStorage energy = new EnergyStorage(10000, 80, 0);
    private ItemStack[] inventory;

    public TileAim() {

        inventory = new ItemStack[2];
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {

        super.readFromNBT(tag);
        NBTTagList list = tag.getTagList("ItemsAim", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound item = list.getCompoundTagAt(i);
            int slot = item.getByte("SlotAim");
            if(slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
        energy.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {

        super.writeToNBT(tag);

        NBTTagList list = new NBTTagList();
        for(int i = 0; i < getSizeInventory(); i++) {
            ItemStack itemstack = getStackInSlot(i);
            if(itemstack != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("SlotAim", (byte) i);
                itemstack.writeToNBT(item);
                list.appendTag(item);
            }
        }
        tag.setTag("ItemsAim", list);

        energy.writeToNBT(tag);
    }

    @Override
    public void updateEntity() {

    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return energy.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return energy.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }


    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int slot, int count) {
        ItemStack itemstack = getStackInSlot(slot);

        if(itemstack != null) {
            if(itemstack.stackSize <= count) {
                setInventorySlotContents(slot, null);
            }
            itemstack = itemstack.splitStack(count);

        }
        super.markDirty();
        return itemstack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        inventory[slot] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
        //onInventoryChanged();
        super.markDirty();
    }

    @Override
    public String getInventoryName() {
        return "beyondreality:AIM";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return true;
    }

    public static float getPowerScaled() {
        return (float)energy.getEnergyStored() / (float)energy.getMaxEnergyStored();
    }
}
