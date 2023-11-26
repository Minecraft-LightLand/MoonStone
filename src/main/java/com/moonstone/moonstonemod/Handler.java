package com.moonstone.moonstonemod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

public class Handler {
    public static boolean hascurio(LivingEntity entity, Item curio) {
        return (entity != null && CuriosApi.getCuriosHelper().findFirstCurio(entity, curio).isPresent());
    }
}
