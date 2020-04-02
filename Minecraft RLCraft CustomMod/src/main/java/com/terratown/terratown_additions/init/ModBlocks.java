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
	public static final Block ROSE_BUSH = new BreedableFlower("rose_bush", new ItemStack(Items.DYE, 3, EnumDyeColor.RED.getDyeDamage()), ModItems.ROSE_SEED);
	public static final Block TULIP_ORANGE = new BreedableFlower("tulip_orange", new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage()), ModItems.TULIP_NODULE_ORANGE);
	public static final Block TULIP_WHITE = new BreedableFlower("tulip_white", new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), ModItems.TULIP_NODULE_WHITE);
	public static final Block TULIP_PINK = new BreedableFlower("tulip_pink", new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage()), ModItems.TULIP_NODULE_PINK);
	public static final Block TULIP_RED = new BreedableFlower("tulip_red", new ItemStack(Items.DYE, 1, EnumDyeColor.RED.getDyeDamage()), ModItems.TULIP_NODULE_RED);
	

}
