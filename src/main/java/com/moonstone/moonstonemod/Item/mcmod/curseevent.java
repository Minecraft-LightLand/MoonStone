package com.moonstone.moonstonemod.Item.mcmod;

import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.ISlotType;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class curseevent extends AAA {
    public curseevent(){
        MinecraftForge.EVENT_BUS.addListener(this::KILL);
    }

    private final String SEVEN_ = "seven";
    private void KILL(PlayerEvent.PlayerLoggedInEvent event) {
        if (Config.logDirtBlock) {
            if (event.getEntity() != null) {
                Player player = event.getEntity();
                if (!player.getTags().contains("seven_c")) {
                    CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (!Handler.hascurio(player, InIt.sevencurse.get())) {
                                    if (!player.getPersistentData().getBoolean(SEVEN_)) {
                                        if (stack.isEmpty()) {
                                            for (ISlotType iSlotType : CuriosApi.getSlots().values()) {
                                                if (iSlotType.getIdentifier().contains("charm")) {
                                                    CuriosApi.getCuriosHelper().setEquippedCurio(player, "charm", i, InIt.sevencurse.get().getDefaultInstance());
                                                    player.getPersistentData().putBoolean(SEVEN_, true);

                                                    player.addTag("seven_c");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }

            }
        }
    }
}
