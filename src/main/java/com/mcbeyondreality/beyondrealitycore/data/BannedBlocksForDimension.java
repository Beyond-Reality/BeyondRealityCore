package com.mcbeyondreality.beyondrealitycore.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannedBlocksForDimension
{
    private static Map<Integer, List<String>> bannedBlocks = new HashMap<Integer, List<String>>();

    public static void addBannedBlockToDimension(int dimension, String name)
    {
        if (bannedBlocks.containsKey(dimension))
            bannedBlocks.get(dimension).add(name);
        else {
            List<String> newList = new ArrayList<String>();
            newList.add(name);
            bannedBlocks.put(dimension, newList);
        }
    }

    public static boolean isBlockBanned(int dimension, String name)
    {
        if(!bannedBlocks.containsKey(dimension))
            return false;
        List<String> list = bannedBlocks.get(dimension);
        return list.contains(name);
    }
}
