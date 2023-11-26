package com.moonstone.moonstonemod.content.item.MSLoot.the_Del;

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

public class skeleton extends LootModifier {

	public static final Codec<skeleton> CODEC;

	static {
		CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, skeleton::new));
	}

	protected skeleton(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@NotNull
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		int e = Mth.nextInt(RandomSource.create(), 1, 11);
		if (e == 1) {
			generatedLoot.add(new ItemStack(Init.ectoplasmball.get(), 2));
		}
		if (e == 2) {
			generatedLoot.add(new ItemStack(Init.ectoplasmcloub.get(), 1));
		}
		int s = Mth.nextInt(RandomSource.create(), 1, 10);
		if (s == 1) {
			generatedLoot.add(new ItemStack(Init.soul.get(), 4));
		}
		if (s == 2) {
			generatedLoot.add(new ItemStack(Init.soulball.get(), 2));
		}
		if (s == 3) {
			generatedLoot.add(new ItemStack(Init.soulcloub.get(), 1));
		}

		int b = Mth.nextInt(RandomSource.create(), 1, 40);
		if (b == 1) {
			generatedLoot.add(new ItemStack(Init.badgeofthedead.get(), 1));
		}
		return generatedLoot;
	}

	public Codec<skeleton> codec() {
		return CODEC;
	}
}

