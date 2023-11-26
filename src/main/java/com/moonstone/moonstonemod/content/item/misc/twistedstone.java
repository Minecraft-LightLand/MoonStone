package com.moonstone.moonstonemod.content.item.misc;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
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

public class twistedstone extends EpicItem {

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
					player.getAttributes().addTransientAttributeModifiers(aaa(player, stack));
					final int bbb = Mth.nextInt(RandomSource.create(), -10, 20);
					CompoundTag tag = stack.getOrCreateTag();
					CompoundTag compoundTag = new CompoundTag();
					stack.addTagElement("ttw", compoundTag);
					if (stack.hasTag()) {
						if (stack.getOrCreateTag().getInt("double_stone_moonstone_tw") == 0) {
							tag.putInt("double_stone_moonstone_tw", bbb);
						}
					}
				}

			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().removeAttributeModifiers(aaa(player, stack));
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
		if (!stack.isEmpty()) {
			if (stack.hasTag()) {
				tooltip.add(Component.translatable("· 伤害加成：" + stack.getOrCreateTag().getInt("double_stone_moonstone_tw") + "%").withStyle(ChatFormatting.GOLD));
			} else {
				tooltip.add(Component.translatable("· 不要被欲望击穿你的意志").withStyle(ChatFormatting.GRAY));

			}
		}
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable("· 这是由扭曲元素所打造的神秘护身符").withStyle(ChatFormatting.GRAY));
			tooltip.add(Component.translatable("· 它可以强化你的灵魂，也可以削弱你灵魂").withStyle(ChatFormatting.GRAY));
			tooltip.add(Component.translatable("· 只有最幸运的人才可以驾驭于这块护身符之上").withStyle(ChatFormatting.GRAY));
		} else {
			tooltip.add(Component.translatable("按下“SHIFT”查看").withStyle(ChatFormatting.GRAY));

		}
	}

	public Multimap<Attribute, AttributeModifier> aaa(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
		modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(player.getUUID(), MoonstoneMod.MODID + "tws", (double) stack.getOrCreateTag().getInt("double_stone_moonstone_tw") / 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		return modifierMultimap;
	}
}

