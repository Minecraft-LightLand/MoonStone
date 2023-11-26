package com.moonstone.moonstonemod.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;


@Mod.EventBusSubscriber(modid = MoonstoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
			.comment("Equip Seven Curse")
			.define("Equip", false);
	private static final ForgeConfigSpec.BooleanValue book = BUILDER
			.comment("Give Item")
			.define("Give", true);


	static final ForgeConfigSpec SPEC = BUILDER.build();

	public static boolean logDirtBlock;
	public static boolean GiveBook;


	@SubscribeEvent
	static void onLoad(final ModConfigEvent event) {
		logDirtBlock = LOG_DIRT_BLOCK.get();
		GiveBook = book.get();
	}

}
