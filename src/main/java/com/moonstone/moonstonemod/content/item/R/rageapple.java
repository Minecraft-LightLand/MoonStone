package com.moonstone.moonstonemod.content.item.R;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.Rage;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class rageapple extends Rage {
	public float aFloat;

	public rageapple() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				player.heal((float) (event.getAmount() * (1 + (acc(player) * 0.3 / 100))));
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
					aFloat = acc(player);
					player.getAttributes().addTransientAttributeModifiers(the_pla(player, stack));

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
					player.getAttributes().removeAttributeModifiers(the_pla(player, stack));

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
			tooltip.add(Component.translatable("· +0.75% 护甲值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +0.65% 攻速值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +0.55% 抗击退").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +0.50% 伤害值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +0.45% 生命值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +0.30% 吸血值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 这些数值会根据你身上的附魔总等级来增加").withStyle(ChatFormatting.DARK_RED));
			tooltip.add(Component.translatable("· 附魔点数最多累计到75点").withStyle(ChatFormatting.DARK_RED));

			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 目前：").withStyle(ChatFormatting.DARK_RED));
			tooltip.add(Component.translatable("· +" + aFloat * 0.75 + "% 护甲值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +" + aFloat * 0.65 + "% 攻速值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +" + aFloat * 0.55 + "% 抗击退").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +" + aFloat * 0.50 + "% 伤害值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +" + aFloat * 0.45 + "% 生命值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +" + aFloat * 0.30 + "% 吸血值").withStyle(ChatFormatting.RED));
		} else {
			tooltip.add(Component.translatable("· Shift").withStyle(ChatFormatting.RED));
		}
	}

	public Multimap<Attribute, AttributeModifier> the_pla(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();

		modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("97b38aa9-8c8b-4b50-8b69-f71db9a5c2d3"), MoonstoneMod.MODID + "souaasdsdaasdsdasdfasdadsl", acc(player) * 0.5 / 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("97b38aa9-8c8b-4b50-8b69-f71db9a5c2d3"), MoonstoneMod.MODID + "souaasdsdfasdvfvfvdadsl", acc(player) * 0.65 / 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("97b38aa9-8c8b-4b50-8b69-f71db9a5c2d3"), MoonstoneMod.MODID + "souaasdsdfasdaewretretdsl", acc(player) * 0.45 / 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.fromString("97b38aa9-8c8b-4b50-8b69-f71db9a5c2d3"), MoonstoneMod.MODID + "souaujujujasdsdfasdadsl", acc(player) * 0.55 / 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("97b38aa9-8c8b-4b50-8b69-f71db9a5c2d3"), MoonstoneMod.MODID + "souaasdsdfaikikiksdadsl", acc(player) * 0.75 / 100, AttributeModifier.Operation.MULTIPLY_TOTAL));


		return modifierMultimap;
	}

	private float acc(Player player) {
		float size = 0;
		List<ItemStack> slot_stack = Lists.newArrayList();
		Iterable<ItemStack> S = player.getArmorSlots();
		for (ItemStack sck : S) {
			slot_stack.add(sck);
		}

		for (ItemStack stack : slot_stack) {
			if (!stack.isEmpty() && stack.isEnchanted()) {

				Map<Enchantment, Integer> map = stack.getAllEnchantments();
				for (Enchantment enchantment : map.keySet()) {

					if (!enchantment.isCurse()) {
						int lvl = stack.getEnchantmentLevel(enchantment);
						if (size < 75) {
							size += lvl;
						} else {
							size = 75;
						}
					}
				}

			}
		}

		return size;
	}
}
