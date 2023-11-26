package com.moonstone.moonstonemod.TwilightForest.Lich;

import com.moonstone.moonstonemod.TwilightForest.tf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.ModList;
import twilightforest.entity.boss.Lich;

public class lichhead extends tf {

    public lichhead(){
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
    }
    private void bbb(LivingDropsEvent event) {
        if (ModList.get().isLoaded("twilightforest")) {
            if (event.getEntity() instanceof Lich lich) {
                event.getDrops().add(new ItemEntity(lich.level(),
                        lich.getX(), lich.getY(), lich.getZ(),
                        new ItemStack(this)));
            }
        }
    }
}
