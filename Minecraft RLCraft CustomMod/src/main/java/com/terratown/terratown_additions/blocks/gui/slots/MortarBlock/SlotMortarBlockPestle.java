package com.terratown.terratown_additions.blocks.gui.slots.MortarBlock;

import com.terratown.terratown_additions.blocks.tileentity.TileEntityMortarBlock;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMortarBlockPestle extends Slot
{
	public SlotMortarBlockPestle(IInventory inventory,int index, int x, int y)
	{
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return TileEntityMortarBlock.isItemPestle(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return super.getItemStackLimit(stack);
	}

	
}
