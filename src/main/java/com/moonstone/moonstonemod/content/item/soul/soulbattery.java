package com.moonstone.moonstonemod.content.item.soul;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.EpicItem;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class soulbattery extends EpicItem {
	public soulbattery() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void aaa(CriticalHitEvent event) {
		if (CuriosHandler.hascurio(event.getEntity(), this)) {
			event.setDamageModifier(event.getDamageModifier() * 1.25f);
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
			public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
				Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
				modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", 0.02, AttributeModifier.Operation.MULTIPLY_TOTAL));
				modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
				modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
				return modifierMultimap;
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
		tooltip.add(Component.translatable("·+25% 暴击伤害").withStyle(ChatFormatting.GOLD));

	}
}

