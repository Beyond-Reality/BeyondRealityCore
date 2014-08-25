package com.mcbeyondreality.beyondrealitycore.transformer;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.SortingIndex(1001)
public class BRLoadingPlugin implements IFMLLoadingPlugin {
	
	public static boolean IN_MCP = false;

	@Override
	public String[] getASMTransformerClass() {
		
		return new String[] { BRClassTransformer.class.getName() };
		
	}

	@Override
	public String getModContainerClass() {
		
		return null;
	}

	@Override
	public String getSetupClass() {
		
		return BRCallHook.class.getName();
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
		IN_MCP = !(Boolean)data.get("runtimeDeobfuscationEnabled");
	}

	@Override
	public String getAccessTransformerClass() {
		
		return null;
	}

}
