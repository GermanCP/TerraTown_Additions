package com.terratown.terratown_additions.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class Fishglass extends BlockBase{

	public static final PropertyInteger FILL = PropertyInteger.create("fill", 0, 3);
	
	
	public Fishglass(String name, Material material, SoundType sound) {
		super(name, material, sound);
		
		//set Block properties, adjust as necessary
		
				//Hardness
					setHardness(0.5F);
				//Resistance to explosions
					setResistance(2.0F);
				//HarvestLevel
					//setHarvestLevel("hoe", 0);
				//Lightemission
					//setLightLevel(0);
				//Light Opacity
					setLightOpacity(0);
				//Breakable?
					//setBlockUnbreakable();
				
		
	}
	
	//-------------------------------------------------------------
	//blockstate
	@Override
	protected BlockStateContainer createBlockState()
	{	
		return new BlockStateContainer(this, new IProperty[] {FILL});
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FILL);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FILL,meta);
	}
	
	
	//-------------------------------------------------------------
	//updating blockstate
	
	
	
	//-------------------------------------------------------------
	//render properties
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	 @Override
	public boolean isTranslucent(IBlockState state)
	{
	        return true;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer()
	{
	        return BlockRenderLayer.TRANSLUCENT;
	}
	 
}
