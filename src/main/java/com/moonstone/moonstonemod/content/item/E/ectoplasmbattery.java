package com.moonstone.moonstonemod.content.item.E;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.EpicItem;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;

public class ectoplasmbattery extends EpicItem {
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
				modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", 10, AttributeModifier.Operation.ADDITION));
				modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", 4, AttributeModifier.Operation.ADDITION));
				modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
				modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
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
}