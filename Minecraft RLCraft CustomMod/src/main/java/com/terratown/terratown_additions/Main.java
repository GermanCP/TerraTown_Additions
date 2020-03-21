package com.terratown.terratown_additions;

import com.terratown.terratown_additions.init.ModBlocks;
import com.terratown.terratown_additions.init.ModItems;
import com.terratown.terratown_additions.init.ModRecipes;
import com.terratown.terratown_additions.proxy.CommonProxy;
import com.terratown.terratown_additions.util.Reference;
import com.terratown.terratown_additions.util.handlers.RegistryHandler;
import com.terratown.terratown_additions.util.handlers.TileEntityHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event){
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		ModRecipes.init();
		RegistryHandler.initRegistries();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event){
		
	}
	
	//----------------------------------------------
	//creativetabs
	
	public static CreativeTabs tabDecorationBlocks = new CreativeTabs("tab_decorationsBlocks") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.SUGARCANE_BLOCK_DECO);
		}
	};
	
	public static CreativeTabs tabBlocks = new CreativeTabs("tab_blocks") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.SUGARCANE_BLOCK);
		}
	};
	
	public static CreativeTabs tabItems = new CreativeTabs("tab_items") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.PESTLE);
		}
	};
	
	public static CreativeTabs tabTools = new CreativeTabs("tab_tools") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.SUGARCANE_BLOCK_DECO);
		}
	};
	
	public static CreativeTabs tabCrafting = new CreativeTabs("tab_crafting") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.MORTAR_BLOCK);
		}
	};
	
	//----------------------------------------------
}
