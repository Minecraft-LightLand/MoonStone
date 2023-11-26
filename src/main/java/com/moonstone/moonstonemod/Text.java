package com.moonstone.moonstonemod;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class Text extends Item {
    public Text(){
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
    }

    private void aaa(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!stack.isEmpty()
                && stack.hasTag()
                && stack.getOrCreateTag().get("tw_stone") != null)
        {
            event.getToolTip().add(Component.nullToEmpty("§c扭曲"));
        }
    }

}
