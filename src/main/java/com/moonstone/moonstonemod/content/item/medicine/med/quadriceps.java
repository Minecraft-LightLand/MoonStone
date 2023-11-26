package com.moonstone.moonstonemod.content.item.medicine.med;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.medicine.extend.medicinebox;
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
import java.util.UUID;

public class quadriceps extends Item {
	public quadriceps() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("sdasdas", ChatFormatting.RED)));
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
			public void onEquip(SlotContext slotContext, ItemStack prevStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().addTransientAttributeModifiers(aaa(player, stack));
				}
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().removeAttributeModifiers(aaa(player, stack));
				}
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

	public Multimap<Attribute, AttributeModifier> aaa(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
		linkedHashMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("c13feccd-e582-4ae1-aee7-35e8acf7e9e1"), MoonstoneMod.MODID + "ec", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
		return linkedHashMultimap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("·使实验者消化其它部位以修复其腿部肌肉").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("·增加 20% 移动速度").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·增加 50% 整体跳跃高度").withStyle(ChatFormatting.RED));
		} else {
			tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
		}
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("·医药箱-跳跃次数：" + medicinebox.do_jump).withStyle(ChatFormatting.RED));
	}

}
