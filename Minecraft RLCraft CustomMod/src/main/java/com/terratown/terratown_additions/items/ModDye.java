package com.terratown.terratown_additions.items;

import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ModDye extends ItemBase{
	
	String NAME;
	EnumDyeColor DYE_COLOR;
	
	
	public ModDye(String name, CreativeTabs tab) {
		super(name, tab);
		NAME = name;
		
		ModItems.ITEMS.add(this);
	}
	private void getColorByName() {
		if(NAME == "blue_dye") DYE_COLOR = EnumDyeColor.BLUE;
		if(NAME == "white_dye") DYE_COLOR = EnumDyeColor.WHITE;
		if(NAME == "light_gray_dye") DYE_COLOR = EnumDyeColor.SILVER;
	}
	
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        if (target instanceof EntitySheep)
        {
            EntitySheep entitysheep = (EntitySheep)target;
            getColorByName();
            EnumDyeColor enumdyecolor = DYE_COLOR;

            if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor)
            {
                entitysheep.setFleeceColor(enumdyecolor);
                stack.shrink(1);
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
