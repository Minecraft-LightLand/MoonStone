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

public class horse extends LootModifier {

	public static final Codec<horse> CODEC;

	static {
		CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, horse::new));
	}

	protected horse(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@NotNull
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

		int e = Mth.nextInt(RandomSource.create(), 1, 11);
		if (e == 1) {
			generatedLoot.add(new ItemStack(Init.woodenhorseshoe.get(), 1));
		}
		return generatedLoot;
	}

	public Codec<horse> codec() {
		return CODEC;
	}
}
