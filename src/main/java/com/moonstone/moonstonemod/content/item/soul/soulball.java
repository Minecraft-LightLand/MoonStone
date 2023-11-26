package com.moonstone.moonstonemod.content.item.soul;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class soulball extends Item {
	public soulball() {
		super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
	}
}
