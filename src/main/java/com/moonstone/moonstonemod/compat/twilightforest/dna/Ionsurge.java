package com.moonstone.moonstonemod.compat.twilightforest.dna;

import com.google.common.collect.Lists;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class Ionsurge extends Item {
	public static float anInt;

	public Ionsurge() {
		super(new Properties().stacksTo(1).defaultDurability(7200).rarity(Rarity.create("asdsa", ChatFormatting.GOLD)));
		MinecraftForge.EVENT_BUS.addListener(this::ddd);

	}

	private void ddd(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				float a = acc(player);
				double b = (double) a / 10;
				event.setAmount((float) (event.getAmount() * (1 + (b))));
			}
		}
	}

	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundTag unused) {
		return CurioItemCapability.createProvider(new ICurio() {

			@Override
			public ItemStack getStack() {
				return stack;
			}

			@Override
			public void curioTick(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					anInt = acc(player);
				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public boolean canEquip(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					return !CuriosHandler.hascurio(player, stack.getItem());
				}
				return true;

			}
		});
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		if (ModList.get().isLoaded("twilightforest")) {

		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来 获取 它").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("但你 可以 使用它").withStyle(ChatFormatting.RED));
		}
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("基础伤害增加55%").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("但每穿戴一件饰品，伤害减少5%").withStyle(ChatFormatting.GOLD));

			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("目前：" + anInt * 10 + "%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));

		} else {
			tooltip.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.GOLD));
		}
		tooltip.add(Component.translatable(""));
	}

	private float acc(Player player) {
		float size = 5;
		List<ItemStack> slot_stack = Lists.newArrayList();
		CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
			Map<String, ICurioStacksHandler> curios = handler.getCurios();
			for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
				ICurioStacksHandler stacksHandler = entry.getValue();
				IDynamicStackHandler stackHandler = stacksHandler.getStacks();
				for (int i = 0; i < stacksHandler.getSlots(); i++) {
					ItemStack stack = stackHandler.getStackInSlot(i);
					slot_stack.add(stack);
				}
			}
		});

		for (ItemStack stack : slot_stack) {
			if (!stack.isEmpty()) {
				size -= 0.5f;
			}
		}

		return size;

	}
}
