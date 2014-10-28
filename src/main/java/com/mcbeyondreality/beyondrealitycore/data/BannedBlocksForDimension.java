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

        String[] compare = name.split(":");
        List<String> list = bannedBlocks.get(dimension);
        for(int i = 0; i < list.size(); i++)
        {
            String[] uniqueID = list.get(i).split(":");
            if(uniqueID.length == 3) //Split 3 times, meta is specified
            {
                if(uniqueID[0].equals(compare[0]) && uniqueID[1].equals(compare[1]) && uniqueID[2].equals(compare[2]))
                    return true;
            }
            else if(uniqueID.length == 2) //Modid and name specified
            {
                if(uniqueID[0].equals(compare[0]) && uniqueID[1].equals(compare[1]))
                    return true;
            }
            else
            {
                if(uniqueID[0].equals(compare[0]))
                    return true;
            }
        }
        return list.contains(name);
    }
}
