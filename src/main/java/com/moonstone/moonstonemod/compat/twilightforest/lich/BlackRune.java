package com.moonstone.moonstonemod.compat.twilightforest.lich;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.compat.twilightforest.base.TFBaseItem;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class BlackRune extends TFBaseItem {
	public BlackRune() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::ccc);
	}

	private void ccc(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty() && !you.isEmpty()) {
			if (zuo.is(Init.redamout.get()) && you.is(Init.lichhead.get())) {
				event.setCost(15);
				event.setMaterialCost(1);
				event.setOutput(this.getDefaultInstance());
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (event.getEntity().getArmorValue() != 0) {
					float a = event.getEntity().getArmorValue();
					float b = a / event.getEntity().getMaxHealth();
					if (b > 1) {
						b = 1;
					}
					event.setAmount(event.getAmount() * (1 + b));
				} else {
					event.setAmount(event.getAmount() * (1 + 0.35f));
				}
			}
		}

		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (player.getMainHandItem().getItem() instanceof SwordItem
						&& player.getMainHandItem().getItem() instanceof PickaxeItem
						&& player.getMainHandItem().getItem() instanceof AxeItem) {
					event.setAmount(event.getAmount() * 0.8f);
				}
			}
		}
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setAmount(event.getAmount() * 0.95f);
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
				tooltip.add(Component.translatable("· 你对拥有护甲的生物造成额外伤害").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 护甲越多，造成的伤害越多(最大100%)").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 减少你剑,斧,镐的伤害").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 同时增加对没有护甲的生物的伤害").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("- 增加5%伤害抗性").withStyle(ChatFormatting.GOLD));
			} else {
				tooltip.add(Component.translatable("·按下 SHIFT 查看详情").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
			}
		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));
		}
	}
}
