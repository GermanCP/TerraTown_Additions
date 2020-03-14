package com.terratown.terratown_additions.blocks.tileentity;

import com.terratown.terratown_additions.blocks.Fishglass;
import com.terratown.terratown_additions.blocks.container.ContainerFishglass;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityFishglass extends TileEntityLockableLoot implements ITickable
	{
		private NonNullList<ItemStack> glassContents = NonNullList.<ItemStack>withSize(72, ItemStack.EMPTY);
		public int numPlayersUsing, ticksSinceSync;
		
		//for blockstate detection
		private int state;
		
		private Fishglass parent;
		
		public TileEntityFishglass(Fishglass parentIn) {
			parent = parentIn;
		}
		
		//-------------------------------------------------------------
		//settings
		@Override
		public int getSizeInventory() {
			return 72;
		}
		
		@Override
		public int getInventoryStackLimit() {
			return 32;
		}
		
		//-------------------------------------------------------------
		//gets and sets
		@Override
		public boolean isEmpty() {
			for(ItemStack stack : this.glassContents)
			{
				if(!stack.isEmpty()) return false;
			}
			
			return true;
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
		
		//-------------------------------------------------------------
		//resetblock on inventory false!
		@Override
	   	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
			return (oldState.getBlock() != newState.getBlock());
	    }
		
		//-------------------------------------------------------------
		//updateinventory - count how many items are in inventory and update blockstate accordingly
		
		public void updateInventory() {
			//reset counter
			int numberOfItems = 0;
			float fillstate = 0;
			String emptyTile = ItemStack.EMPTY.toString();
			
			//run through whole inventory
			for(int i = 0; i < glassContents.size(); i++)
			{
				//detect if slot is empty
				if(emptyTile.equals(glassContents.get(i).toString())) { //returns true when slot has been cleared and empty has been newly created			
				}
				else
				{
					//if not - count
					numberOfItems++;
				}
			}
			
			//System.out.println("-------------------------numberOfItems: " + numberOfItems);
			
			//now check how much of the inventory is filled and thereby determine how the block should render
			if(numberOfItems != 0) {
			fillstate = (float) glassContents.size() / (float) numberOfItems;
				if(fillstate == 1)
				{
						//full - state 3
						state = 3;
				} else if(fillstate < 2)
				{
						//half full - state 2
						state = 2;
				} else if(fillstate > 2)
				{
						//some items - state 1
						state = 1;
				} else {
						//do nothing
				}
			} else
			{
				//no items - state 0
				state = 0;
			}
			
			//call parent to update blockstate
			parent.updateBlockstate(state, pos, world);
		}
		
	}
