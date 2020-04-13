package com.terratown.terratown_additions.events;

import com.terratown.terratown_additions.recipe.DummyRecipe;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;


public class RemoveVanillaRecipe 
{
	/**
	 * registers Recipes that wanna get removed from vanilla recipe
	 * @param event registry event from IRecipe
	 */
	@SubscribeEvent
	public void registerRecipes(RegistryEvent.Register<IRecipe> event)
	{
		IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable)event.getRegistry();
		removeRecipe(modRegistry, new ResourceLocation("minecraft:cyan_dye"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:gray_dye"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:light_blue_dye_from_blue_orchid"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:light_blue_dye_from_lapis_bonemeal"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:light_gray_dye_from_azure_bluet"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:light_gray_dye_from_gray_bonemeal"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:light_gray_dye_from_ink_bonemeal"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:light_gray_dye_from_oxeye_daisy"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:light_gray_dye_from_white_tulip"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:lime_dye"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:magenta_dye_from_allium"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:magenta_dye_from_lapis_ink_bonemeal"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:magenta_dye_from_lapis_red_pink"), Reference.MOD_ID);
		//removeRecipe(modRegistry, new ResourceLocation("minecraft:magenta_dye_from_lilac"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:magenta_dye_from_purple_and_pink"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:orange_dye_from_orange_tulip"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:orange_dye_from_red_yellow"), Reference.MOD_ID);
		//removeRecipe(modRegistry, new ResourceLocation("minecraft:pink_dye_from_peony"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:pink_dye_from_pink_tulip"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:pink_dye_from_red_bonemeal"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:purple_dye"), Reference.MOD_ID);
		//removeRecipe(modRegistry, new ResourceLocation("minecraft:red_dye_from_beetroot"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:red_dye_from_poppy"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:red_dye_from_rose_bush"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:red_dye_from_tulip"), Reference.MOD_ID);
		removeRecipe(modRegistry, new ResourceLocation("minecraft:yellow_dye_from_dandelion"), Reference.MOD_ID);
		//removeRecipe(modRegistry, new ResourceLocation("minecraft:yellow_dye_from_sunflower"), Reference.MOD_ID);
		
	}
	
	/**
	 * removes recipe from vanilla registry
	 * @param modRegistry IForgeRegistryModifiable as event.getRegistry();
	 * @param recipe ResourceLocation -> name of recipe json
	 * @param modID Referance.MOD_ID
	 */
	public static void removeRecipe(IForgeRegistryModifiable modRegistry, ResourceLocation recipe, String modID)
	{
		IRecipe p = (IRecipe)modRegistry.getValue(recipe);
		modRegistry.remove(recipe);
		modRegistry.register(DummyRecipe.from(p));
	}
}
