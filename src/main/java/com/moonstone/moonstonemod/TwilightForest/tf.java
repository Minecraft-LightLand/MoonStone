package com.moonstone.moonstonemod.TwilightForest;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.MinecraftForge;

public class tf  extends Item {
    public tf() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
    }
}
