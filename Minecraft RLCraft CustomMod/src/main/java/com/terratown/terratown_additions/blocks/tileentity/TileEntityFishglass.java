package com.terratown.terratown_additions.blocks.tileentity;

import com.terratown.terratown_additions.blocks.container.ContainerFishglass;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityFishglass extends TileEntityLockableLoot implements ITickable
	{
		private NonNullList<ItemStack> glassContents = NonNullList.<ItemStack>withSize(72, ItemStack.EMPTY);
		public int numPlayersUsing, ticksSinceSync;

		@Override
		public int getSizeInventory() {
			return 72;
		}

		@Override
		public boolean isEmpty() {
			for(ItemStack stack : this.glassContents)
			{
				if(!stack.isEmpty()) return false;
			}
			
			return true;
		}

		@Override
		public int getInventoryStackLimit() {
			return 1;
		}

		@Override
		public String getName() {
			return this.hasCustomName() ? this.customName : "container.fishglass";
		}
		
		@Override
		public void readFromNBT(NBTTagCompound compound)
		{
			super.readFromNBT(compound);
			this.glassContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
			
			if(!this.checkLootAndRead(compound))
			{
				ItemStackHelper.loadAllItems(compound, glassContents);
			}
			
			if(compound.hasKey("CustomName", 8)) this.customName = compound.getString("CustomName");
		}
		
		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound compound)
		{
			super.writeToNBT(compound);
			
			if(!this.checkLootAndWrite(compound)) ItemStackHelper.saveAllItems(compound, glassContents);
			if(compound.hasKey("CustomName", 8)) compound.setString("CustomName", this.customName);
			
			return compound;
		}
		

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
			return new ContainerFishglass(playerInventory, this, playerIn);
		}

		@Override
		public String getGuiID() {
			return Reference.MOD_ID + ":fishglass";
		}

		@Override
		public void update() {
			
		}

		@Override
		protected NonNullList<ItemStack> getItems() {
			return this.glassContents;
		}
		
		@Override
		public void openInventory(EntityPlayer player)
		{
			++this.numPlayersUsing;
			this.world.addBlockEvent(pos, this.getBlockType(), 1,this.numPlayersUsing);
			this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
		}
	}
