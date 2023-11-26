package com.moonstone.moonstonemod.Item.Amout;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.InIt;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class Event extends Item {
    public Event() {
        super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
    }

    private void bbb(PlayerEvent.PlayerLoggedInEvent event) {
        if (Config.GiveBook) {
            if (event.getEntity() != null) {
                Player player = event.getEntity();
                if (!player.getTags().contains("welcome_to_moonstone")) {
                    int a = Mth.nextInt(RandomSource.create(), 1, 3);
                    if (a == 1) {
                        player.addItem(InIt.ectoplasmstone.get().getDefaultInstance());
                    }
                    if (a == 2) {
                        player.addItem(InIt.twistedstone.get().getDefaultInstance());
                    }
                    if (a == 3) {
                        player.addItem(InIt.goldstone.get().getDefaultInstance());
                    }
                    player.addTag("welcome_to_moonstone");
                }
                if (!player.getTags().contains("book")) {
                    player.addItem(InIt.soulbook.get().getDefaultInstance());
                    player.addTag("book");
                }
                if (!player.getTags().contains("moon_apple")) {
                    player.addItem(InIt.apple.get().getDefaultInstance());
                    player.addTag("moon_apple");
                }
            }
        }
    }
}
