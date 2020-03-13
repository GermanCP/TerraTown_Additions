package com.terratown.terratown_additions.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init()
	{
		//vanilla recipes
		
		
		//mod internal recipes
		GameRegistry.addSmelting(ModItems.RAW_NEPTUNIUM, new ItemStack(ModItems.NEPTUNIUM_INGOT), 1.0F);
		
	}
}
