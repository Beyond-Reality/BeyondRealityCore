package com.pauljoda.beyondrealitycoremod.handlers;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import com.pauljoda.beyondrealitycoremod.BeyondRealityCore;

public class BeyondRealityCoreEvent {
	
	@ForgeSubscribe
	public void onBlockBreak(BreakEvent event)
	{
		if(event.getPlayer().dimension == 1)
		{
			for(int i = 0; i < BeyondRealityCore.bannedEnderBlocks.length; i++)
			{
				int blockId;
				int meta;
				if(BeyondRealityCore.bannedEnderBlocks[i].contains(":"))
				{
					String splitID[] = BeyondRealityCore.bannedEnderBlocks[i].split(":");
					blockId = Integer.parseInt(splitID[0]);
					meta = Integer.parseInt(splitID[1]);
				}

				else
				{
					blockId = Integer.parseInt(BeyondRealityCore.bannedEnderBlocks[i]);
					meta = 0;
				}

				if(blockId == event.block.blockID && meta == event.blockMetadata)
				{
					angerEndermen(event.getPlayer(), event.world, event.x, event.y, event.z);
				}

			}
		}
	}

private void angerEndermen(EntityPlayer player, World world, int x, int y, int z)
{
	int aggroRange = BeyondRealityCore.aggroRange;
	List<?> list = world.getEntitiesWithinAABB(EntityEnderman.class,
			AxisAlignedBB.getBoundingBox(x - aggroRange, y - aggroRange, z - aggroRange, x + aggroRange + 1, y + aggroRange + 1, z + aggroRange + 1));
	for(int j = 0; j < list.size(); j++)
	{
		Entity entity1 = (Entity)list.get(j);
		if(entity1 instanceof EntityEnderman)
		{
			EntityEnderman entityendermen = (EntityEnderman)entity1;
			entityendermen.attackEntityFrom(DamageSource.causePlayerDamage(player), 0);
		}
	}
}

}
