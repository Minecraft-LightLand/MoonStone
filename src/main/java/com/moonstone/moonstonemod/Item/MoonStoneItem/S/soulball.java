package com.moonstone.moonstonemod.Item.MoonStoneItem.S;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class soulball extends Item{
    public soulball() {
        super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
    }
}
