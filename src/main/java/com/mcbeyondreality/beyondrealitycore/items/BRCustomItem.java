package com.mcbeyondreality.beyondrealitycore.items;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import net.minecraft.item.Item;

public class BRCustomItem extends Item {
     public BRCustomItem(String name) {
         this.setUnlocalizedName("beyondrealitycore:" + name);
         this.setTextureName("beyondrealitycore:" + name);
         this.setCreativeTab(BeyondRealityCore.tabBeyondReality);
     }
}
