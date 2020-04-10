package com.terratown.terratown_additions.blocks.container;

import com.terratown.terratown_additions.blocks.gui.slots.MortarBlock.SlotMortarBlockOutput;
import com.terratown.terratown_additions.blocks.gui.slots.MortarBlock.SlotMortarBlockPestle;
import com.terratown.terratown_additions.blocks.recipes.MortarRecipes;
import com.terratown.terratown_additions.blocks.tileentity.TileEntityMortarBlock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class defines the item-container for the mortar-block
 * @author KamiKatze
 */

public class ContainerMortar extends Container
{
	private final TileEntityMortarBlock tileentity;

	//time variables for crafting
	private int pestleTime;
	private int currentPestleTime;
	private int grindTime;
	private int totalGrindTime;

	public ContainerMortar(InventoryPlayer player, TileEntityMortarBlock tileentity) 
	{
		this.tileentity = tileentity;
		
		//add slots to container
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 56, 53));
		this.addSlotToContainer(new SlotMortarBlockPestle(tileentity, 2, 33, 35));
		this.addSlotToContainer(new SlotMortarBlockOutput(player.player, tileentity, 3, 116, 35));
		
		//create player inventory grid
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}
		
		//add hotbar inventory grid
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	
	}
	
	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++)
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.grindTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.pestleTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.currentPestleTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.totalGrindTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}
		
		this.grindTime = this.tileentity.getField(2);
		this.pestleTime = this.tileentity.getField(0);
		this.currentPestleTime = this.tileentity.getField(1);
		this.totalGrindTime = this.tileentity.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 3)
			{
				if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index != 2 && index != 1 && index != 0)
			{
				Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				
				if(!MortarRecipes.getInstance().getGrindingResult(stack1, slot1.getStack()).isEmpty())
				{
					if(!this.mergeItemStack(stack1, 0, 2, false))
					{
						return ItemStack.EMPTY;
					}
					else if (tileentity.isItemPestle(stack1)) 
					{
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if (tileentity.isItemPestle(stack1))
					{
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if (tileentity.isItemPestle(stack1)) 
					{
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if(index >= 4 && index < 31) 
					{
						if(!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
					}
					else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false))
					{
						return ItemStack.EMPTY;
					}
				}
			}
			else if(!this.mergeItemStack(stack1, 4, 40, false))
			{
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
}
