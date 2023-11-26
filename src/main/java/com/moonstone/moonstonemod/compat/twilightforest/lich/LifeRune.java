package com.moonstone.moonstonemod.compat.twilightforest.lich;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.compat.twilightforest.base.TFBaseItem;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class LifeRune extends TFBaseItem {
	public LifeRune() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::bbb);
		MinecraftForge.EVENT_BUS.addListener(this::ccc);
	}

	private void bbb(LivingHealEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (player.getCooldowns().isOnCooldown(this)) {
					event.setAmount(event.getAmount() * 2);
				}
			}
		}
	}

	private void ccc(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty() && !you.isEmpty()) {
			if (zuo.is(Init.greedamout.get()) && you.is(Init.lichhead.get())) {
				event.setCost(15);
				event.setMaterialCost(1);
				event.setOutput(this.getDefaultInstance());
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (event.getSource().getEntity() instanceof LivingEntity living) {
					if (living.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof AxeItem) {
						event.setAmount(event.getAmount() * 0.45f);
					}
				}
			}
		}


		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (!player.getCooldowns().isOnCooldown(this)) {
					player.heal(player.getMaxHealth() / 4);
					player.getCooldowns().addCooldown(this, 150);
				} else {
					event.setAmount(event.getAmount() / 2);
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
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 攻击后立即回复25%最大生命值").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 随后此物品会进入7.5秒的冷却时间").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 在物品冷却期间攻击力下降50%").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 但增加100%的任何生命回复效果").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("- 减少55%来自斧头的伤害").withStyle(ChatFormatting.GOLD));
			} else {
				tooltip.add(Component.translatable("·按下 SHIFT 查看详情").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
			}
		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));
		}
	}
}
