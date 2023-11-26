package com.moonstone.moonstonemod.content.item.N;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.Nightmare;
import com.moonstone.moonstonemod.init.Init;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class nightmarestone extends Nightmare {

	public nightmarestone() {
		MinecraftForge.EVENT_BUS.addListener(this::KnockBack);
		MinecraftForge.EVENT_BUS.addListener(this::Heal);
		MinecraftForge.EVENT_BUS.addListener(this::BreakSpeed);
		MinecraftForge.EVENT_BUS.addListener(this::exp);
	}

	private void BreakSpeed(PlayerEvent.BreakSpeed event) {
		if (event.getEntity() != null) {
			Player player = event.getEntity();
			if (CuriosHandler.hascurio(player, this)) {
				event.setNewSpeed(event.getNewSpeed() * (1 + acc(player) / 10));
			}
		}
	}

	private void exp(LivingExperienceDropEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setDroppedExperience((int) (event.getDroppedExperience() * (1 + acc(player) / 10)));
			}
		}
	}

	private void KnockBack(LivingKnockBackEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setStrength(event.getStrength() * (1 - acc(player) / 14));
			}
		}
	}

	private void Heal(LivingHealEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setAmount(event.getAmount() * (1 + acc(player) / 14));
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
					aDouble = acc(player);
					if (!player.level().isClientSide && player.tickCount % 20 == 0) {
						player.getAttributes().addTransientAttributeModifiers(un_un_pla(player));
						Collection<MobEffectInstance> collection = player.getActiveEffects();
						for (MobEffectInstance mobEffectInstance : collection) {
							MobEffect mobEffect = mobEffectInstance.getEffect();
							if (!mobEffect.isBeneficial()) {
								int lvl = mobEffectInstance.getAmplifier();
								int time = mobEffectInstance.getDuration();
								MobEffect effect = mobEffectInstance.getEffect();

								player.addEffect(new MobEffectInstance(effect, time + 10, lvl));

							}
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
					player.getAttributes().removeAttributeModifiers(un_un_pla(player));
				}
			}

			@Override
			public boolean canEquip(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					if (!CuriosHandler.hascurio(player, Init.nightmareeye.get())) {
						return false;
					}
					return !CuriosHandler.hascurio(player, stack.getItem());
				}

				return true;

			}
		});
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable(""));

		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable(" -你的身上每存在一个负面Buff：").withStyle(ChatFormatting.DARK_RED));
			tooltip.add(Component.translatable("· +4% 伤害，移速，护甲").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +7% 生命回复，抗击退").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +10% 挖掘速度，经验掉落").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable(" -已获得的属性加成：").withStyle(ChatFormatting.DARK_RED));
			tooltip.add(Component.translatable("· +" + (int) (aDouble / 25 * 100) + "% 伤害，移速，护甲").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +" + (int) (aDouble / 14 * 100) + "% 生命回复，抗击退").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· +" + (int) (aDouble / 10 * 100) + "% 挖掘速度，经验掉落").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 你身上的任何负面效果时长翻倍").withStyle(ChatFormatting.RED));
		} else {
			tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
		}
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("· 唯有承受深渊之恐的人").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable("· 才有资格使用此物品").withStyle(ChatFormatting.DARK_RED));
	}

	//c98b3962-3ea9-47f8-820d-134ce2691af0
	public Multimap<Attribute, AttributeModifier> un_un_pla(Player player) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();


		modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("c98b3962-3ea9-47f8-820d-134ce2691af0"), MoonstoneMod.MODID + "sojjmjmul", acc(player) / 25, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("c98b3962-3ea9-47f8-820d-134ce2691af0"), MoonstoneMod.MODID + "soudfsdl", acc(player) / 25, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("c98b3962-3ea9-47f8-820d-134ce2691af0"), MoonstoneMod.MODID + "sojjmasdadjmul", acc(player) / 25, AttributeModifier.Operation.MULTIPLY_TOTAL));

		return modifierMultimap;
	}

	public static double aDouble;

	private float acc(Player player) {
		float size = 0;
		List<Integer> Int = Lists.newArrayList();
		Collection<MobEffectInstance> collection = player.getActiveEffects();
		for (MobEffectInstance mobEffectInstance : collection) {
			MobEffect mobEffect = mobEffectInstance.getEffect();
			if (!mobEffect.isBeneficial()) {
				Int.add(1);
			}
		}
		for (Integer i : Int) {
			size += 1;
		}
		return size;
	}
}
