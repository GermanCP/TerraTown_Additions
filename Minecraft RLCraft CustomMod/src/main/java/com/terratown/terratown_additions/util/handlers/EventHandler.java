package com.terratown.terratown_additions.util.handlers;

import com.terratown.terratown_additions.events.RemoveVanillaRecipe;

import net.minecraftforge.common.MinecraftForge;

public class EventHandler 
{
	public static void registerEvents()
	{
		RemoveVanillaRecipe recipeEvent = new RemoveVanillaRecipe();
		
		MinecraftForge.EVENT_BUS.register(recipeEvent);
	}

}
