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

/**
 * This class defines the blocktype: breedable flower. Containing drops and seeds with wich to plant a certain flower. Hitbox alignment is also handled here.
 * @author KamiKatze
 */

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

    private static ItemStack PETAL;
    private static Item SEED;
    private String NAME;

    /**Constructor*/
	public BreedableFlower(String name) 
	{
		
		setUnlocalizedName(name);
		setRegistryName(name);
		NAME = name;
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	/**set Petal (Itemstack) to according item, based on flowers name*/
	private void getPetal()
	{
		if(NAME == "rose_bush") PETAL = new ItemStack(ModItems.PETAL_RED);
		if(NAME == "tulip_orange") PETAL = new ItemStack(ModItems.PETAL_ORANGE);
		if(NAME == "tulip_white") PETAL = new ItemStack(ModItems.PETAL_WHITE);
		if(NAME == "tulip_pink") PETAL = new ItemStack(ModItems.PETAL_PINK);
		if(NAME == "tulip_red") PETAL = new ItemStack(ModItems.PETAL_RED);
		if(NAME == "dandelion") PETAL = new ItemStack(ModItems.PETAL_YELLOW);
		if(NAME == "poppy") PETAL = new ItemStack(ModItems.PETAL_RED);
		if(NAME == "cornflower") PETAL = new ItemStack(ModItems.PETAL_BLUE);
		if(NAME == "oxeye_daisy") PETAL = new ItemStack(ModItems.PETAL_WHITE);
		if(NAME == "allium") PETAL = new ItemStack(ModItems.PETAL_MAGENTA);
		if(NAME == "lily_of_the_valley") PETAL = new ItemStack(ModItems.PETAL_WHITE);
		if(NAME == "azure_bluet") PETAL = new ItemStack(ModItems.PETAL_LIGHT_GRAY);
		if(NAME == "blue_orchid") PETAL = new ItemStack(ModItems.PETAL_LIGHT_BLUE);
	}
	
	/**set Seed (Item) to according item, based on flowers name*/
	private void getSeedByName() 
	{
		if(NAME == "rose_bush") SEED = ModItems.ROSE_CUTTING;
		if(NAME == "tulip_orange") SEED = ModItems.TULIP_NODULE_ORANGE;
		if(NAME == "tulip_white") SEED = ModItems.TULIP_NODULE_WHITE;
		if(NAME == "tulip_pink") SEED = ModItems.TULIP_NODULE_PINK;
		if(NAME == "tulip_red") SEED = ModItems.TULIP_NODULE_RED;
		if(NAME == "dandelion") SEED = ModItems.DANDELION_SEED;
		if(NAME == "poppy") SEED = ModItems.POPPY_SEED;
		if(NAME == "cornflower") SEED = ModItems.CORNFLOWER_SEED;
		if(NAME == "oxeye_daisy") SEED = ModItems.OXEYE_DAISY_SEED;
		if(NAME == "allium") SEED = ModItems.ALLIUM_NODULE;
		if(NAME == "lily_of_the_valley") SEED = ModItems.LILY_OF_THE_VALLEY_SEED;
		if(NAME == "azure_bluet") SEED = ModItems.AZURE_BLUET_SEED;
		if(NAME == "blue_orchid") SEED = ModItems.BLUE_ORCHID_CUTTING;
	}
	
	public final void setDefaultState() 
	{
		this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
		 
	}
	
	/**Determines behaviour on right-click, if flower should drop petal (-> fully grown/age 7)*/
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, 
			EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			if(this.isMaxAge(state))
			{
				getPetal();
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), PETAL));
				worldIn.setBlockState(pos, this.withAge(0));
				return true;
			}
		}
		return false;
	}
	
	//--------------------------------------------------------------
	//gets
	
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
	
	//--------------------------------------------------------------
	//bounding box
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{		
		return flower[((Integer)state.getValue(this.getAgeProperty())).intValue()];
	}
	
	
}
