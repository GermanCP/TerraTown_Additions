package com.terratown.terratown_additions.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import scala.util.Random;

public class SugarcaneBlockDeco extends SugarcaneBlock
{

	public SugarcaneBlockDeco(String name, Material material, SoundType sound) {
		super(name, material, sound);
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
