package com.beyondrealitygaming.core.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;
import java.util.Map;

public class BRPedestal extends BRUnbreakableBlock {
    private static final PropertyBool DOWN = PropertyBool.create("down");
    private static final PropertyBool UP = PropertyBool.create("up");
    private static final String cname = "beyondreality:pedestal";
    protected BRPedestal(String name, CreativeTabs tabs) {
        super(name, tabs);
        setLightLevel(1.0F);
        setDefaultState(
            blockState.getBaseState()
            .withProperty(DOWN, false)
            .withProperty(UP, false)
        );
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, DOWN, UP, TYPE);
    }

    @Override
    public void registerModels() {
        TYPE.getAllowedValues().forEach((type) -> {
            ModelLoader.setCustomStateMapper(this, new StateMapper());
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type, new ModelResourceLocation(cname, "inventory"));
        });
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        return world.getBlockState(pos) == world.getBlockState(pos.offset(facing));
    }

    /**
     * Get the actual Block state of this Pedestal at the given position.
     * This applies run-time non-persistent properties not visible in the metadata,
     * such as pedestal UP and DOWN connections with same pedestal type blocks.
     */
    @Override
    @Deprecated
    @Nonnull
    public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(UP, canBeConnectedTo(worldIn, pos, EnumFacing.UP))
                    .withProperty(DOWN, canBeConnectedTo(worldIn, pos, EnumFacing.DOWN));
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public static class StateMapper extends StateMapperBase {

        private StateMapper() {
        }

        @Override
        @Nonnull
        protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
            ResourceLocation loc = new ResourceLocation(cname);
            String propstr = getPropertyString(state.getProperties());
            return new ModelResourceLocation(loc, propstr);
        }

        @Override
        @Nonnull
        public String getPropertyString(Map< IProperty<?>, Comparable<? >> values) {
            StringBuilder stringbuilder = new StringBuilder();

            for (Map.Entry<IProperty<?>, Comparable<? >> entry : values.entrySet())
            {
                IProperty<?> iproperty = (IProperty)entry.getKey();

                if(!iproperty.equals(TYPE)) {
                    if (stringbuilder.length() != 0)
                    {
                        stringbuilder.append(",");
                    }
                    stringbuilder.append(iproperty.getName());
                    stringbuilder.append("=");
                    stringbuilder.append(this.getPropertyName(iproperty, entry.getValue()));
                }
            }

            return stringbuilder.toString();
        }

        @Nonnull
        @SuppressWarnings("unchecked")
        private <T extends Comparable<T>> String getPropertyName(IProperty<T> property, Comparable<?> value) {
            return property.getName((T)value);
        }
    }
}
