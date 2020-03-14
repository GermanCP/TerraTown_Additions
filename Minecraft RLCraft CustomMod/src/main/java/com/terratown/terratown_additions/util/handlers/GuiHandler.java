package com.terratown.terratown_additions.util.handlers;

import com.terratown.terratown_additions.blocks.container.ContainerFishglass;
import com.terratown.terratown_additions.blocks.gui.GuiFishglass;
import com.terratown.terratown_additions.blocks.tileentity.TileEntityFishglass;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == Reference.GUI_FISHGLASS) return new ContainerFishglass(player.inventory, (TileEntityFishglass)world.getTileEntity(new BlockPos(x,y,z)), player);
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == Reference.GUI_FISHGLASS) return new GuiFishglass(player.inventory, (TileEntityFishglass)world.getTileEntity(new BlockPos(x,y,z)), player);
		return null;
	}
	
	public static void registerGUIs()
	{
		
	}
}
