package com.terratown.terratown_additions.blocks;

import com.terratown.terratown_additions.init.ModBlocks;
import com.terratown.terratown_additions.init.ModItems;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BreedableFlower extends BlockCrops
{
    private static final AxisAlignedBB[] flower = new AxisAlignedBB[] {
    		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), 
    		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), 
    		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), 
    		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), 
    		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), 
    		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), 
    		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), 
    		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)
    		};



	public BreedableFlower(String name, ItemStack breed, ItemStack seed) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		ItemStack dyeResult = breed;
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			if(this.isMaxAge(state))
			{
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.DYE, 3, EnumDyeColor.RED.getDyeDamage())));
				worldIn.setBlockState(pos, this.withAge(0));
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected Item getSeed()
	{
		return ModItems.ROSE_SEED;
	}
	
	@Override
	protected Item getCrop() 
	{	
		return ModItems.ROSE_SEED;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		
		return flower[((Integer)state.getValue(this.getAgeProperty())).intValue()];
	}
	
}
