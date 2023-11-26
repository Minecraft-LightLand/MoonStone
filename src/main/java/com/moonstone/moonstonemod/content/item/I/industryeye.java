package com.moonstone.moonstonemod.content.item.I;

import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.Industry;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.Collection;
import java.util.List;

public class industryeye extends Industry {
	public industryeye() {


	}

	private void aaaa(LivingEvent.LivingTickEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (!player.level().isClientSide) {
					if (!player.getCooldowns().isOnCooldown(this)) {
						Vec3 playerPos = player.position().add(0, 0.75, 0);
						int range = 10;
						List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
						for (LivingEntity living : entities) {
							if (living != null && !living.is(player) && !(living instanceof Player)) {
								Collection<MobEffectInstance> mobEffectInstance = living.getActiveEffects();
								if (!mobEffectInstance.isEmpty()) {

									for (MobEffectInstance effectInstance : mobEffectInstance) {

										MobEffect mobEffect = effectInstance.getEffect();
										if (mobEffect.isBeneficial()) {
											living.removeEffect(mobEffect);
											player.addEffect(new MobEffectInstance(mobEffect, effectInstance.getDuration(), effectInstance.getAmplifier()));
										}
									}

								}
							}
						}
					}
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
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("这个物品可能有导致存档损坏的BUG").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable(""));
		//  tooltip.add(Component.translatable("将附近生物的正面药水效果偷盗到你身上").withStyle(ChatFormatting.GOLD));
		tooltip.add(Component.translatable(""));
	}
}
