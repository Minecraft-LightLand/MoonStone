package com.moonstone.moonstonemod.compat.alexcave;

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

public class conchloot extends LootModifier {

	public static final Codec<conchloot> CODEC;

	static {
		CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, conchloot::new));
	}

	protected conchloot(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@NotNull
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

		int a = Mth.nextInt(RandomSource.create(), 1, 20);
		if (a == 1) {
			generatedLoot.add(new ItemStack(Init.conch.get()));
		}
		return generatedLoot;
	}

	public Codec<conchloot> codec() {
		return CODEC;
	}
}



