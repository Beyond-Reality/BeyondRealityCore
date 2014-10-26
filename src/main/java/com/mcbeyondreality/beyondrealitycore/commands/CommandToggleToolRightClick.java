package com.mcbeyondreality.beyondrealitycore.commands;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandToggleToolRightClick extends CommandBase
{
    @Override
    public String getCommandName() {
        return "toggleNextSlotPlace";
    }

    @Override
    public String getCommandUsage(ICommandSender user) {
        return "Usage: /toggleNextSlotPlace";
    }

    @Override
    public void processCommand(ICommandSender user, String[] args) {
        if(!BeyondRealityCore.rightClick)
        {
            user.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Tool right click enabled"));
            BeyondRealityCore.rightClick = true;
        }
        else
        {
            user.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Tool right click disabled"));
            BeyondRealityCore.rightClick = false;
        }
    }
}
