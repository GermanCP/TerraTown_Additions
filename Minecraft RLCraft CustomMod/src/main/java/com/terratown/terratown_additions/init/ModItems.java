package com.terratown.terratown_additions.init;

import java.util.ArrayList;
import java.util.List;
import com.terratown.terratown_additions.items.ItemBase;
import net.minecraft.item.Item;
import com.terratown.terratown_additions.items.ItemBaseXPTome;

public class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//list of items with name
	public static final Item RAW_NEPTUNIUM = new ItemBase("raw_neptunium");
	public static final Item NEPTUNIUM_INGOT = new ItemBase("neptunium_ingot");

	//xp-tomes
	public static final Item XP_TOME_IRON = new ItemBaseXPTome("xp_tome_iron", 2921);
	public static final Item XP_TOME_GOLD = new ItemBaseXPTome("xp_tome_gold", 5345);
	public static final Item XP_TOME_DIAMOND = new ItemBaseXPTome("xp_tome_diamond", 8670);
	public static final Item XP_TOME_EMERALD = new ItemBaseXPTome("xp_tome_emerald", 12895);

}
