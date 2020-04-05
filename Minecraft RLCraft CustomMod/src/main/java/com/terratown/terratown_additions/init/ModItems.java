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
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import com.terratown.terratown_additions.items.ItemBaseXPTome;
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
	public static final Item LILY_OF_THE_VALLY_SEED = new FlowerSeeds("lily_of_the_vally_seed");
	public static final Item AZURE_BLUET_SEED = new FlowerSeeds("azure_bluet_seed");
	public static final Item BLUE_ORCHID_CUTTING = new FlowerSeeds("blue_orchid_cutting");
	
	//petals
	public static final Item ROSE_PETAL = new ItemBase("rose_petal", Main.tabItems);
	public static final Item TULIP_ORANGE_PETAL = new ItemBase("tulip_orange_petal", Main.tabItems);
	public static final Item TULIP_WHITE_PETAL = new ItemBase("tulip_white_petal", Main.tabItems);
	public static final Item TULIP_PINK_PETAL = new ItemBase("tulip_pink_petal", Main.tabItems);
	public static final Item TULIP_RED_PETAL = new ItemBase("tulip_red_petal", Main.tabItems);
	public static final Item DANDELION_PETAL = new ItemBase("dadelion_petal", Main.tabFlower);
	public static final Item POPPY_PETAL = new ItemBase("poppy_petal", Main.tabFlower);
	public static final Item CORNFLOWER_PETAL = new ItemBase("cornflower_petal", Main.tabFlower);
	public static final Item OXEYE_DAISY_PETAL = new ItemBase("oxeye_daisy_petal", Main.tabFlower);
	public static final Item ALLIUM_PETAL = new ItemBase("allium_petal", Main.tabFlower);
	public static final Item LILY_OF_THE_VALLEY_PETAL = new ItemBase("lily_of_the_vally_petal", Main.tabFlower);
	public static final Item AZURE_BLUET_PETAL = new ItemBase("azure_bluet_petal", Main.tabFlower);
	public static final Item BLUE_ORCHID_PETAL = new ItemBase("blue_orchid_petal", Main.tabFlower);
	
	
}