package com.terratown.terratown_additions.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SugarcaneBlock extends BlockBase
{

	public SugarcaneBlock(String name, Material material, SoundType sound) {
		super(name, material, sound);
		
		//set Block properties, adjust as necessary
		
		//Hardness
			setHardness(1.0F);
		//Resistance to explosions
			setResistance(2.0F);
		//HarvestLevel
			setHarvestLevel("hoe", 0);
		//Lightemission
			//setLightLevel(0);
		//Light Opacity
			//setLightOpacity(0);
		//Breakable?
			//setBlockUnbreakable();
		
	}

	
}
