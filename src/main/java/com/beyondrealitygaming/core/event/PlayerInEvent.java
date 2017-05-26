package com.beyondrealitygaming.core.event;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerInEvent {

    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player.getName().equals("Buuz135")) {
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("sending miau");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Minecraft.getMinecraft().player.playSound(new SoundEvent(new ResourceLocation("entity.cat.ambient")), 0.5f, 1);
            }).start();
        }
    }

}
