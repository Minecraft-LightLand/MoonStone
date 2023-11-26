package com.moonstone.moonstonemod.content.item.medicine.extend;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class TheNecoraIC extends Item {
	public TheNecoraIC() {
		super(new Properties().stacksTo(1).defaultDurability(7200).rarity(Rarity.create("asdasda", ChatFormatting.RED)));
	}
}

