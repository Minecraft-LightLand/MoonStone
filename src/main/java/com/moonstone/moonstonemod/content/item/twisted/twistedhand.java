package com.moonstone.moonstonemod.content.item.twisted;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.EpicItem;
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
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class twistedhand extends EpicItem {
	public twistedhand() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::bbb);
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				double aaa = (100 - (player.getHealth() / player.getMaxHealth() * 100)) / 100;
				double Do = aaa / 3;
				event.setAmount((float) (event.getAmount() * (1 + Do)));
			}
		}
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				double aaa = (100 - (player.getHealth() / player.getMaxHealth() * 100)) / 100;
				double Do = aaa / 10;
				event.setAmount((float) (event.getAmount() * (1 + Do)));
			}
		}
	}

	private void bbb(LivingHealEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				double aaa = (100 - (player.getHealth() / player.getMaxHealth() * 100)) / 100;
				double Do = aaa / 2;
				event.setAmount((float) (event.getAmount() * (1 + Do)));
			}
		}
	}

	private Multimap<Attribute, AttributeModifier> iii(Player player) {
		double aaa = (100 - (player.getHealth() / player.getMaxHealth() * 100)) / 100;
		double Do = aaa;
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
		modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(player.getUUID(), "asjdnajdsadnansijdn", Do, AttributeModifier.Operation.MULTIPLY_TOTAL));
		return modifierMultimap;
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
				if (slotContext.entity() instanceof Player pl) {
					pl.getAttributes().addTransientAttributeModifiers(iii(pl));
				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player pl) {
					pl.getAttributes().removeAttributeModifiers(iii(pl));
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

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("·每损失1%的生命值都会：").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable("· +0.1% 伤害抗性").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("· +0.33% 攻击伤害").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("· +0.5% 生命恢复速度").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("· +1.0% 移动速度").withStyle(ChatFormatting.RED));
	}

}
