package com.mcbeyondreality.beyondrealitycore.handlers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class CoreHandler {
	
	static Random rng = new Random();
	
	public static void handleLeaveDecay(World worldObj, int posX, int posY, int posZ, Block block)
	{
	
	worldObj.scheduleBlockUpdate(posX, posY, posZ, block, 4 + rng.nextInt(7));
	return;
	
	}

}
