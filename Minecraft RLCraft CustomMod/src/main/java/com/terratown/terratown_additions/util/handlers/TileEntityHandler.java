package com.terratown.terratown_additions.util.handlers;

import com.terratown.terratown_additions.blocks.tileentity.TileEntityFishglass;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityFishglass.class, new ResourceLocation(Reference.MOD_ID + ":fishglass"));
	}
}
