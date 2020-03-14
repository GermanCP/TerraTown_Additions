package com.terratown.terratown_additions.util.handlers;

import com.terratown.terratown_additions.init.ModItems;
import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.init.ModBlocks;
import com.terratown.terratown_additions.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{	
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(ModBlocks.FISHGLASS), 0, null);
		
		for(Item item : ModItems.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : ModBlocks.BLOCKS)
		{
			if(block instanceof IHasModel){
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries()
	{
		
	}
	
	public static void initRegistries() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
}
