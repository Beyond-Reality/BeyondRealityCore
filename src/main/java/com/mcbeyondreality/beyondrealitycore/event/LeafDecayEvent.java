package com.mcbeyondreality.beyondrealitycore.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLog;
import net.minecraft.item.ItemAxe;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Random;

public class LeafDecayEvent {
    @SubscribeEvent
    public void handleLeafDecay(BlockEvent.BreakEvent event)
    {
        if (event.isCanceled() || event.world.isRemote) return;

        if(event.block instanceof BlockLog)
        {
            if(event.getPlayer().inventory.getCurrentItem().getItem() instanceof ItemAxe)
            {
                Random r = new Random();
                for(int i = 0; i < 3; i++)
                {
                    for(int j = 0; j < 3; j++)
                    {
                        for(int k = 0; k < 3; k++)
                        {

                        }
                    }
                }
            }
        }
    }
}
