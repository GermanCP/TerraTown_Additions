package com.terratown.terratown_additions.blocks.tileentity;

import javax.annotation.Nullable;

import com.terratown.terratown_additions.blocks.Fishglass;
import com.terratown.terratown_additions.blocks.container.ContainerFishglass;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityFishglass extends TileEntityLockableLoot implements IInventory, ITickable 
	{
		//-------------------------------------------------------------
		//contents:
		private NonNullList<ItemStack> glassContents = NonNullList.<ItemStack>withSize(72, ItemStack.EMPTY);
		
		public int numPlayersUsing, ticksSinceSync;
		
		//for blockstate detection
		private int state;
		private Fishglass parent;
		
		//-------------------------------------------------------------
		//constructor
		public TileEntityFishglass(Fishglass parentIn)
		{
			parent = parentIn;
		}
		
		//-------------------------------------------------------------
		//settings
		@Override
		public int getSizeInventory() {
			return this.glassContents.size();
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
			
			//load Items
			ItemStackHelper.loadAllItems(compound, this.glassContents);

			
			if(compound.hasKey("CustomName", 8)) this.customName = compound.getString("CustomName");
			
				System.out.println("--------------------------------readFromNBT has been called");
		}
		
	    /**
	     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
	     */
		@Override
	    public ItemStack decrStackSize(int index, int count)
	    {
	        return ItemStackHelper.getAndSplit(this.glassContents, index, count);
	    }
		
	    /**
	     * Removes a stack from the given slot and returns it.
	     */
		@Override
	    public ItemStack removeStackFromSlot(int index)
	    {
	        return ItemStackHelper.getAndRemove(this.glassContents, index);
	    }
		
		//-------------------------------------------------------------
		//NBT
		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound compound)
		{
			super.writeToNBT(compound);
			
			ItemStackHelper.saveAllItems(compound, glassContents);
			if(compound.hasKey("CustomName", 8)) compound.setString("CustomName", this.customName);
			
			System.out.println("--------------------------------writeToNBT has been called");
			
			return compound;
		}
		

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
		{
			return new ContainerFishglass(playerInventory, this, playerIn);
		}

		@Override
		public String getGuiID() {
			return Reference.MOD_ID + ":fishglass";
		}

		@Override
		public void update() {
			if (!this.world.isRemote)
			{
				
			}
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
		
		@Override
		public void closeInventory(EntityPlayer player)
		{
			this.markDirty();
			--this.numPlayersUsing;
			
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
				} else if(fillstate >= 2)
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
		
		
		//Update Tileentity
		@Override
		@Nullable
		public SPacketUpdateTileEntity getUpdatePacket() {
			return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
		}

		@Override
		public NBTTagCompound getUpdateTag() {
			return this.writeToNBT(new NBTTagCompound());
		}
		
		@Override
		public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
			super.onDataPacket(net, pkt);
			handleUpdateTag(pkt.getNbtCompound());
			
			this.readFromNBT(pkt.getNbtCompound());
		}
		
	}
