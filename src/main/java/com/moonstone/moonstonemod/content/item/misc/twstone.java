package com.moonstone.moonstonemod.content.item.misc;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class twstone extends Item {
	public twstone() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("twstone", ChatFormatting.RED)));
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::bbb);
		MinecraftForge.EVENT_BUS.addListener(this::ccc);
		MinecraftForge.EVENT_BUS.addListener(this::ddd);
	}


	private void bbb(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
				Map<String, ICurioStacksHandler> curios = handler.getCurios();
				for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
					ICurioStacksHandler stacksHandler = entry.getValue();
					IDynamicStackHandler stackHandler = stacksHandler.getStacks();
					for (int i = 0; i < stacksHandler.getSlots(); i++) {
						ItemStack stack = stackHandler.getStackInSlot(i);
						if (stack.hasTag()) {
							if (!stack.isEmpty()) {
								if (stack.getOrCreateTag().get("tw_stone") != null) {
									if (CuriosHandler.hascurio(player, stack.getItem())) {
										event.setAmount(event.getAmount() * 1.03f);
									}
								}
							}
						}
					}
				}
			});

		}
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
								if (stack.getOrCreateTag().get("tw_stone") != null) {
									if (CuriosHandler.hascurio(player, stack.getItem())) {
										event.setAmount(event.getAmount() * 1.05f);
									}
								}
							}
						}
					}
				}
			});

		}
	}

	private void ddd(LivingHurtEvent event) {
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
								if (stack.getOrCreateTag().get("tw_stone") != null) {
									if (CuriosHandler.hascurio(player, stack.getItem())) {
										event.setAmount(event.getAmount() * 0.99f);
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
				if (you.is(Init.twstone.get())) {
					event.setCost(10);
					event.setMaterialCost(10);
					event.setOutput(zuo);
					CompoundTag tag = new CompoundTag();
					zuo.addTagElement("moonstone_stone", tag);
					zuo.addTagElement("tw_stone", tag);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("· 此物品必须强化到饰品上生效").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable("· 强化到饰品上时：").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("· + 1% 伤害抗性").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("· + 3% 攻击伤害").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("· + 5% 生命恢复").withStyle(ChatFormatting.RED));


	}
}
