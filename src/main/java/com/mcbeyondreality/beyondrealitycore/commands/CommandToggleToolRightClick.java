package com.mcbeyondreality.beyondrealitycore.commands;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import com.mcbeyondreality.beyondrealitycore.handlers.ConfigHandler;
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
        if(!ConfigHandler.rightClick)
        {
            user.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Tool right click enabled"));
            ConfigHandler.rightClick = true;
        }
        else
        {
            user.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Tool right click disabled"));
            ConfigHandler.rightClick = false;
        }
    }
}
