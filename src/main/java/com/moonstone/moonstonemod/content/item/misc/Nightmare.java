package com.moonstone.moonstonemod.content.item.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class Nightmare extends Item {
	public Nightmare() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("sdas", ChatFormatting.RED)));

	}
}
