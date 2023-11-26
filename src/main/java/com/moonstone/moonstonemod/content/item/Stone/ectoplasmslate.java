package com.moonstone.moonstonemod.content.item.Stone;

import com.moonstone.moonstonemod.compat.CuriosHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class ectoplasmslate extends Item {
	public ectoplasmslate() {
		super(new Properties().stacksTo(64).rarity(Rarity.RARE));
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::ccc);

	}

	private void ccc(LivingHealEvent event) {
		if (event.getEntity() instanceof Player player) {
			CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
				Map<String, ICurioStacksHandler> curios = handler.getCurios();
				for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
					ICurioStacksHandler stacksHandler = entry.getValue();
					IDynamicStackHandler stackHandler = stacksHandler.getStacks();
					for (int i = 0; i < stacksHandler.getSlots(); i++) {
						ItemStack stack = stackHandler.getStackInSlot(i);
						if (stack.hasTag()) {
							if (!stack.isEmpty()) {
								if (stack.getOrCreateTag().get("ectoplasm_stone") != null) {
									if (CuriosHandler.hascurio(player, stack.getItem())) {
										event.setAmount(event.getAmount() * 1.09f);
									}
								}
							}
						}
					}
				}
			});

		}
	}

	private void aaa(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty()) {
			if (zuo.getOrCreateTag().get("moonstone_stone") == null) {
				if (you.is(this)) {
					event.setCost(1);
					event.setMaterialCost(10);
					event.setOutput(zuo);
					CompoundTag tag = new CompoundTag();
					zuo.addTagElement("moonstone_stone", tag);
					zuo.addTagElement("ectoplasm_stone", tag);
				}
			}
		}
	}
}


