package com.moonstone.moonstonemod.Item.MoonStoneItem;

import com.moonstone.moonstonemod.tab;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class rage extends Item {
    public rage() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("sdas", ChatFormatting.DARK_RED)));

    }
}

