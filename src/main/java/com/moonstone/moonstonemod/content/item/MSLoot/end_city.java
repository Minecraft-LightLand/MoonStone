package com.moonstone.moonstonemod.content.item.MSLoot;

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

public class end_city extends LootModifier {

	public static final Codec<end_city> CODEC;

	static {
		CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, end_city::new));
	}

	protected end_city(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@NotNull
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {


		int doom = Mth.nextInt(RandomSource.create(), 1, 20);


		if (doom == 1) {
			generatedLoot.add(new ItemStack(Init.doomfruit.get(), 1));
		}
		return generatedLoot;
	}

	public Codec<end_city> codec() {
		return CODEC;
	}
}





