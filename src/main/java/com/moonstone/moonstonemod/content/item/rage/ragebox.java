package com.moonstone.moonstonemod.content.item.rage;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.Rage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class ragebox extends Rage {
	public static float a;

	public ragebox() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setAmount(event.getAmount() * 0.8f);
			}

		}

		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setAmount(event.getAmount() * 1.45f);
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
					a += 0.05F;
					if (player.getHealth() < (player.getMaxHealth() / 2)) {
						if (player.isAlive()) {
							player.kill();
						}
					}
				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {

				}
			}

			@Override
			public boolean canEquip(SlotContext slotContext) {
				boolean aaa = false;
				if (slotContext.entity() instanceof Player player) {
					aaa = !CuriosHandler.hascurio(player, stack.getItem());
				}
				return aaa;

			}
		});
	}


	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		if (Screen.hasShiftDown()) {

			tooltip.add(Component.translatable("· +45% 攻击伤害").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +20% 伤害抗性").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 但生命值降低到50%以下将立即死亡").withStyle(ChatFormatting.RED));


		} else {
			tooltip.add(Component.translatable("· Shift").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
		}

		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("· 在开启饰品栏上方的按钮后").withStyle(ChatFormatting.GOLD));
		tooltip.add(Component.translatable("· 召唤一个小反应炉在头顶旋转").withStyle(ChatFormatting.GOLD));

	}
}
