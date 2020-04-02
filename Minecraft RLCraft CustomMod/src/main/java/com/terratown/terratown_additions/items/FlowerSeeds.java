package com.terratown.terratown_additions.items;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.blocks.BreedableFlower;
import com.terratown.terratown_additions.init.ModBlocks;
import com.terratown.terratown_additions.init.ModItems;
import com.terratown.terratown_additions.util.IHasModel;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class FlowerSeeds extends Item implements IPlantable, IHasModel
{
	public Block CROPS;
	private int Crops = 0;
	
	public FlowerSeeds(String name, int crops, Block soil) {
		
		//CROPS = crops;
		Crops = crops;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.tabItems);
		
		
		ModItems.ITEMS.add(this);
	}
	
	private void getCrops(int crops) {
		if(crops == 1) CROPS = ModBlocks.ROSE_BUSH;
		if(crops == 2) CROPS = ModBlocks.TULIP_ORANGE;
		if(crops == 3) CROPS = ModBlocks.TULIP_WHITE;
		if(crops == 4) CROPS = ModBlocks.TULIP_PINK;
		if(crops == 5) CROPS = ModBlocks.TULIP_RED;
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && 
        		state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
        {
        	worldIn.setBlockState(pos.up(), CROPS.getDefaultState());
        	if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
            }
        	itemstack.shrink(1);
        	return EnumActionResult.SUCCESS;
        }
        else return EnumActionResult.FAIL;
	}
	
	
	@Override
    public EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Crop;
    }
	
	
	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) 
	{
		getCrops(Crops);
		return CROPS.getDefaultState();
	}
	
}
