package com.beyondrealitygaming.core.proxy;

import com.beyondrealitygaming.core.block.BRPedestal;
import com.beyondrealitygaming.core.block.BRUnbreakableBlock;
import com.beyondrealitygaming.core.event.PlayerInEvent;
import com.beyondrealitygaming.core.proxy.registry.BlockRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommonProxy {

    public static Logger logger;
    public static File configFolder;
    public static List<BRPedestal> pedestalList = new ArrayList<>();
    public static List<BRUnbreakableBlock> unbreakableBlocks = new ArrayList<>();
    public static CreativeTabs buildingBlocks = new CreativeTabs("brBuildingBlocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.BRICK_BLOCK);
        }
    };

    public void preInit(FMLPreInitializationEvent event) throws IOException {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new PlayerInEvent());
        MinecraftForge.EVENT_BUS.register(new BlockRegistry());
        configFolder = new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + "brcore");
        if (!configFolder.exists()) {
            try {
                if(!configFolder.mkdir()) {
                    logger.printf(Level.ERROR, "Could not create config folder: %s", configFolder.toString());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        File configFile = new File(configFolder.getAbsolutePath() + File.separator + "brcore.cfg");
        if (!configFile.exists()) {
            try {
                if(!configFile.createNewFile()) {
                    logger.printf(Level.ERROR, "Could not create config file: %s", configFile.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Configuration configuration = new Configuration(configFile);
        for (int i = 0, amountOfPedestals = configuration.getInt("amountOfPedestals", Configuration.CATEGORY_GENERAL, 1, 0, Integer.MAX_VALUE, "The amount of pedestal multiplied by 16 that will be generated"); i < amountOfPedestals; ++i) {
            BRPedestal pedestal = new BRPedestal("pedestal" + i , buildingBlocks){
                @Override
                public void registerItem(IForgeRegistry<Item> itemIForgeRegistry) {
                    itemIForgeRegistry.register(new ItemBlock(this) {
                        @Override
                        @Nonnull
                        public String getUnlocalizedName(ItemStack stack) {
                            return getUnlocalizedName() + "." + stack.getItemDamage();
                        }

                        @Override
                        @Nonnull
                        public String getItemStackDisplayName(@Nonnull ItemStack stack) {
                            return "Unbreakable Pedestal Block";
                        }

                        @Override
                        public void addInformation(@Nullable ItemStack stack, @Nullable World worldIn, @Nullable List<String> tooltip, @Nullable ITooltipFlag flagIn) {
                            super.addInformation(stack, worldIn, tooltip, flagIn);
                            if (tooltip != null) {
                                tooltip.add("Can be stacked vertically for higher pedestals.");
                                if (stack != null) {
                                    ResourceLocation registryName = stack.getItem().getRegistryName();
                                    if (registryName != null) {
                                        tooltip.add("Type: "+registryName.getResourcePath().replaceAll("pedestal", ""));
                                    }
                                    tooltip.add("Meta: "+stack.getItemDamage());
                                }
                            }
                        }
                    }.setCreativeTab(buildingBlocks).setRegistryName(this.getRegistryName()+"").setHasSubtypes(true));
                }
            };
            logger.printf(Level.INFO, "adding %s to pedestalList.", pedestal.getUnlocalizedName());
            pedestalList.add(pedestal);
        }
        for (int i = 0, amountOfUnbreakableBlocks = configuration.getInt("amountOfUnbreakableBlocks", Configuration.CATEGORY_GENERAL, 1, 0, Integer.MAX_VALUE, "The amount of unbreakable blocks multiplied by 16 that will be generated"); i < amountOfUnbreakableBlocks; ++i) {
            BRUnbreakableBlock block = new BRUnbreakableBlock("unbreakable" + i, buildingBlocks){
                @Override
                public void registerItem(IForgeRegistry<Item> itemIForgeRegistry) {
                    itemIForgeRegistry.register(new ItemBlock(this) {
                        @Override
                        @Nonnull
                        public String getItemStackDisplayName(@Nonnull ItemStack stack) {
                            return "Unbreakable Block";
                        }

                        @Override
                        public void addInformation(@Nullable ItemStack stack, @Nullable World worldIn, @Nullable List<String> tooltip, @Nullable ITooltipFlag flagIn) {
                            super.addInformation(stack, worldIn, tooltip, flagIn);
                            if (tooltip != null) {
                                if (stack != null) {
                                    ResourceLocation registryName = stack.getItem().getRegistryName();
                                    if (registryName != null) {
                                        tooltip.add("Type: " + registryName.getResourcePath().replaceAll("unbreakeable", ""));
                                    }
                                    tooltip.add("Meta: " + stack.getItemDamage());
                                }
                            }
                        }
                    }.setHasSubtypes(true).setCreativeTab(buildingBlocks).setRegistryName(this.getRegistryName()+""));
                }
            };
            unbreakableBlocks.add(block);
        }
        configuration.save();
    }

    public void init() {

    }

    public void postInit() throws IOException {
//        File assemblyRecipes = new File(configFolder.getAbsolutePath() + File.separator + "assemblyRecipes.json");
//        if (assemblyRecipes.exists() && Loader.isModLoaded("buildcraftsilicon")) {
//            FileReader reader = new FileReader(assemblyRecipes);
//            BRAssemblyRecipe[] recipes = new Gson().fromJson(new JsonReader(reader), BRAssemblyRecipe[].class);
//            int amount = 0;
//            for (BRAssemblyRecipe brAssemblyRecipe : recipes) {
//                if (Item.getByNameOrId(brAssemblyRecipe.output.item) != null) {
//                    long power = brAssemblyRecipe.power;
//                    ItemStack output = new ItemStack(Item.getByNameOrId(brAssemblyRecipe.output.item), brAssemblyRecipe.output.amount, brAssemblyRecipe.output.metadata);
//                    ImmutableSet.Builder<StackDefinition> inputs = ImmutableSet.<StackDefinition>builder();
//                    for (BRAssemblyRecipe.Item i : brAssemblyRecipe.input) {
//                        if (i.item.contains(":") && Item.getByNameOrId(i.item) != null) {
//                            inputs.add(ArrayStackFilter.definition(new ItemStack(Item.getByNameOrId(i.item), 1, i.metadata)));
//                        } else {
//                            inputs.add(OreStackFilter.definition(i.item));
//                        }
//                    }
//                    BuildcraftRecipeRegistry.assemblyRecipes.addRecipe(new AssemblyRecipe("brcustomrecipe" + amount, power, inputs.build(), output));
//                }
//                ++amount;
//            }
//            reader.close();
//        }
    }
}
