package com.terratown.terratown_additions.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**contains all recipes to be added to the game*/
public class ModRecipes {
	
	/**used to initilize recipes*/
	public static void init()
	{
		//vanilla recipes
		
		
		//mod internal recipes
		GameRegistry.addSmelting(ModItems.RAW_NEPTUNIUM, new ItemStack(ModItems.NEPTUNIUM_INGOT), 1.0F);
		
	}
}
