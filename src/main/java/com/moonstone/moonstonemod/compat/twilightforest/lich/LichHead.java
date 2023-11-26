package com.moonstone.moonstonemod.compat.twilightforest.lich;

import com.moonstone.moonstonemod.compat.twilightforest.base.TFBaseItem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.ModList;
import twilightforest.entity.boss.Lich;

public class LichHead extends TFBaseItem {

	public LichHead() {
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
