package com.moonstone.moonstonemod.content.item.soul;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class soul extends Item {
	public soul() {
		super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public boolean isFoil(ItemStack p_41453_) {
		return true;
	}
}
