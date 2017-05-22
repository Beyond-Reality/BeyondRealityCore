package com.beyondrealitygaming.core.recipe;

public class BRAssemblyRecipe {

    public long power;
    public Item output;
    public Item[] input;

    public class Item {

        public String item;
        public int amount;
        public int metadata;

        @Override
        public String toString() {
            return "Item{" +
                    "item='" + item + '\'' +
                    ", amount=" + amount +
                    ", metadata=" + metadata +
                    '}';
        }
    }
}
