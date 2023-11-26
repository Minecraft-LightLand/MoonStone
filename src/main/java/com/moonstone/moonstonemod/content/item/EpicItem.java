package com.moonstone.moonstonemod.content.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EpicItem extends Item {
	public EpicItem() {
		super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
	}
}