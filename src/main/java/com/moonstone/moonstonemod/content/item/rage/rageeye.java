package com.moonstone.moonstonemod.content.item.rage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.Rage;
import com.moonstone.moonstonemod.init.Init;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class rageeye extends Rage {

	public String attack = "attack";
	public String health = "health";
	public String attack_speed = "attack_speed";
	public String armor = "armor";

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		boolean a = super.onLeftClickEntity(stack, player, entity);

		if (!CuriosHandler.hascurio(player, Init.rageeye.get())) {
			if (entity instanceof LivingEntity living) {

				if (living.getAttributes().getInstance(Attributes.ATTACK_DAMAGE) != null && player.getAttributes().getValue(Attributes.ATTACK_DAMAGE) != 0) {
					if (living.getAttributes().getValue(Attributes.ATTACK_DAMAGE) / 10 < player.getAttributes().getValue(Attributes.ATTACK_DAMAGE) / 2) {
						stack.getOrCreateTag().putDouble(attack, living.getAttributes().getValue(Attributes.ATTACK_DAMAGE) / 10);
					} else {
						stack.getOrCreateTag().putDouble(attack, player.getAttributes().getValue(Attributes.ATTACK_DAMAGE) / 2);
					}
				}
				if (living.getAttributes().getInstance(Attributes.MAX_HEALTH) != null && player.getAttributes().getValue(Attributes.MAX_HEALTH) != 0) {
					if (living.getAttributes().getValue(Attributes.MAX_HEALTH) / 10 < player.getAttributes().getValue(Attributes.MAX_HEALTH) / 2) {
						stack.getOrCreateTag().putDouble(health, living.getAttributes().getValue(Attributes.MAX_HEALTH) / 10);
					} else {
						stack.getOrCreateTag().putDouble(health, player.getAttributes().getValue(Attributes.MAX_HEALTH) / 2);
					}
				}

				if (living.getAttributes().getInstance(Attributes.ATTACK_SPEED) != null && player.getAttributes().getValue(Attributes.ATTACK_SPEED) != 0) {
					if (living.getAttributes().getValue(Attributes.ATTACK_SPEED) / 10 < player.getAttributes().getValue(Attributes.ATTACK_SPEED) / 2) {
						stack.getOrCreateTag().putDouble(attack_speed, living.getAttributes().getValue(Attributes.ATTACK_SPEED) / 10);
					} else {
						stack.getOrCreateTag().putDouble(attack_speed, player.getAttributes().getValue(Attributes.ATTACK_SPEED) / 2);
					}
				}

				if (living.getAttributes().getInstance(Attributes.ARMOR) != null && player.getAttributes().getValue(Attributes.ARMOR) != 0) {
					if (living.getAttributes().getValue(Attributes.ARMOR) / 10 < player.getAttributes().getValue(Attributes.ARMOR) / 2) {
						stack.getOrCreateTag().putDouble(armor, living.getAttributes().getValue(Attributes.ARMOR) / 10);
					} else {
						stack.getOrCreateTag().putDouble(armor, player.getAttributes().getValue(Attributes.ARMOR) / 2);
					}
				}
			}
		}
		return a;
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
			public void curioTick(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().addTransientAttributeModifiers(the_pla(player, stack));

				}
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
			tooltip.add(Component.translatable("· 左键盗取该生物的属性(比例：1/10)").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 你必须拥有这些属性，才能成功盗取").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 佩戴后可获得相应的属性加成").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 目前：").withStyle(ChatFormatting.DARK_RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("·+" + stack.getOrCreateTag().getDouble(attack) + " 近战伤害").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·+" + stack.getOrCreateTag().getDouble(health) + " 最大生命").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·+" + stack.getOrCreateTag().getDouble(attack_speed) + " 攻击速度").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·+" + stack.getOrCreateTag().getDouble(armor) + " 护甲数值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 但这些属性不会超过你自身属性的最大值").withStyle(ChatFormatting.RED));
		} else {
			tooltip.add(Component.translatable("· Shift").withStyle(ChatFormatting.RED));

		}
	}

	public Multimap<Attribute, AttributeModifier> the_pla(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
		double double_attack = stack.getOrCreateTag().getDouble(attack);
		double double_health = stack.getOrCreateTag().getDouble(health);
		double double_attack_speed = stack.getOrCreateTag().getDouble(attack_speed);
		double double_armor = stack.getOrCreateTag().getDouble(armor);
		modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("f6dbd5e9-701c-4c76-8a48-0ba4b687c76d"), MoonstoneMod.MODID + "souaasdsdfasdadsl", double_health, AttributeModifier.Operation.ADDITION));
		modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("f6dbd5e9-701c-4c76-8a48-0ba4b687c76d"), MoonstoneMod.MODID + "souaasdsdfasdddsl", double_attack, AttributeModifier.Operation.ADDITION));
		modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("f6dbd5e9-701c-4c76-8a48-0ba4b687c76d"), MoonstoneMod.MODID + "souaasdsdfasdfdsl", double_attack_speed, AttributeModifier.Operation.ADDITION));
		modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("f6dbd5e9-701c-4c76-8a48-0ba4b687c76d"), MoonstoneMod.MODID + "souaasdsdfagsddsl", double_armor, AttributeModifier.Operation.ADDITION));
		return modifierMultimap;
	}
}
