package com.moonstone.moonstonemod.Item.MoonStoneItem;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class nightmare extends Item {
    public nightmare() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("sdas", ChatFormatting.RED)));

    }
}
