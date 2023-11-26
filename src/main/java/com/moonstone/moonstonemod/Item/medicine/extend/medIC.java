package com.moonstone.moonstonemod.Item.medicine.extend;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class medIC extends Item {
    public medIC() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("asdasda", ChatFormatting.RED)));
    }
}
