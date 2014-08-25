package com.mcbeyondreality.beyondrealitycore.transformer;

import java.io.File;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.relauncher.IFMLCallHook;

public class BRCallHook implements IFMLCallHook {
	
	public static Configuration config;
	public static boolean FASTER_LEAVEDECAY;

	@Override
	public Void call() throws Exception {
		
		File f = new File("config/beyondrealitycore.cfg");
		config = new Configuration(f);
		config.load();
		FASTER_LEAVEDECAY = config.get("VanillaChanges", "FasterLeaveDecay", true ,"Leaves will decay much faster when no longer connected to a log").getBoolean(true);
		if (config.hasChanged())
		config.save();
		
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

}
