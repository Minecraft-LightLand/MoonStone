package com.moonstone.moonstonemod.content.item.G;

import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingUseTotemEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class undead extends Item {
	public undead() {
		super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::bbb);
	}

	private void bbb(LivingUseTotemEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				player.setAbsorptionAmount(player.getAbsorptionAmount() + player.getMaxHealth() / 2);
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (!player.getCooldowns().isOnCooldown(this)) {
					if (event.getAmount() > player.getHealth()) {
						player.heal(player.getMaxHealth() / 2);
						player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 4));
						player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 600, 1));
						player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 2));
						player.addEffect(new MobEffectInstance(MobEffects.JUMP, 600, 2));
						player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1));
						player.giveExperienceLevels(-(player.experienceLevel / 2));
						player.getCooldowns().addCooldown(this, 2400);
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
		tooltip.add(Component.translatable("· 当你接近死亡时此物品会向你的体内注入新的灵魂").withStyle(ChatFormatting.GOLD));
		tooltip.add(Component.translatable("· 那些灵魂会治愈你一切所受到的伤痕，但代价是消耗你50%的经验值").withStyle(ChatFormatting.GOLD));
		tooltip.add(Component.translatable("· 同时此物品会进入冷却阶段").withStyle(ChatFormatting.GOLD));
		tooltip.add(Component.translatable("· 当你使用不死图腾时，会加强不死图腾的效果").withStyle(ChatFormatting.GOLD));
	}

}
