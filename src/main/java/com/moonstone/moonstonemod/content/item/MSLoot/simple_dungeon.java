package com.moonstone.moonstonemod.content.item.MSLoot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.init.Init;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class simple_dungeon extends LootModifier {

	public static final Codec<simple_dungeon> CODEC;

	static {
		CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, simple_dungeon::new));
	}

	protected simple_dungeon(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@NotNull
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {


		int p = Mth.nextInt(RandomSource.create(), 1, 20);

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
		if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player) {
			if (CuriosHandler.hascurio(player, Init.necora.get())) {
				int a = Mth.nextInt(RandomSource.create(), 1, 10);
				if (a == 1) {
					generatedLoot.add(new ItemStack(Init.regenerative.get(), 1));
				}
			}
		}
		return generatedLoot;
	}

	public Codec<simple_dungeon> codec() {
		return CODEC;
	}
}




