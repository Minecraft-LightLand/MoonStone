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

public class ancient_city extends LootModifier {

    public static final Codec<ancient_city> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, ancient_city::new));
    }

    protected ancient_city(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(InIt.slate.get(), 1));
        int p= Mth.nextInt(RandomSource.create(), 1, 15);


        if (Mth.nextInt(RandomSource.create(), 1, 50)== 1){
            generatedLoot.add(new ItemStack(InIt.soulapple.get(), 1));
        }
        int rage= Mth.nextInt(RandomSource.create(), 1, 60);
        if (rage == 1){
            generatedLoot.add(new ItemStack(InIt.rageeye.get(), 1));
        }
        if (rage == 2){
            generatedLoot.add(new ItemStack(InIt.rageapple.get(), 1));
        }

        if (rage == 3){
            generatedLoot.add(new ItemStack(InIt.ragepear.get(), 1));
        }

        if (rage == 4){
            generatedLoot.add(new ItemStack(InIt.ragebox.get(), 1));
        }


        if (p == 1){
            generatedLoot.add(new ItemStack(InIt.nanorobot.get(), 1));
        }
        if (p == 2){
            generatedLoot.add(new ItemStack(InIt.nanocube.get(), 1));
        }
        if (p == 3){
            generatedLoot.add(new ItemStack(InIt.whiteorb.get(), 1));
        }
        if (p == 4){
            generatedLoot.add(new ItemStack(InIt.blackhead.get(), 1));
        }
        return generatedLoot;
    }

    public Codec<ancient_city> codec() {
        return CODEC;
    }
}




