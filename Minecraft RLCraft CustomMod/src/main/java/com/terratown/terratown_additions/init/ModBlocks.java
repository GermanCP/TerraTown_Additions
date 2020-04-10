package com.terratown.terratown_additions.init;

import java.util.ArrayList;
import java.util.List;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.blocks.BlockBase;
import com.terratown.terratown_additions.blocks.BreedableFlower;
//advanced
import com.terratown.terratown_additions.blocks.Fishglass;
import com.terratown.terratown_additions.blocks.SugarcaneBlock;
import com.terratown.terratown_additions.blocks.SugarcaneBlockDeco;
import com.terratown.terratown_additions.blocks.MortarBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
	
	//flowers
	public static final Block ROSE_BUSH = new BreedableFlower("rose_bush");
	public static final Block TULIP_ORANGE = new BreedableFlower("tulip_orange");
	public static final Block TULIP_WHITE = new BreedableFlower("tulip_white");
	public static final Block TULIP_PINK = new BreedableFlower("tulip_pink");
	public static final Block TULIP_RED = new BreedableFlower("tulip_red");
	public static final Block DANDELION = new BreedableFlower("dandelion");
	public static final Block POPPY = new BreedableFlower("poppy");
	public static final Block CORNFLOWER = new BreedableFlower("cornflower");
	public static final Block OXEYE_DAISY = new BreedableFlower("oxeye_daisy");
	public static final Block ALLIUM = new BreedableFlower("allium");
	public static final Block LILY_OF_THE_VALLEY = new BreedableFlower("lily_of_the_valley");
	public static final Block AZURE_BLUET = new BreedableFlower("azure_bluet");
	public static final Block BLUE_ORCHID = new BreedableFlower("blue_orchid");
	
	

}
