package com.moonstone.moonstonemod.content.item.ectoplasm;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EctoplasmCloud extends Item {
	public EctoplasmCloud() {
		super(new Properties().stacksTo(64).rarity(Rarity.RARE).food(
				new FoodProperties.Builder().alwaysEat().nutrition(8).saturationMod(0.8f).build()));
	}
}
