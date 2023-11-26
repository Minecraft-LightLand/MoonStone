package com.moonstone.moonstonemod.compat.twilightforest.base;

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
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class Loot extends LootModifier {

	public static final Codec<Loot> CODEC;

	static {
		CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, Loot::new));
	}

	protected Loot(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@NotNull
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

		if (ModList.get().isLoaded("twilightforest")) {
			int a = Mth.nextInt(RandomSource.create(), 1, 20);
			if (a == 1) {
				generatedLoot.add(new ItemStack(Init.reshuffle.get()));
			}
			if (a == 2) {
				generatedLoot.add(new ItemStack(Init.sensitivity.get()));
			}
			if (a == 3) {
				generatedLoot.add(new ItemStack(Init.iondeficit.get()));
			}
			if (a == 4) {
				generatedLoot.add(new ItemStack(Init.ionsurge.get()));
			}
		}
		return generatedLoot;
	}

	public Codec<Loot> codec() {
		return CODEC;
	}
}


