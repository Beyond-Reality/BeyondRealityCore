package com.mcbeyondreality.beyondrealitycore.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.mcbeyondreality.beyondrealitycore.data.AIMMachineRecipe;
import com.mcbeyondreality.beyondrealitycore.handlers.AIMMachineRecipeHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
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
        energy.writeToNBT(tag);
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return energy.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return 0;
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
        for(AIMMachineRecipe aim: AIMMachineRecipeHandler.aim) {
            String blocks[] = aim.input.split(":");
            Block block = GameRegistry.findBlock(blocks[0], blocks[1]);
            if (itemstack.getUnlocalizedName().equalsIgnoreCase(block.getUnlocalizedName())) {
                return true;
            }
        }
        return false;
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

    private ItemStack canTransform() {
        if (this.inventory[0] != null) {

            for (AIMMachineRecipe aim : AIMMachineRecipeHandler.aim) {
                String blocks[] = aim.input.split(":");
                Block block = GameRegistry.findBlock(blocks[0], blocks[1]);
                if (this.inventory[0].getUnlocalizedName().equalsIgnoreCase(block.getUnlocalizedName())) {
                    String blockoutput[] = aim.output.split(":");
                    Block output = GameRegistry.findBlock(blockoutput[0], blockoutput[1]);
                    ItemStack itemstack = new ItemStack(output);

                    if (this.inventory[1] == null) return itemstack;
                    if(!this.inventory[1].isItemEqual(itemstack)) return null;
                    int result = this.inventory[0].stackSize + itemstack.stackSize;
                    if (this.inventory[0].getMaxStackSize() < result) return null;
                    return itemstack;
                }
            }
        }

        return null;
    }

    public void transformItem() {
        ItemStack output = canTransform();

        if (output == null) return;

        if (this.inventory[1] == null) {
            this.inventory[1] = output.copy();
        } else if (this.inventory[1].getItem() == output.getItem()) {
            this.inventory[1].stackSize += output.stackSize; // Forge BugFix: Results may have multiple items
        }

        --this.inventory[0].stackSize;

        if (this.inventory[0].stackSize <= 0) {
            this.inventory[0] = null;
        }

    }


    @Override
    public void updateEntity() {

        boolean flag = this.processedRF > 0;
        boolean flag1 = false;

        if (this.processedRF > 0)
        {
            processedRF -= this.energy.getEnergyStored() > 80 ? 80 : this.getEnergyStored();
        }

        if (!this.worldObj.isRemote)
        {
            if (this.processedRF > 0 || this.inventory[0] != null)
            {
                if (this.furnaceBurnTime == 0 && this.canSmelt())
                {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

                    if (this.furnaceBurnTime > 0)
                    {
                        flag1 = true;

                        if (this.furnaceItemStacks[1] != null)
                        {
                            --this.furnaceItemStacks[1].stackSize;

                            if (this.furnaceItemStacks[1].stackSize == 0)
                            {
                                this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.furnaceCookTime;

                    if (this.furnaceCookTime == 200)
                    {
                        this.furnaceCookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.furnaceCookTime = 0;
                }
            }

            if (flag != this.furnaceBurnTime > 0)
            {
                flag1 = true;
                BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }

    }

}
