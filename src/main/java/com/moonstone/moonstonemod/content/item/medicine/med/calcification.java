package com.moonstone.moonstonemod.content.item.medicine.med;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.medicine.extend.medIC;
import com.moonstone.moonstonemod.content.item.medicine.extend.medicinebox;
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

public class calcification extends medIC {
	public calcification() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {

				event.setAmount(event.getAmount() * 0.825f);
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
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("·使实验者的内分泌系统变异导致其部分皮肤硬化乃至钙化，增强实验者的战斗优势").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("·增加 17.5% 伤害抗性").withStyle(ChatFormatting.RED));
		} else {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
		}

		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("·医药箱-受伤次数：" + medicinebox.do_hurt).withStyle(ChatFormatting.RED));

	}


}


