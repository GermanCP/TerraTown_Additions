package com.terratown.terratown_additions.blocks.gui;

import com.terratown.terratown_additions.blocks.container.ContainerMortar;
import com.terratown.terratown_additions.blocks.tileentity.TileEntityMortarBlock;
import com.terratown.terratown_additions.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * This class defines inventoryrender and texture for the mortar-block
 * @author KamiKatze
 */

public class GuiMortarBlock extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/mortar_block.png");
	private final InventoryPlayer player;
	private TileEntityMortarBlock tileentity;
	
	public GuiMortarBlock(InventoryPlayer player, TileEntityMortarBlock tileentity) 
	{
		super(new ContainerMortar(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		//label different parts of the inventory
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
			
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		//set texture for the inventory
		GlStateManager.clearColor(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		//set texture for the grinding animation
		if(TileEntityMortarBlock.isGrinding(tileentity))
		{
			//working animation
			int k = this.getPestleLeftScaled(16);
			this.drawTexturedModalRect(this.guiLeft + 58, this.guiTop + 34 + 15 - k, 178, 15 - k, 15, k + 1);
			
			//progress animation
			int l = this.getGrindProcessScaled(23);
			this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 35, 176, 17, l + 1, 18);
		}
		
	}
	
	//method for determining the scale of the working animation
	private int getPestleLeftScaled(int pixels)
	{
		int i = this.tileentity.getField(1);
		if(i == 0) i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}
	
	//method for determining the scale of the progress animation
	private int getGrindProcessScaled(int pixels)
	{
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
