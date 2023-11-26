package com.moonstone.moonstonemod.content.item.E;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ectoplasmball extends Item {
	public ectoplasmball() {
		super(new Properties().stacksTo(64).rarity(Rarity.RARE).food(
				new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(0.6f).build()));
	}

}
