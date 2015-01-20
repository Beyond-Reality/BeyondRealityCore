package com.mcbeyondreality.beyondrealitycore.blocks;

import com.mcbeyondreality.beyondrealitycore.handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class MultiBlockUnbreakable extends ItemBlockWithMetadata {

    public MultiBlockUnbreakable(Block block) {
        super(block, block);
        setHasSubtypes(true);
        //setUnlocalizedName("beyondrealitycore:unbreakableBlock");
    }

    @Override
    public int getMetadata (int damageValue) {
        return damageValue;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {

        return getUnlocalizedName() + "_" + Integer.toString(itemstack.getItemDamage());
    }
}
