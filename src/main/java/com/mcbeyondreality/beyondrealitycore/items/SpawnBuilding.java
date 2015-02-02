package com.mcbeyondreality.beyondrealitycore.items;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.handlers.ConfigHandler;
import ivorius.reccomplex.worldgen.StructureInfo;
import ivorius.reccomplex.worldgen.StructureRegistry;
import ivorius.reccomplex.worldgen.WorldGenStructures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnBuilding extends Item {

    public SpawnBuilding() {
        this.setUnlocalizedName("beyondrealitycore:itemSpawnBuilding");
        this.setTextureName("beyondrealitycore:spawnbuilding");
        this.setCreativeTab(BeyondRealityCore.tabBeyondReality);
        //this.setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int i, float f1, float f2, float f3) {

        String structureName = ConfigHandler.spawnBuildingName;
        StructureInfo structureInfo = StructureRegistry.getStructure(structureName);

        if(structureInfo == null) {
            return false;
        } else {
            /*int x = player.getPlayerCoordinates().posX;
            int z = player.getPlayerCoordinates().posZ;
            if(args.length >= 3) {
                x = MathHelper.floor_double(func_110666_a(commandSender, (double) x, args[1]));
                z = MathHelper.floor_double(func_110666_a(commandSender, (double)z, args[2]));
            }*/

            WorldGenStructures.generateStructureRandomly(world, world.rand, structureInfo, x, z, false);
        }

        return false;
    }
}
