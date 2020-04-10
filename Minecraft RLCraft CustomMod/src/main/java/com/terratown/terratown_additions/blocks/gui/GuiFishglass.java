package com.terratown.terratown_additions.blocks.gui;

import com.terratown.terratown_additions.blocks.container.ContainerFishglass;
import com.terratown.terratown_additions.blocks.tileentity.TileEntityFishglass;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * This class defines inventoryrender and texture for the fishglass-block
 * @author GermanCreepPlay
 */

public class GuiFishglass extends GuiContainer {
	private static final ResourceLocation GUI_GLASS = new ResourceLocation(Reference.MOD_ID + ":textures/gui/fishglass.png");
	private final InventoryPlayer playerInventory;
	private final TileEntityFishglass te;
	
	public GuiFishglass(InventoryPlayer playerInventory, TileEntityFishglass glassInventory, EntityPlayer player)
	{
		super(new ContainerFishglass(playerInventory, glassInventory, player));
		this.playerInventory = playerInventory;
		this.te = glassInventory;
		
		//texturesize is defined here
		this.xSize = 179;
		this.ySize = 256;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		//add labels to different parts of the inventory
		this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 10204912);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 92, 10204912);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		//draw texture for the inventory
		GlStateManager.color(1.0f,1.0f,1.0f);
		this.mc.getTextureManager().bindTexture(GUI_GLASS);
		this.drawTexturedModalRect(this.guiLeft+1, this.guiTop+2, 0, 0, this.xSize, this.ySize);
	}
}
