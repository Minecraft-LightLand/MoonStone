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
import java.text.SimpleDateFormat;
import java.util.Date;

public class zombie extends LootModifier {
    Date nowTime = new Date(System.currentTimeMillis());

    SimpleDateFormat sdFormatter = new SimpleDateFormat("MM");

    String now = this.sdFormatter.format(this.nowTime);

    public static final Codec<zombie> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, zombie::new));
    }

    protected zombie(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        int e=Mth.nextInt(RandomSource.create(), 1, 11);
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



        if (this.now.compareTo("06") == 0) {
            int a=Mth.nextInt(RandomSource.create(), 1, 100);
            if (a == 1){
                generatedLoot.add(new ItemStack(InIt.mblock.get()));
            }
            if (a == 2){
                generatedLoot.add(new ItemStack(InIt.mshell.get()));
            }
            if (a == 3){
                generatedLoot.add(new ItemStack(InIt.mbottle.get()));
            }
            if (a == 4){
                generatedLoot.add(new ItemStack(InIt.morb.get()));
            }
            if (a == 5){
                generatedLoot.add(new ItemStack(InIt.mring.get()));
            }
        }

        return generatedLoot;
    }

    public Codec<zombie> codec() {
        return CODEC;
    }
}