package com.moonstone.moonstonemod.compat.twilightforest.base;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class TFBaseItem extends Item {
	public TFBaseItem() {
		super(new Properties().stacksTo(1).rarity(Rarity.RARE));
	}
}
