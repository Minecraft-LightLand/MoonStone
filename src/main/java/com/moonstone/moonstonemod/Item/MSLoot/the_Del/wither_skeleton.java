package com.moonstone.moonstonemod.Item.MSLoot.the_Del;

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

public class wither_skeleton extends LootModifier {

    public static final Codec<wither_skeleton> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, wither_skeleton::new));
    }

    protected wither_skeleton(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        int e= Mth.nextInt(RandomSource.create(), 1, 11);
        if (e == 1){
            generatedLoot.add(new ItemStack(InIt.ectoplasmball.get(), 2));
        }
        if (e == 2){
            generatedLoot.add(new ItemStack(InIt.ectoplasmcloub.get(), 1));
        }




        int s=Mth.nextInt(RandomSource.create(), 1, 10);
        if (s == 1){
            generatedLoot.add(new ItemStack(InIt.soul.get(), 4));
        }
        if (s == 2){
            generatedLoot.add(new ItemStack(InIt.soulball.get(), 2));
        }
        if (s == 3){
            generatedLoot.add(new ItemStack(InIt.soulcloub.get(),1));
        }

        int b= Mth.nextInt(RandomSource.create(), 1, 50);
        if (b == 1){
            generatedLoot.add(new ItemStack(InIt.twistedbadge.get(), 1));
        }
        return generatedLoot;
    }

    public Codec<wither_skeleton> codec() {
        return CODEC;
    }
}


