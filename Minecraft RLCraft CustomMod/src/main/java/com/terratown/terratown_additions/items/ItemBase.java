package com.terratown.terratown_additions.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.init.ModItems;
import com.terratown.terratown_additions.util.IHasModel;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	
	
}
