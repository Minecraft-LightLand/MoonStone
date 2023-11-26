package com.moonstone.moonstonemod.content.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.init.Init;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class abandoned_mineshaft extends LootModifier {

	public static final Codec<abandoned_mineshaft> CODEC;

	static {
		CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, abandoned_mineshaft::new));
	}

	protected abandoned_mineshaft(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@NotNull
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {


		int p = Mth.nextInt(RandomSource.create(), 1, 30);
		int aaa = Mth.nextInt(RandomSource.create(), 1, 25);
		if (aaa == 1) {
			generatedLoot.add(new ItemStack(Init.soulbattery.get(), 1));
		}
		if (aaa == 2) {
			generatedLoot.add(new ItemStack(Init.airdnarune.get(), 1));
		}
		if (p == 1) {
			generatedLoot.add(new ItemStack(Init.warcrystal.get(), 1));
		}
		if (p == 2) {
			generatedLoot.add(new ItemStack(Init.greedcrystal.get(), 1));
		}
		if (p == 3) {
			generatedLoot.add(new ItemStack(Init.undead.get(), 1));
		}
		if (p == 3) {
			generatedLoot.add(new ItemStack(Init.goldbook.get(), 1));
		}
		if (p == 4) {
			generatedLoot.add(new ItemStack(Init.deerbadge.get(), 1));
		}
		if (p == 6) {
			generatedLoot.add(new ItemStack(Init.redamout.get(), 1));
		}
		if (p == 7) {
			generatedLoot.add(new ItemStack(Init.blueamout.get(), 1));
		}
		if (p == 8) {
			generatedLoot.add(new ItemStack(Init.greedamout.get(), 1));
		}
		if (p == 9) {
			generatedLoot.add(new ItemStack(Init.treasure.get(), 1));
		}
		if (p == 10) {
			generatedLoot.add(new ItemStack(Init.blessingseye.get(), 1));
		}
		if (p == 11) {
			generatedLoot.add(new ItemStack(Init.battery.get(), 1));
		}
		if (p == 12) {
			generatedLoot.add(new ItemStack(Init.whiteorb.get(), 1));
		}
		if (p == 13) {
			generatedLoot.add(new ItemStack(Init.evilmug.get(), 1));
		}
		if (p == 14) {
			generatedLoot.add(new ItemStack(Init.obsidianring.get(), 1));
		}


		return generatedLoot;
	}

	public Codec<abandoned_mineshaft> codec() {
		return CODEC;
	}
}



