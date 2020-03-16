package com.terratown.terratown_additions.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import scala.util.Random;

public class MortarBlock extends BlockBase
{
	
	public MortarBlock(String name, Material material, SoundType sound) {
		super(name, material, sound);
		
		//set Block properties, adjust as necessary
		
		//Hardness
			setHardness(2.0F);
		//Resistance to explosions
			setResistance(12.0F);
		//HarvestLevel
			setHarvestLevel("pickaxe", 0);
			setCreativeTab(CreativeTabs.DECORATIONS);
		
	}
	
	public Item getItemDropped(int par1, Random random, int par2)
	{
		return Item.getItemFromBlock(this);
	}

}
