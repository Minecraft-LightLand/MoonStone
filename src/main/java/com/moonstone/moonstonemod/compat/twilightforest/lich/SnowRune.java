package com.moonstone.moonstonemod.compat.twilightforest.lich;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.compat.twilightforest.base.TFBaseItem;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Init;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import twilightforest.init.TFDamageTypes;
import twilightforest.init.TFMobEffects;

import java.util.List;
import java.util.UUID;

public class SnowRune extends TFBaseItem {
	public SnowRune() {

		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::ccc);
	}

	private void ccc(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty() && !you.isEmpty()) {
			if (zuo.is(Init.blueamout.get()) && you.is(Init.lichhead.get())) {
				event.setCost(15);
				event.setMaterialCost(1);
				event.setOutput(this.getDefaultInstance());
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (ModList.get().isLoaded("twilightforest")) {
					if (event.getSource().is(TFDamageTypes.FROZEN) || event.getSource().is(DamageTypes.MAGIC)) {
						event.setAmount(event.getAmount() / 2);
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
					if (ModList.get().isLoaded("twilightforest")) {
						FrostWalkerEnchantment.onEntityMoved(player, player.level(), new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()), 3);
						player.getAttributes().addTransientAttributeModifiers(the_pla(player, stack));

						player.removeEffect(TFMobEffects.FROSTY.get());
						player.clearFire();
					}
				}
			}

			@Override
			public void onEquip(SlotContext slotContext, ItemStack prevStack) {

			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().removeAttributeModifiers(the_pla(player, stack));

				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public boolean canWalkOnPowderedSnow(SlotContext slotContext) {
				return true;
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
		if (ModList.get().isLoaded("twilightforest")) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 快速消除你身上的霜冻效果").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 并快速熄灭你身上的火焰").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 减少50%来自魔法和霜冻的伤害").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 轻功雪上飘").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 冰霜行者").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 每一级冰霜行者效果，增加15%的移动速度").withStyle(ChatFormatting.GOLD));

			} else {
				tooltip.add(Component.translatable("·按下 SHIFT 查看详情").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
			}
		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));
		}
	}

	public Multimap<Attribute, AttributeModifier> the_pla(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
		double aaa = EnchantmentHelper.getEnchantmentLevel(Enchantments.FROST_WALKER, player);
		modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
				UUID.fromString("9a5fa717-4d45-47f3-95bf-b3bb928f654e"),
				MoonstoneMod.MODID + "dnaisjnd",
				aaa / 10 / 2 * 3, AttributeModifier.Operation.MULTIPLY_TOTAL));
		return modifierMultimap;
	}
}
