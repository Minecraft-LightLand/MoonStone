package com.moonstone.moonstonemod.Item.MSLoot;

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

public class bastion_treasure extends LootModifier {

    public static final Codec<bastion_treasure> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, bastion_treasure::new));
    }

    protected bastion_treasure(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        int e= Mth.nextInt(RandomSource.create(), 1, 3);
        int t= Mth.nextInt(RandomSource.create(), 1, 7);
        generatedLoot.add(new ItemStack(InIt.twstone.get(), 1));

        if (e == 1){
            generatedLoot.add(new ItemStack(InIt.twistedrock.get(), 1));
        }
        if (t == 1){
            generatedLoot.add(new ItemStack(InIt.twistedyoke.get(), 1));
        }
        if (t == 2){
            generatedLoot.add(new ItemStack(InIt.twistedhand.get(), 1));
        }
        return generatedLoot;
    }

    public Codec<bastion_treasure> codec() {
        return CODEC;
    }
}


