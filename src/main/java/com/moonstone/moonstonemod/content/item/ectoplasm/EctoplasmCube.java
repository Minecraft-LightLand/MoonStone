package com.moonstone.moonstonemod.content.item.ectoplasm;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EctoplasmCube extends Item {
	public EctoplasmCube() {
		super(new Properties().stacksTo(64).rarity(Rarity.EPIC).food(
				new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(1.0f).build()));
	}
}

