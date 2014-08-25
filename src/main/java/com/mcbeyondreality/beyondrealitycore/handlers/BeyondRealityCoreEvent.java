package com.mcbeyondreality.beyondrealitycore.handlers;

import java.util.List;
import java.util.Random;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.block.Block;
import net.minecraft.command.CommandGive;;

public class BeyondRealityCoreEvent {
	
	@SubscribeEvent
	public void onBlockDestroyedByPlayer(BreakEvent event)
	{
		UniqueIdentifier blockName = GameRegistry.findUniqueIdentifierFor(event.block);
		String blockID = blockName.modId + ":" + blockName.name;
		
		if(event.getPlayer().dimension == 1)
		{
			for(int i = 0; i < BeyondRealityCore.bannedEnderBlocks.length; i++) {
				String splitBlocks[] = BeyondRealityCore.bannedEnderBlocks[i].toString().split(":");
				
				if (splitBlocks.length == 2) {
					if (blockID.toString().equalsIgnoreCase(BeyondRealityCore.bannedEnderBlocks[i])) {
						angerEndermen(event.getPlayer(), event.world, event.x, event.y, event.z);
					}
				} else {
					String blockNameID = blockID + ":" + event.blockMetadata;
					if (blockNameID.toString().equalsIgnoreCase(BeyondRealityCore.bannedEnderBlocks[i]))
						angerEndermen(event.getPlayer(), event.world, event.x, event.y, event.z);
				}
				
			}
		} else if(event.getPlayer().dimension == -1) {
		
			for(int i = 0; i < BeyondRealityCore.bannedNetherBlocks.length; i++) {
				String splitBlocks[] = BeyondRealityCore.bannedNetherBlocks[i].toString().split(":");
				
				if (splitBlocks.length == 2) {
					if (blockID.toString().equalsIgnoreCase(BeyondRealityCore.bannedNetherBlocks[i])) {
						angerPigmen(event.getPlayer(), event.world, event.x, event.y, event.z);
					}
				} else {
					String blockNameID = blockID + ":" + event.blockMetadata;
					if (blockNameID.toString().equalsIgnoreCase(BeyondRealityCore.bannedNetherBlocks[i]))
						angerPigmen(event.getPlayer(), event.world, event.x, event.y, event.z);
				}
				
			}
			
		} 
	}
	
	
	private void angerEndermen(EntityPlayer player, World world, int x, int y,
			int z) {
		int aggroRange = BeyondRealityCore.aggrorangeEnd;
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

		int aggroRange = BeyondRealityCore.aggrorangeNether;
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

