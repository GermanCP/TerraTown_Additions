package com.terratown.terratown_additions.blocks.tileentity;

import com.terratown.terratown_additions.blocks.MortarBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMortarBlock extends TileEntity implements IInventory, ITickable 
{
	private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1, 3};
    
	private NonNullList<ItemStack> inventoryMortar = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	private String customName;
	
	private int pestleTime;
	private int currentPestleTime;
	private int grindTime;
	private int totalGrindTime;
	
    /**
     * Returns the number of slots in the inventory.
     */
	@Override
    public int getSizeInventory()
    {
        return this.inventoryMortar.size();
    }
	
	@Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventoryMortar)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Returns the stack in the given slot.
     */
	@Override
    public ItemStack getStackInSlot(int index)
    {
        return (ItemStack)this.inventoryMortar.get(index);
    }
    
    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
	@Override
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.inventoryMortar, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
	@Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.inventoryMortar, index);
    }
	
    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
	@Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = (ItemStack)this.inventoryMortar.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventoryMortar.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && index + 1 == 1 && !flag)
        {
        	ItemStack stack1 = (ItemStack)this.inventoryMortar.get(index + 1);
            this.totalGrindTime = this.getGrindTime(stack, stack1);
            this.grindTime = 0;
            this.markDirty();
        }
    }
	
	/**
	 * sets how fast its grinding, increase -> decrease speed
	 */
    public int getGrindTime(ItemStack input1, ItemStack input2)
    {
        return 200;
    }
	
    /**
     * Get the name of this object. For players this returns their username
     */
    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.mortar_block";
    }
	
    /**
     * Returns true if this thing is named
     */
    @Override
    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.customName = p_145951_1_;
    }
    
    
    public void setCustomName(String customName)
    {
    	this.customName = customName;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inventoryMortar = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventoryMortar);
        this.pestleTime = compound.getInteger("PestleTime");
        this.grindTime = compound.getInteger("GrindTime");
        this.totalGrindTime = compound.getInteger("GrindTimeTotal");
        this.currentPestleTime = getItemGrindTime(this.inventoryMortar.get(1));

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("PestleTime", (short)this.pestleTime);
        compound.setInteger("GrindTime", (short)this.grindTime);
        compound.setInteger("GrindTimeTotal", (short)this.totalGrindTime);
        ItemStackHelper.saveAllItems(compound, this.inventoryMortar);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }
    
    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    /**
     * Grinder isGrinding
     */
    public boolean isGrinding()
    {
        return this.pestleTime > 0;
    }
    
    @SideOnly(Side.CLIENT)
    public static boolean isGrinding(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }
    
    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        boolean flag = this.isGrinding();
        boolean flag1 = false;

        if (this.isGrinding())
        {
            --this.pestleTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = (ItemStack)this.inventoryMortar.get(2);

            if (this.isGrinding() || !itemstack.isEmpty() && !((((ItemStack)this.inventoryMortar.get(0)).isEmpty()) 
            		|| ((ItemStack)this.inventoryMortar.get(1)).isEmpty()))
            {
                if (!this.isGrinding() && this.canGrind())
                {
                    this.pestleTime = getItemGrindTime(itemstack);
                    this.currentPestleTime = this.pestleTime;

                    if (this.isGrinding())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.inventoryMortar.set(2, item1);
                            }
                        }
                    }
                }

                if (this.isGrinding() && this.canGrind())
                {
                    ++this.grindTime;

                    if (this.grindTime == this.totalGrindTime)
                    {
                        this.grindTime = 0;
                        this.totalGrindTime = this.getGrindTime((ItemStack)this.inventoryMortar.get(0), 
                        		(ItemStack)this.inventoryMortar.get(1));
                        this.grindItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.grindTime = 0;
                }
            }
            else if (!this.isGrinding() && this.grindTime > 0)
            {
                this.grindTime = MathHelper.clamp(this.grindTime - 2, 0, this.totalGrindTime);
            }

            if (flag != this.isGrinding())
            {
                flag1 = true;
                MortarBlock.setState(this.isGrinding(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }
    

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canGrind()
    {
        if (((ItemStack)this.inventoryMortar.get(0)).isEmpty() || ((ItemStack)this.inventoryMortar.get(1)).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack result = MortarRecipes.instance().getGrindingResult(this.inventoryMortar.get(0),
            		(ItemStack)this.inventoryMortar.get(1));

            if (result.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack output = this.inventoryMortar.get(3);

                if (output.isEmpty())
                {
                    return true;
                }
                if (!output.isItemEqual(result))
                {
                    return false;
                }
                int res = output.getCount() + result.getCount();
                return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
            }
        }
    }
    
    public void grindItem()
    {
        if (this.canGrind())
        {
        	ItemStack input1 = (ItemStack)this.inventoryMortar.get(0);
        	ItemStack input2 = (ItemStack)this.inventoryMortar.get(1);
            ItemStack result = MortarRecipes.getInstance().getGrindingResult(input1, input2);
            ItemStack output = (ItemStack)this.inventoryMortar.get(3);

            if (output.isEmpty())
            {
                this.inventoryMortar.set(3, result.copy());
            }
            else if (output.getItem() == result.getItem())
            {
                output.grow(result.getCount());
            }
            input1.shrink(1);
            input2.shrink(1);
        }
    }
 
    //NEED TO IMPORT PESTLE BEFORE CHANGING ITEMGRINDTIME!!!!!!!!
    public static int getItemGrindTime(ItemStack pestle)
    {
        if (pestle.isEmpty())
        {
            return 0;
        }
        else
        {
            Item item = pestle.getItem();
            
            /*
             * Replace olt with Pestle when implemented
             */
            
            if(item == Items.STICK) return 100;
            if(item == Items.COAL) return 1600;
            if(item == Items.LAVA_BUCKET) return 10000;
            
            return GameRegistry.getFuelValue(pestle);
        }
    }
    
    public static boolean isItemPestle(ItemStack stack)
    {
        return getItemGrindTime(stack) > 0;
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}
    
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index == 3)
        {
            return false;
        }
        else if (index != 2)
        {
            return true;
        }
        else
        {
            return isItemPestle(stack);
        }								
    }
    
    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }
    
    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }
    
    public String getGuiID()
    {
        return "terratown_additions:mortar_block";
    }
    
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerMortar(playerInventory, this);
        //import container mortar
    }
    
    @Override
    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.pestleTime;
            case 1:
                return this.currentPestleTime;
            case 2:
                return this.grindTime;
            case 3:
                return this.totalGrindTime;
            default:
                return 0;
        }
    }
    
    @Override
    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.pestleTime = value;
                break;
            case 1:
                this.currentPestleTime = value;
                break;
            case 2:
                this.grindTime = value;
                break;
            case 3:
                this.totalGrindTime = value;
        }
    }
    
    @Override
    public int getFieldCount()
    {
        return 4;
    }
    
    @Override
    public void clear()
    {
        this.inventoryMortar.clear();
    }
    
}