package com.terratown.terratown_additions.items;

import java.util.List;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import com.terratown.terratown_additions.util.IHasModel;


import com.terratown.terratown_additions.Main;
import com.terratown.terratown_additions.init.ModItems;
import com.terratown.terratown_additions.util.EnchantmentUtils;


public class ItemBaseXPTome extends Item implements IHasModel
{
	
	public int MAX_STORAGE; //first 30 levels
	private final Random random = new Random();

	public ItemBaseXPTome(String name, int max_STORAGE)
	{
		MAX_STORAGE = max_STORAGE;
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
		setMaxDamage(max_STORAGE);
		setMaxStackSize(1);
		
		ModItems.ITEMS.add(this);
	}

	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

		if(player.isSneaking() && getXPStored(stack) != MAX_STORAGE)
		{
			int playerXP = EnchantmentUtils.getPlayerXP(player);

			if(playerXP == 0)
				return new ActionResult<>(EnumActionResult.PASS, stack);

			int actuallyStored = addXP(stack, playerXP); //try to store all of the player's levels

			EnchantmentUtils.addPlayerXP(player, -actuallyStored);

			if(!world.isRemote)
				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, (random.nextFloat() - random.nextFloat()) * 0.35F + 0.9F);

			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		}
		else if(!player.isSneaking() && getXPStored(stack) != 0)
		{
			EnchantmentUtils.addPlayerXP(player, getXPStored(stack));
			setStoredXP(stack, 0);

			if(!world.isRemote)
			{
				float pitchMultiplier = player.experienceLevel > 30 ? 1.0F : player.experienceLevel / 30.0F;

				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, pitchMultiplier * 0.75F, 1.0F);
			}

			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		}

		return new ActionResult<>(EnumActionResult.PASS, stack);
	}

	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return getXPStored(stack) > 0;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return false;
	}

	@Override
	public boolean isRepairable()
	{
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		tooltip.add(I18n.format("xpbook.tooltip.1"));
		tooltip.add(I18n.format("xpbook.tooltip.2"));
		tooltip.add(I18n.format("xpbook.tooltip.3", getXPStored(stack), MAX_STORAGE));
	}

	/**
	 * Adds the given amount of XP to the given stack. If that action would exceed the storage capacity, as much XP as possible will be stored.
	 * @param stack The stack to add XP to
	 * @param amount The amount of XP to add
	 * @return The amount XP that was added
	 */
	public int addXP(ItemStack stack, int amount)
	{
		int stored = getXPStored(stack);

		if(stored + amount > MAX_STORAGE)
		{
			setStoredXP(stack, MAX_STORAGE);
			return MAX_STORAGE - stored;
		}
		else
		{
			setStoredXP(stack, stored + amount);
			return amount;
		}
	}

	/**
	 * Sets the amount of XP that is stored in the given stack
	 * @param stack The stack to set the amount of stored XP of
	 * @param amount The amount of XP to set the storage to
	 */
	public void setStoredXP(ItemStack stack, int amount)
	{
		stack.setItemDamage(MAX_STORAGE - amount);
	}

	/**
	 * Gets the amount of XP that the given stack has stored
	 * @param stack The stack to get the amount of stored XP from
	 * @return The amount of stored XP in the stack
	 */
	public int getXPStored(ItemStack stack)
	{
		return MAX_STORAGE - stack.getItemDamage(); //if the damage is 0, the book is full on xp
	}

}
