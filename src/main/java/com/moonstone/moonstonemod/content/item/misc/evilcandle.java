package com.moonstone.moonstonemod.content.item.misc;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class evilcandle extends EpicItem {
	public evilcandle() {
		MinecraftForge.EVENT_BUS.addListener(this::ccc);
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void ccc(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty() && !you.isEmpty()) {
			if (zuo.is(Items.YELLOW_CANDLE) && you.is(Init.ectoplasmprism.get())) {
				event.setCost(30);
				event.setMaterialCost(1);
				event.setOutput(Init.evilcandle.get().getDefaultInstance());
			}
		}

	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (event.getSource().is(DamageTypes.IN_FIRE) && event.getSource().is(DamageTypes.ON_FIRE) && event.getSource().is(DamageTypes.LAVA)) {
					event.setAmount(event.getAmount() * 0.2f);
				}
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
					player.clearFire();
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
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable("· 熔岩行者").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 抵挡80%熔岩伤害").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("· 快速熄灭你身上火焰").withStyle(ChatFormatting.GOLD));
		} else {
			tooltip.add(Component.translatable("-·邪恶之火。。。。。").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-·按下 SHIFT 查看详情").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));

		}
	}
}
