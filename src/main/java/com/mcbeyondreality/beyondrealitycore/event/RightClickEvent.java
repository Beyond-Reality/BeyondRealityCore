package com.mcbeyondreality.beyondrealitycore.event;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.data.BannedBlocksForDimension;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class RightClickEvent {
    private int[] slots = {1, 2, 3, 4, 5, 6, 7, 8, 0, -1};
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerRightClick(PlayerInteractEvent event)
    {
        if (event.isCanceled() || event.world.isRemote || event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || !BeyondRealityCore.rightClick) return;
        ItemStack heldItem = event.entityPlayer.inventory.getCurrentItem();

        if (heldItem == null || !(heldItem.getItem() instanceof ItemTool)) return;
        for(String name : BeyondRealityCore.rightClickBlackList)
        {
            if(heldItem.getUnlocalizedName().equals(name))
                return;
        }
        int oldSlot = event.entityPlayer.inventory.currentItem;
        if (oldSlot < 0 || oldSlot > 8) return;

        int newSlot = slots[oldSlot];
        if (newSlot < 0 || newSlot > 8) return;
        ItemStack slotStack = event.entityPlayer.inventory.getStackInSlot(newSlot);

        if (slotStack == null || slotStack.getItem() instanceof ItemTool) return;

        event.entityPlayer.inventory.currentItem = newSlot;
        if(!((EntityPlayerMP) event.entityPlayer).theItemInWorldManager.activateBlockOrUseItem(event.entityPlayer, event.world, slotStack, event.x, event.y, event.z, event.face, 0.5f, 0.5f, 0.5f))
            return;

        if (slotStack.stackSize <= 0) slotStack = null;
        event.entityPlayer.inventory.currentItem = oldSlot;
        event.entityPlayer.inventory.setInventorySlotContents(newSlot, slotStack);
        ((EntityPlayerMP) event.entityPlayer).playerNetServerHandler.sendPacket(new S2FPacketSetSlot(0, newSlot + 36, slotStack));
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onBlockPlace(PlayerInteractEvent event)
    {
        if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
        {
            if(event.entityPlayer.getCurrentEquippedItem() != null) {

                if (BannedBlocksForDimension.isBlockBanned(event.entity.dimension, event.entityPlayer.getCurrentEquippedItem().getItem().getUnlocalizedName())) {

                    event.entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You Can't Use " +
                            EnumChatFormatting.YELLOW +  event.entityPlayer.getCurrentEquippedItem().getDisplayName() +
                            EnumChatFormatting.RED + " In this Dimension"));

                    event.setCanceled(true);
                }

            }
        }
    }
}
