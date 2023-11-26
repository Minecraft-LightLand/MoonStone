package com.moonstone.moonstonemod.content.item.mcmod;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class sevenorb extends Item {
	public sevenorb() {
		super(new Properties().stacksTo(1).rarity(Rarity.EPIC).food(
				new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(1.0f).build()));

	}


	@Override
	public int getUseDuration(ItemStack p_41454_) {
		return 32;
	}
}
