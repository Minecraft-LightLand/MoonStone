package com.moonstone.moonstonemod.content.item.MoonStoneItem;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class Do extends Item {
	public Do() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("sdas", ChatFormatting.RED)));

	}
}

