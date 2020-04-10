package com.terratown.terratown_additions.init;

import java.util.ArrayList;
import java.util.List;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.blocks.BreedableFlower;
import com.terratown.terratown_additions.items.FlowerSeeds;
import com.terratown.terratown_additions.items.ItemBase;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import com.terratown.terratown_additions.items.ItemBaseXPTome;
import com.terratown.terratown_additions.items.ModDye;
import com.terratown.terratown_additions.items.Pestle;

public class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//list of items with name
	public static final Item RAW_NEPTUNIUM = new ItemBase("raw_neptunium", Main.tabItems);
	public static final Item NEPTUNIUM_INGOT = new ItemBase("neptunium_ingot", Main.tabItems);

	//crafting tools
	public static final Item PESTLE = new Pestle("pestle", Main.tabItems, 10, 1);
	
	//xp-tomes
	public static final Item XP_TOME_IRON = new ItemBaseXPTome("xp_tome_iron", 2921);
	public static final Item XP_TOME_GOLD = new ItemBaseXPTome("xp_tome_gold", 5345);
	public static final Item XP_TOME_DIAMOND = new ItemBaseXPTome("xp_tome_diamond", 8670);
	public static final Item XP_TOME_EMERALD = new ItemBaseXPTome("xp_tome_emerald", 12895);
	
	//seeds
	public static final Item ROSE_CUTTING = new FlowerSeeds("rose_cutting");
	public static final Item TULIP_NODULE_ORANGE = new FlowerSeeds("tulip_nodule_orange");
	public static final Item TULIP_NODULE_WHITE = new FlowerSeeds("tulip_nodule_white");
	public static final Item TULIP_NODULE_PINK = new FlowerSeeds("tulip_nodule_pink");
	public static final Item TULIP_NODULE_RED = new FlowerSeeds("tulip_nodule_red");
	public static final Item DANDELION_SEED = new FlowerSeeds("dandelion_seed");
	public static final Item POPPY_SEED = new FlowerSeeds("poppy_seed");
	public static final Item CORNFLOWER_SEED = new FlowerSeeds("cornflower_seed");
	public static final Item OXEYE_DAISY_SEED = new FlowerSeeds("oxeye_daisy_seed");
	public static final Item ALLIUM_NODULE = new FlowerSeeds("allium_nodule");
	public static final Item LILY_OF_THE_VALLEY_SEED = new FlowerSeeds("lily_of_the_valley_seed");
	public static final Item AZURE_BLUET_SEED = new FlowerSeeds("azure_bluet_seed");
	public static final Item BLUE_ORCHID_CUTTING = new FlowerSeeds("blue_orchid_cutting");
	
	//petals
	public static final Item PETAL_RED = new ItemBase("petal_red", Main.tabFlower);
	public static final Item PETAL_ORANGE = new ItemBase("petal_orange", Main.tabFlower);
	public static final Item PETAL_WHITE = new ItemBase("petal_white", Main.tabFlower);
	public static final Item PETAL_PINK = new ItemBase("petal_pink", Main.tabFlower);
	public static final Item PETAL_YELLOW = new ItemBase("petal_yellow", Main.tabFlower);
	public static final Item PETAL_LIGHT_GRAY = new ItemBase("petal_light_gray", Main.tabFlower);
	public static final Item PETAL_MAGENTA = new ItemBase("petal_magenta", Main.tabFlower);
	public static final Item PETAL_BLUE = new ItemBase("petal_blue", Main.tabFlower);
	public static final Item PETAL_LIGHT_BLUE = new ItemBase("petal_light_blue", Main.tabFlower);
	
	public static final Item BLUE_DYE = new ModDye("blue_dye", Main.tabFlower);
	public static final Item WHITE_DYE = new ModDye("white_dye", Main.tabFlower);
	public static final Item BROWN_DYE = new ModDye("brown_dye", Main.tabFlower);
	
	
}