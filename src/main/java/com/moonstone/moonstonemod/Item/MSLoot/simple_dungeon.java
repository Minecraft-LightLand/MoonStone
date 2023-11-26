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


        int p= Mth.nextInt(RandomSource.create(), 1, 20);

        int aaa= Mth.nextInt(RandomSource.create(), 1, 25);
        if (aaa == 1){
            generatedLoot.add(new ItemStack(InIt.soulbattery.get(), 1));
        }
        if (aaa == 2){
            generatedLoot.add(new ItemStack(InIt.airdnarune.get(), 1));
        }
        if (p == 1){
            generatedLoot.add(new ItemStack(InIt.warcrystal.get(), 1));
        }
        if (p == 2){
            generatedLoot.add(new ItemStack(InIt.greedcrystal.get(), 1));
        }
        if (p == 3){
            generatedLoot.add(new ItemStack(InIt.undead.get(), 1));
        }
        if (p == 3){
            generatedLoot.add(new ItemStack(InIt.goldbook.get(), 1));
        }
        if (p == 4){
            generatedLoot.add(new ItemStack(InIt.deerbadge.get(), 1));
        }
        if (p == 6){
            generatedLoot.add(new ItemStack(InIt.redamout.get(), 1));
        }
        if (p == 7){
            generatedLoot.add(new ItemStack(InIt.blueamout.get(), 1));
        }
        if (p == 8){
            generatedLoot.add(new ItemStack(InIt.greedamout.get(), 1));
        }
        if (p == 9){
            generatedLoot.add(new ItemStack(InIt.treasure.get(), 1));
        }
        if (p == 10){
            generatedLoot.add(new ItemStack(InIt.blessingseye.get(), 1));
        }
        if (p == 11){
            generatedLoot.add(new ItemStack(InIt.battery.get(), 1));
        }
        if (p == 12){
            generatedLoot.add(new ItemStack(InIt.whiteorb.get(), 1));
        }
        if (p == 13){
            generatedLoot.add(new ItemStack(InIt.evilmug.get(), 1));
        }
        if (p == 14){
            generatedLoot.add(new ItemStack(InIt.obsidianring.get(), 1));
        }
        if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player) {
            if (Handler.hascurio(player, InIt.necora.get())) {
                int a = Mth.nextInt(RandomSource.create(), 1, 10);
                if (a == 1) {
                    generatedLoot.add(new ItemStack(InIt.regenerative.get(), 1));
                }
            }
        }
        return generatedLoot;
    }

    public Codec<simple_dungeon> codec() {
        return CODEC;
    }
}




