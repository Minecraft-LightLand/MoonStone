package com.moonstone.moonstonemod.content.item.N;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.Nightmare;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class nightmarecharm extends Nightmare {

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
				Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
				CuriosApi.getCuriosHelper()
						.addSlotModifier(linkedHashMultimap, "charm", uuid, 1, AttributeModifier.Operation.ADDITION);
				return linkedHashMultimap;
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
			tooltip.add(Component.translatable("·减少15%来自深渊之眼的诅咒").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·加法计数").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("·+1 饰品槽位").withStyle(ChatFormatting.RED));

		} else {
			tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
		}
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("· 唯有承受深渊之恐的人").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable("· 才有资格使用此物品").withStyle(ChatFormatting.DARK_RED));
	}
}
