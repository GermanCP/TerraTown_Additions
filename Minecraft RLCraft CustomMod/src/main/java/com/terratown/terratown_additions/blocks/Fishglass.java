package com.terratown.terratown_additions.blocks;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.blocks.tileentity.TileEntityFishglass;
import com.terratown.terratown_additions.init.ModBlocks;
import com.terratown.terratown_additions.init.ModItems;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Fishglass extends BlockContainer{

	public static final PropertyInteger FILL = PropertyInteger.create("fill", 0, 3);
	public static World world;
	
	
	public Fishglass(String name, Material material, SoundType sound) {
		super(material);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.DECORATIONS);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
		
		//set Block properties, adjust as necessary
		
				//Hardness
					setHardness(0.5F);
				//Resistance to explosions
					setResistance(2.0F);
				//HarvestLevel
					//setHarvestLevel("hoe", 0);
				//Lightemission
					//setLightLevel(0);
				//Light Opacity
					setLightOpacity(0);
				//Breakable?
					//setBlockUnbreakable();
				//set Sound
					setSoundType(sound);
				
		
	}
	
	//-------------------------------------------------------------
	//blockstate
	@Override
	protected BlockStateContainer createBlockState()
	{	
		return new BlockStateContainer(this, new IProperty[] {FILL});
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FILL);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FILL, meta);
	}	
	
	//-------------------------------------------------------------
	//update blockstate
	public void updateBlockstate(int newstate, BlockPos pos, World worldIn)
	{
		System.out.println(this.blockState.getValidStates() + " | " + this.getStateFromMeta(newstate)); //= newstate;
		worldIn.setBlockState(pos, this.getStateFromMeta(newstate), 3);
	}
	
	//-------------------------------------------------------------
	//render properties
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	 @Override
	public boolean isTranslucent(IBlockState state)
	{
	        return true;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer()
	{
	        return BlockRenderLayer.TRANSLUCENT;
	}
	
	//-------------------------------------------------------------
	//cheststuff
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(Main.instance, Reference.GUI_FISHGLASS, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	//-------------------------------------------------------------
	//breaking the block
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntityFishglass tileentity = (TileEntityFishglass)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(stack.hasDisplayName())
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if(tileentity instanceof TileEntityFishglass) {
				((TileEntityFishglass)tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
	
	//-------------------------------------------------------------
	//creating a new tileentity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityFishglass(this);
	}
	
	//-------------------------------------------------------------
	//settings
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	 
}
