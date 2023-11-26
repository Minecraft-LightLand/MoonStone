package com.moonstone.moonstonemod.content.item.misc;

import com.moonstone.moonstonemod.init.Config;
import com.moonstone.moonstonemod.init.Init;
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
						player.addItem(Init.ectoplasmstone.get().getDefaultInstance());
					}
					if (a == 2) {
						player.addItem(Init.twistedstone.get().getDefaultInstance());
					}
					if (a == 3) {
						player.addItem(Init.goldstone.get().getDefaultInstance());
					}
					player.addTag("welcome_to_moonstone");
				}
				if (!player.getTags().contains("book")) {
					player.addItem(Init.soulbook.get().getDefaultInstance());
					player.addTag("book");
				}
				if (!player.getTags().contains("moon_apple")) {
					player.addItem(Init.apple.get().getDefaultInstance());
					player.addTag("moon_apple");
				}
			}
		}
	}
}
