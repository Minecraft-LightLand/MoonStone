package com.moonstone.moonstonemod.content.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CaveItem extends Item {
	public CaveItem() {
		super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

}
