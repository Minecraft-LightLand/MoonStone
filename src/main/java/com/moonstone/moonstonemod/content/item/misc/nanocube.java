package com.moonstone.moonstonemod.content.item.misc;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class nanocube extends Item {
	public nanocube() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("nanocube", ChatFormatting.GOLD)));

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
					if (!player.level().isClientSide && player.tickCount % 20 == 0) {
						ItemStack HEAD = player.getItemBySlot(EquipmentSlot.HEAD);
						if (!HEAD.isEmpty()) {
							if (HEAD.getMaxDamage() != 0) {
								HEAD.setDamageValue(HEAD.getDamageValue() - 1);
							}
						}
						ItemStack CHEST = player.getItemBySlot(EquipmentSlot.CHEST);
						if (!CHEST.isEmpty()) {
							if (CHEST.getMaxDamage() != 0) {
								CHEST.setDamageValue(CHEST.getDamageValue() - 1);
							}
						}
						ItemStack LEGS = player.getItemBySlot(EquipmentSlot.LEGS);
						if (!LEGS.isEmpty()) {
							if (LEGS.getMaxDamage() != 0) {
								LEGS.setDamageValue(LEGS.getDamageValue() - 1);
							}
						}
						ItemStack FEET = player.getItemBySlot(EquipmentSlot.FEET);
						if (!FEET.isEmpty()) {
							if (FEET.getMaxDamage() != 0) {
								FEET.setDamageValue(FEET.getDamageValue() - 1);
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
		tooltip.add(Component.translatable("· 修复身着盔甲").withStyle(ChatFormatting.GOLD));

	}


}
