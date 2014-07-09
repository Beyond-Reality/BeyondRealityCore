package beyondrealitycore.handlers;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import beyondrealitycore.BeyondRealityCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BeyondRealityCoreEvent {
	
	@SubscribeEvent
	public void onBlockDestroyedByPlayer(BreakEvent event)
	{
		if(event.getPlayer().dimension == 1)
		{
			for(int i = 0; i < BeyondRealityCore.bannedEnderBlocks.length; i++)
			{
				String blockId;

				blockId = BeyondRealityCore.bannedEnderBlocks[i];
				
				if(event.block.getUnlocalizedName().equals(blockId))
				{
					angerEndermen(event.getPlayer(), event.world, event.x, event.y, event.z);
				}

			}
		} else if(event.block.getUnlocalizedName().equals("tile.stone")) {
			
			if (!event.world.isRemote)
			{
				//int rnd1 = (int)(Math.random() * ((BeyondRealityCore.numSilverfish - 0) + 1)); 			
				//int rnd2 = (int)(Math.random() * ((100 - 0) + 1));
				Random random = new Random();
				//Random random2 = new Random();
				
				int rnd1 = random.nextInt(100);
				int rnd2 = random.nextInt(BeyondRealityCore.numSilverfish) + 1;
				
				//System.out.println(String.valueOf(rnd1));
				//System.out.println(String.valueOf(rnd2));
				
				if (rnd1 <= BeyondRealityCore.perSilverfish)
				{
					for(int i = 0; i < rnd2; i++)
					{
						EntitySilverfish entitysilverfish = new EntitySilverfish(event.world);
						entitysilverfish.setLocationAndAngles((double)event.x + 0.5D, (double)event.y, (double)event.z + 0.5D, 0.0F, 0.0F);
						event.world.spawnEntityInWorld(entitysilverfish);
						entitysilverfish.spawnExplosionParticle();
					}
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
