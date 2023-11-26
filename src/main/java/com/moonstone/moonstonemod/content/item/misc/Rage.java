package com.moonstone.moonstonemod.content.item.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class Rage extends Item {
	public Rage() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("sdas", ChatFormatting.DARK_RED)));

	}
}

