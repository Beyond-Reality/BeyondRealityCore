package com.mcbeyondreality.beyondrealitycore.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.mcbeyondreality.beyondrealitycore.blocks.BlockAim;
import com.mcbeyondreality.beyondrealitycore.data.AIMMachineRecipe;
import com.mcbeyondreality.beyondrealitycore.handlers.AIMMachineRecipeHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

public class TileAim extends TileEntity implements IEnergyHandler, IInventory {

    public static final int MAXENERGY = 10000;
    protected EnergyStorage energy = new EnergyStorage(MAXENERGY, 80, 0);
    private ItemStack[] inventory;
    public long processedRF = 0;
    public long totalRF = 0;
    public int processedPercent = 0;
    public boolean isBurning = false;


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
        this.processedRF = tag.getLong("processedRF");
        this.totalRF = tag.getLong("totalRF");
        this.processedPercent = tag.getInteger("processedPercent");
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
        tag.setLong("processedRF", this.processedRF);
        tag.setLong("totalRF", this.totalRF);
        tag.setInteger("processedPercent", this.processedPercent);

        energy.writeToNBT(tag);
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return energy.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return energy.extractEnergy(maxExtract, simulate);
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

    public float getPowerScaled() {

        return ((float)energy.getEnergyStored() / (float)energy.getMaxEnergyStored()) * 55;
    }

    public int getEnergyStored() {
        return energy.getEnergyStored();
    }

    public void setEnergyStored(int i) {
        energy.setEnergyStored(i);
    }

    @Override
    public void updateEntity() {

        super.updateEntity();
        if(worldObj.isRemote) return;
        ItemStack output = null;
        long requiredRF = 0;

        if (inventory[0] != null) {
            for (AIMMachineRecipe aim : AIMMachineRecipeHandler.aim) {

                Item item = (Item) Item.itemRegistry.getObject(aim.input);
                if (this.inventory[0].getUnlocalizedName().equalsIgnoreCase(item.getUnlocalizedName())) {
                    output = new ItemStack((Item) Item.itemRegistry.getObject(aim.output));
                    requiredRF = aim.rf;
                } else return;
            }
        }
        if (this.inventory[0] != null && this.processedRF == 0) {
            processedRF = requiredRF;
            BlockAim.updateFurnaceBlockState(true, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }

        if (this.processedRF > 0) {
            if (this.inventory[0] == null)  {
                this.processedRF = 0;
                this.processedPercent = 0;
                BlockAim.updateFurnaceBlockState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                return;
            }
            totalRF = this.energy.getEnergyStored();
            long usedRF = Math.min(Math.min(totalRF, this.processedRF), 80);
            this.processedRF -= usedRF;
            this.energy.setEnergyStored((int) (totalRF - usedRF));
            this.processedPercent = Math.round(((float) processedRF / requiredRF) * 100);

        }
            if (this.processedRF <= 0 && inventory[0] != null) {

                if (this.inventory[1] == null) {
                    this.inventory[1] = output.copy();
                } else if (this.inventory[1].getItem() == output.getItem()) {
                    this.inventory[1].stackSize += output.stackSize;
                }

                --this.inventory[0].stackSize;

                if (this.inventory[0].stackSize <= 0) {
                    this.inventory[0] = null;
                }
                this.processedPercent = 0;
                BlockAim.updateFurnaceBlockState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                super.markDirty();


            }

    }

}
