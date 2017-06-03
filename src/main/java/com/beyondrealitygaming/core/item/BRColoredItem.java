package com.beyondrealitygaming.core.item;

import net.minecraft.creativetab.CreativeTabs;

public class BRColoredItem extends BRItem {

    private int color;

    public BRColoredItem(String name, CreativeTabs tabs, int color) {
        super(name, tabs);
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
