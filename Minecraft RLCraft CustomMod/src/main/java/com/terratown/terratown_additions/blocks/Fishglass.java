package com.terratown.terratown_additions.blocks;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.blocks.tileentity.TileEntityFishglass;
import com.terratown.terratown_additions.init.ModBlocks;
import com.terratown.terratown_additions.init.ModItems;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
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

/**
 * This class defines the blocktype: Fishglass, all basic settings are handled, as well as blockstate-updates.
 * @author GermanCreepPlay
 */

public class Fishglass extends BlockContainer implements ITileEntityProvider
{

	public static final PropertyInteger FILL = PropertyInteger.create("fill", 0, 3);
	public static World world;
	
	/**constructor
	 * @param name		name of the item
	 * @param material	which material the block is to be assigned
	 * @param sound		which soundtype the block is to be assigned
	 * @param tab		creative tab the block is listed in
	 * */
	public Fishglass(String name, Material material, SoundType sound, CreativeTabs tab) {
		super(material);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
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
	//set tileentity
	
	/**
	 * set blockstate
	 * @param active	not used
	 * @param worldIn	world the block is in
	 * @param pos		position of the block
	 * */
	public void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	//-------------------------------------------------------------
	//update blockstate
	
	/**
	 * method called by tileentity to update the blockstate of 'this'
	 * @param newstate	new blockstate to be assigned
	 * @param pos		position of the block to be updated
	 * @param worldIn	world the block to be changed is in
	 * */
	public static void updateBlockstate(int newstate, BlockPos pos, World worldIn)
	{
			IBlockState state = worldIn.getBlockState(pos);
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			worldIn.setBlockState(pos, ModBlocks.FISHGLASS.getDefaultState().withProperty(FILL, newstate), 3);
			
			if(tileentity != null)
			{
				tileentity.validate();
				worldIn.setTileEntity(pos, tileentity);
			}
	}
	
	//-------------------------------------------------------------
	//render properties
	
	/**@param state - IBlockState of 'this'
	 * @return false
	 * */
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	/**@param state - IBlockState of 'this'
	 * @return true
	 * */
	 @Override
	public boolean isTranslucent(IBlockState state)
	{
	        return true;
	}
	
	 /**@return Blockrenderlayer: TRANSLUCENT*/
	@Override
	public BlockRenderLayer getBlockLayer()
	{
	        return BlockRenderLayer.TRANSLUCENT;
	}
	
	//-------------------------------------------------------------
	//cheststuff
	/**opens gui of fishglass
	 * @param worldIn	world the activated block is in
	 * @param pos		position of the activated block
	 * @param state		blockstate id of the activated block
	 * @param playerIn	player which activated the block
	 * @param hand		hand with which the block was activated (main, offhand)
	 * @param facing	
	 * @param hitX		position of hit - X component
	 * @param hitY		position of hit - Y component
	 * @param hitZ		position of hit - Z component
	 * */
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
	/**drops items from the fishglass inventory as well as the block itself
	 * @param worldIn	world of the block to brak
	 * @param pos		position of the block to break
	 * @param state		blockstate of the block to break
	 * */
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntityFishglass tileentity = (TileEntityFishglass)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
	}
	
	/**creating new tileentity on block-placement
	 * @param worldIn	world to place block in
	 * @param pos		position to place block in
	 * @param state		blockstate to assign to placed block
	 * @param placer	Entity which placed block
	 * @param stack		
	 * */
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
	/**@param worldIn	current world ('this' is in)
	 * @param meta		metadata of block
	 * @return 			new tileEntity of type Fishglass (TileEntityFishglass)*/
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityFishglass();
	}
	
	//-------------------------------------------------------------
	//settings
	/**@param state - IBlockState of 'this'
	 * @return Blockrendertype: MODEL
	 * */
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	/**@param state - IBlockState of 'this'
	 * @return false
	 * */
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	/**@param state - IBlockState of 'this'
	 * @return false
	 * */
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	 
}
