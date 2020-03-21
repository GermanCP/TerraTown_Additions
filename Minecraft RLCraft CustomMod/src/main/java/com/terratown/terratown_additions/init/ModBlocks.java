package com.terratown.terratown_additions.init;

import java.util.ArrayList;
import java.util.List;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.blocks.BlockBase;

//advanced
import com.terratown.terratown_additions.blocks.Fishglass;
import com.terratown.terratown_additions.blocks.SugarcaneBlock;
import com.terratown.terratown_additions.blocks.SugarcaneBlockDeco;
import com.terratown.terratown_additions.blocks.MortarBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModBlocks
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//List of blocks with name and type of block
	public static final Block SUGARCANE_BLOCK = new SugarcaneBlock("sugarcane_block", Material.GRASS, SoundType.PLANT, Main.tabBlocks);
	
	//storage blocks
	public static final Block FISHGLASS = new Fishglass("fishglass", Material.GLASS, SoundType.GLASS, Main.tabDecorationBlocks);
	
	//decorative blocks
	public static final Block SUGARCANE_BLOCK_DECO = new SugarcaneBlockDeco("sugarcane_block_deco", Material.GRASS, SoundType.PLANT, Main.tabDecorationBlocks);
	
	//functional block
	public static final Block MORTAR_BLOCK = new MortarBlock("mortar_block", Material.ROCK, SoundType.STONE, Main.tabCrafting, 200);
	//public static final Block MORTAR_BLOCK_ADVANCED = new MortarBlock("mortar_block_advanced", Material.ROCK, SoundType.STONE, Main.tabCrafting, 100);

}
