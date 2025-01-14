package com.moonstone.moonstonemod.content;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

public class CuriosHandler {
	public static boolean hascurio(LivingEntity entity, Item curio) {
		return (entity != null && CuriosApi.getCuriosHelper().findFirstCurio(entity, curio).isPresent());
	}
}
