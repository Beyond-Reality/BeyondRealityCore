package com.mcbeyondreality.beyondrealitycore.event;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.handlers.ConfigHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import java.util.List;

;

public class BeyondRealityCoreEvent {
	
	@SubscribeEvent
	public void onBlockDestroyedByPlayer(BreakEvent event)
	{
		UniqueIdentifier blockName = GameRegistry.findUniqueIdentifierFor(event.block);
		String blockID = blockName.modId + ":" + blockName.name;
		
		if(event.getPlayer().dimension == 1)
		{
			for(int i = 0; i < ConfigHandler.bannedEnderBlocks.length; i++) {
				String splitBlocks[] = ConfigHandler.bannedEnderBlocks[i].toString().split(":");
				
				if (splitBlocks.length == 2) {
					if (blockID.toString().equalsIgnoreCase(ConfigHandler.bannedEnderBlocks[i])) {
						angerEndermen(event.getPlayer(), event.world, event.x, event.y, event.z);
					}
				} else {
					String blockNameID = blockID + ":" + event.blockMetadata;
					if (blockNameID.toString().equalsIgnoreCase(ConfigHandler.bannedEnderBlocks[i]))
						angerEndermen(event.getPlayer(), event.world, event.x, event.y, event.z);
				}
				
			}
		} else if(event.getPlayer().dimension == -1) {
		
			for(int i = 0; i < ConfigHandler.bannedNetherBlocks.length; i++) {
				String splitBlocks[] = ConfigHandler.bannedNetherBlocks[i].toString().split(":");
				
				if (splitBlocks.length == 2) {
					if (blockID.toString().equalsIgnoreCase(ConfigHandler.bannedNetherBlocks[i])) {
						angerPigmen(event.getPlayer(), event.world, event.x, event.y, event.z);
					}
				} else {
					String blockNameID = blockID + ":" + event.blockMetadata;
					if (blockNameID.toString().equalsIgnoreCase(ConfigHandler.bannedNetherBlocks[i]))
						angerPigmen(event.getPlayer(), event.world, event.x, event.y, event.z);
				}
				
			}
			
		} 
	}
	
	
	private void angerEndermen(EntityPlayer player, World world, int x, int y,
			int z) {
		int aggroRange = ConfigHandler.aggrorangeEnd;
		List<?> list = world.getEntitiesWithinAABB(
				EntityEnderman.class,
				AxisAlignedBB.getBoundingBox(x - aggroRange, y - aggroRange, z
						- aggroRange, x + aggroRange + 1, y + aggroRange + 1, z
						+ aggroRange + 1));
		for (int j = 0; j < list.size(); j++) {
			Entity entity1 = (Entity) list.get(j);
			if (entity1 instanceof EntityEnderman) {
				EntityEnderman entityendermen = (EntityEnderman) entity1;
				entityendermen.attackEntityFrom(
						DamageSource.causePlayerDamage(player), 0);
			}
		}
	}

	private void angerPigmen(EntityPlayer player, World world, int x, int y,
			int z) {

		int aggroRange = ConfigHandler.aggrorangeNether;
		List<EntityPigZombie> list = world.getEntitiesWithinAABB(
				EntityPigZombie.class,
				AxisAlignedBB.getBoundingBox(x - aggroRange, y - aggroRange, z
						- aggroRange, x + aggroRange + 1, y + aggroRange + 1, z
						+ aggroRange + 1));
		for (int j = 0; j < list.size(); j++) {
			Entity entity1 = (Entity) list.get(j);
			if (entity1 instanceof EntityPigZombie) {
				EntityPigZombie entitypigmen = (EntityPigZombie) entity1;
				entitypigmen.attackEntityFrom(
						DamageSource.causePlayerDamage(player), 0);
			}
		}
	}
}

