package com.terratown.terratown_additions.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MortarRecipes 
{
	private static final MortarRecipes INSTANCE = new MortarRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> grindingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static MortarRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private MortarRecipes() 
	{
		//add Recipes here
		
		//1 orange
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.YELLOW.getDyeDamage()), 
				new ItemStack(Items.DYE, 1, EnumDyeColor.RED.getDyeDamage()), 
				new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage()), 0.1F);
		//2 light blue
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), 0.1F);
		//3 cyan
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.CYAN.getDyeDamage()), 0.1F);
		//4 pink
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.RED.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage()), 0.1F);
		//5 purple
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.RED.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.PURPLE.getDyeDamage()), 0.1F);
		//6 magenta
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.PURPLE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.MAGENTA.getDyeDamage()), 0.1F);
		//7 lime
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.LIME.getDyeDamage()), 0.1F);
		//8 light gray (SILVER??? wtf)
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.GRAY.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.SILVER.getDyeDamage()), 0.1F);
		//9 gray
		addMortarRecipes(new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()),
				new ItemStack(Items.DYE, 1, EnumDyeColor.GRAY.getDyeDamage()), 0.1F);
		
		
		
		
		
		
	}
	
	public void addMortarRecipes(ItemStack input1, ItemStack input2, ItemStack result, float experience)
	{
		if(getGrindingResult(input1, input2) != ItemStack.EMPTY) {return;}
		this.grindingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getGrindingResult(ItemStack input1, ItemStack input2)
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.grindingList.columnMap().entrySet())
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
			{
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
					{
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 
				|| stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualGrindingList()
	{
		return this.grindingList;
	}
	
	public float getGrindingExperience(ItemStack stack)
	{
		//float ret = stack.getItem().getSmeltingExperience(stack);
        //if (ret != -1) return ret;
        
		for(Entry<ItemStack, Float> entry : this.experienceList.entrySet())
		{
			if(this.compareItemStacks(stack,  (ItemStack)entry.getKey()))
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
	
	
}
