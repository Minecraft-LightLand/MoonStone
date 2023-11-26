package com.moonstone.moonstonemod.Item.MSLoot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class goldbook extends LootModifier {

    public static final Codec<goldbook> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, goldbook::new));
    }

    protected goldbook(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player) {
            if (Handler.hascurio(player, InIt.goldbook.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                    generatedLoot.add(new ItemStack(Items.GOLD_INGOT, 1));
                }
            }
        }
        return generatedLoot;
    }

    public Codec<goldbook> codec() {
        return CODEC;
    }
}

