package com.moonstone.moonstonemod.Item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class cave extends Item {
    public cave() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

}
