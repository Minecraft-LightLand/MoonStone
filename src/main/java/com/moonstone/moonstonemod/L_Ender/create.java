package com.moonstone.moonstonemod.L_Ender;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.InIt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.ModList;

public class create extends AAA {
    public create() {
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
        MinecraftForge.EVENT_BUS.addListener(this::ccc);
    }

    private void ccc(AnvilUpdateEvent event) {
        ItemStack you = event.getRight();
        ItemStack zuo = event.getLeft().copy();
        if (!zuo.isEmpty() && !you.isEmpty()) {
            if (ModList.get().isLoaded("cataclysm")) {
                if (zuo.is(InIt.battery.get()) && you.is(ModItems.IGNITIUM_INGOT.get())) {
                    event.setCost(30);
                    event.setMaterialCost(30);
                    event.setOutput(InIt.hellbattery.get().getDefaultInstance());
                }
            }
        }

    }

    private void bbb(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() != null) {
            Player player =event.getEntity();
            if (!Config.logDirtBlock) {
                if (!player.getTags().contains("welcome_to_moonstone_l_ender")) {
                    if (ModList.get().isLoaded("cataclysm")) {
                        player.addItem(InIt.sevencurse.get().getDefaultInstance());
                        player.addTag("welcome_to_moonstone_l_ender");
                    }
                }
            }
        }
    }
}
