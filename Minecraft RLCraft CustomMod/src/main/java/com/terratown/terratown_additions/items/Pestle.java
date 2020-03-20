package com.terratown.terratown_additions.items;

import net.minecraft.creativetab.CreativeTabs;

public class Pestle extends ItemBase{

	public int durability;
	public int grindTime;
	
	public Pestle(String name, CreativeTabs tab, int durabilityBase, int grindTimeIn) {
		super(name, tab);
		
		this.durability = durabilityBase;
		this.grindTime = grindTimeIn;
	}
	
	public int getGrindTime() {
		return grindTime;
	}
	
}
