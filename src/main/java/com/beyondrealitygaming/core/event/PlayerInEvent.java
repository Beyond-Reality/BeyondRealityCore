package com.beyondrealitygaming.core.event;


import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerInEvent {

    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player.getName().equals("Buuz135")) {
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("Sound" + FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers().toString());
                    FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers().forEach(entityPlayer ->
                            entityPlayer.connection.sendPacket(new SPacketCustomSound("entity.cat.ambient", SoundCategory.MASTER, entityPlayer.getPosition().getX(), entityPlayer.getPosition().getY(), entityPlayer.getPosition().getZ(), (float) 0.5, (float) 1))
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
