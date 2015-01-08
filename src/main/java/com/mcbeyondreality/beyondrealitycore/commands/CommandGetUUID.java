package com.mcbeyondreality.beyondrealitycore.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class CommandGetUUID extends CommandBase {

	@Override
	public String getCommandName() {
		return "uuid";
	}

	@Override
	public String getCommandUsage(ICommandSender iCommandSender) {
		return "Usage: /uuid <Player>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {

		if(args.length <= 0)
			throw new WrongUsageException("Please provide a valid username" , new Object[0]);
		else
		{
			EntityPlayerMP player;

			player = this.getPlayer(sender, args[0]);

			EntityPlayerMP user = this.getCommandSenderAsPlayer(sender);
			user.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "The UUID for player " + args[0] + " is: " + player.getUniqueID()));
		}

	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args)
	{
		return args.length != 1 ? null : getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
	}
}
