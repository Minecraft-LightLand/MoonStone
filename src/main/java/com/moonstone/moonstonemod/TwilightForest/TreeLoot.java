package com.moonstone.moonstonemod.TwilightForest;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.InIt;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class TreeLoot extends LootModifier {

    public static final Codec<TreeLoot> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, TreeLoot::new));
    }

    protected TreeLoot(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        int a = Mth.nextInt(RandomSource.create(), 1, 10);

        if (a == 1) {
            generatedLoot.add(new ItemStack(InIt.treeorb.get()));
        }
        if (a == 2) {
            generatedLoot.add(new ItemStack(InIt.gammaeye.get()));
        }
        return generatedLoot;
    }

    public Codec<TreeLoot> codec() {
        return CODEC;
    }
}



