package com.terratown.terratown_additions.blocks;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.blocks.tileentity.TileEntityMortarBlock;
import com.terratown.terratown_additions.init.ModBlocks;
import com.terratown.terratown_additions.util.Reference;

import java.util.Random;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MortarBlock extends BlockBase implements ITileEntityProvider
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool GRINDING = PropertyBool.create("grinding");
	public static final PropertyBool HAS_PESTLE = PropertyBool.create("has_pestle");
	public static final PropertyInteger ANIMATION_STATE = PropertyInteger.create("animation_state", 0, 7);
	
	public static int GrindSpeed;
	
	public MortarBlock(String name, Material material, SoundType sound, CreativeTabs tab, int grindspeed) {
		super(name, material, sound, tab);
		
		//set Block properties, adjust as necessary
		
		//Hardness
			setHardness(2.0F);
		//Resistance to explosions
			setResistance(12.0F);
		//HarvestLevel
			setHarvestLevel("pickaxe", 0);
		//Light Opacity
			setLightOpacity(0);	
		//Property
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(GRINDING, false).withProperty(HAS_PESTLE, false));
		//Grindspeed
			GrindSpeed = grindspeed;
		
	}
	
	//---------------------------------------------------------------------
	//make sure blocks behind this are rendered
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	//---------------------------------------------------------------------
	//drop the right item if block is destroyed
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(this);	//if doesn't work: ModBlocks.MORTAR_BLOCK
	}

	//---------------------------------------------------------------------
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) 
	{
		return new ItemStack(this);	//if doesn't work: ModBlocks.MORTAR_BLOCK
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(Main.instance, Reference.GUI_MORTAR, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		if(!worldIn.isRemote)
		{
		//Blockstate for each direction
			IBlockState north = worldIn.getBlockState(pos.north());
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState west = worldIn.getBlockState(pos.west());
			IBlockState east = worldIn.getBlockState(pos.east());
			EnumFacing face = (EnumFacing)state.getValue(FACING);
			
		//sets opposide direction player is facing
			if(face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
			else if (face == EnumFacing.SOUTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.NORTH;
			else if (face == EnumFacing.WEST && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.EAST;
			else if (face == EnumFacing.EAST && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.WEST;
			worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	
	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		worldIn.setBlockState(pos, ModBlocks.MORTAR_BLOCK.getDefaultState()
				//------------------
				//get and set
				.withProperty(FACING, state.getValue(FACING))
				.withProperty(GRINDING,  active) //------------------------------set this value
				.withProperty(HAS_PESTLE, state.getValue(HAS_PESTLE))
				.withProperty(ANIMATION_STATE, state.getValue(ANIMATION_STATE))
				//------------------
				, 3);
		
		if(tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	public static void setStatePestle(boolean hasPestle, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		worldIn.setBlockState(pos, ModBlocks.MORTAR_BLOCK.getDefaultState()
				//------------------
				//get and set
				.withProperty(FACING, state.getValue(FACING))
				.withProperty(GRINDING, state.getValue(GRINDING)) //------------------------------set this value
				.withProperty(HAS_PESTLE, hasPestle)
				.withProperty(ANIMATION_STATE, state.getValue(ANIMATION_STATE))
				//------------------
				, 3);
		
		if(tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	public static void setStateInt(int stateInt, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		worldIn.setBlockState(pos, ModBlocks.MORTAR_BLOCK.getDefaultState()
				//------------------
				//get and set
				.withProperty(FACING, state.getValue(FACING))
				.withProperty(GRINDING, state.getValue(GRINDING))
				.withProperty(HAS_PESTLE, state.getValue(HAS_PESTLE))
				.withProperty(ANIMATION_STATE,  stateInt) //------------------------------set this value
				//------------------
				, 3);
		
		if(tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityMortarBlock();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		TileEntityMortarBlock tileentity = (TileEntityMortarBlock)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
		
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) 
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {GRINDING, FACING, HAS_PESTLE, ANIMATION_STATE});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing).withProperty(HAS_PESTLE, false)
				.withProperty(ANIMATION_STATE, meta);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
		
}
