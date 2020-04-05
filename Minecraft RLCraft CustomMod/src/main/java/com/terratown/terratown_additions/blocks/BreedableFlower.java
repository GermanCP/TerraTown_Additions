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
    private static ItemStack dyeResult;
    private static Item SEED;
    private String NAME;


	public BreedableFlower(String name) 
	{
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
		setUnlocalizedName(name);
		setRegistryName(name);
		NAME = name;
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	private void getDyeResult() 
	{
		if(NAME == "rose_bush") dyeResult = new ItemStack(Items.DYE, 3, EnumDyeColor.RED.getDyeDamage());
		if(NAME == "tulip_orange") dyeResult = new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage());
		if(NAME == "tulip_white") dyeResult = new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage());
		if(NAME == "tulip_pink") dyeResult = new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage());
		if(NAME == "tulip_red") dyeResult = new ItemStack(Items.DYE, 1, EnumDyeColor.RED.getDyeDamage());
		if(NAME == "dandelion") dyeResult = new ItemStack(Items.DYE, 1, EnumDyeColor.YELLOW.getDyeDamage());
	}
	
	private void getSeedByName() 
	{
		if(NAME == "rose_bush") SEED = ModItems.ROSE_SEED;
		if(NAME == "tulip_orange") SEED = ModItems.TULIP_NODULE_ORANGE;
		if(NAME == "tulip_white") SEED = ModItems.TULIP_NODULE_WHITE;
		if(NAME == "tulip_pink") SEED = ModItems.TULIP_NODULE_PINK;
		if(NAME == "tulip_red") SEED = ModItems.TULIP_NODULE_RED;
		if(NAME == "dandelion") SEED = ModItems.DANDELION_SEED;
	}
	
	public final void setDefaultState() 
	{
		this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
		 
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, 
			EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			if(this.isMaxAge(state))
			{
				getDyeResult();
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), dyeResult));
				worldIn.setBlockState(pos, this.withAge(0));
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	protected Item getSeed()
	{
		getSeedByName();
		return SEED;
	}
	
	@Override
	protected Item getCrop() 
	{	
		getSeedByName();
		return SEED;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		
		return flower[((Integer)state.getValue(this.getAgeProperty())).intValue()];
	}
	
	
}
