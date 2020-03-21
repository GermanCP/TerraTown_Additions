package com.terratown.terratown_additions.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Pestle extends ItemBase{

	public int durability;
	public int grindTime;
	
	public Pestle(String name, CreativeTabs tab, int durabilityBase, int grindTimeIn) {
		super(name, tab);
		this.maxStackSize = 1;
		this.setMaxDamage(durabilityBase);
		durability = durabilityBase;
		this.grindTime = grindTimeIn;
	}
	
	public int getMaxDurability()
	{
		return this.durability;
	}
	
	public int getGrindTime() {
		return grindTime;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
	
	public boolean canHarvestBlock(IBlockState blockIn)
    {
        return false;
    }

	
}
