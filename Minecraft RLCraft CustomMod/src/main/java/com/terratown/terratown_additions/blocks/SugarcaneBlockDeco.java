package com.terratown.terratown_additions.blocks;

import com.terratown.terratown_additions.Main;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import scala.util.Random;

public class SugarcaneBlockDeco extends SugarcaneBlock
{

	public SugarcaneBlockDeco(String name, Material material, SoundType sound, CreativeTabs tab) {
		super(name, material, sound, tab);
		//Light Opacity
		setLightOpacity(0);
	}
	
	//-------------------------------------------------------------
	//render properties
		
		@Override
		public boolean isOpaqueCube(IBlockState state)
		{
			return false;
		}
	
}
