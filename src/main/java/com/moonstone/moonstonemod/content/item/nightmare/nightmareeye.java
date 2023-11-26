package com.moonstone.moonstonemod.content.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.Nightmare;
import com.moonstone.moonstonemod.init.Init;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class nightmareeye extends Nightmare {
	public nightmareeye() {
		MinecraftForge.EVENT_BUS.addListener(this::ccc);

		MinecraftForge.EVENT_BUS.addListener(this::att);        //enderDragon - nightmareorb
		MinecraftForge.EVENT_BUS.addListener(this::attack);
	}

	private void att(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setAmount(event.getAmount() * 1.33f);
			}
		}
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setAmount(event.getAmount() * 0.75f);
			}
		}

	}

	private void attack(LivingDeathEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
				Map<String, ICurioStacksHandler> curios = handler.getCurios();
				for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
					ICurioStacksHandler stacksHandler = entry.getValue();
					IDynamicStackHandler stackHandler = stacksHandler.getStacks();
					for (int i = 0; i < stacksHandler.getSlots(); i++) {
						ItemStack stack = stackHandler.getStackInSlot(i);
						if (stack.is(this)) {
							if (event.getEntity() instanceof EnderDragon enderDragon) {
								if (player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
									player.addItem(new ItemStack(Init.nightmareorb.get()));
								}
							}
							if (event.getEntity() instanceof WitherBoss witherBoss) {
								if (player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
									player.addItem(new ItemStack(Init.nightmarestone.get()));
								}
							}
						}
					}
				}
			});
		}
	}

	private void ccc(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty() && !you.isEmpty()) {
			if (zuo.is(Items.ENDER_EYE) && you.is(Init.ectoplasmprism.get())) {
				event.setCost(25);
				event.setMaterialCost(1);
				event.setOutput(Init.nightmareeye.get().getDefaultInstance());
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
					player.getAttributes().addTransientAttributeModifiers(un_un_pla(player));
				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public boolean canUnequip(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					return player.isCreative();
				}
				return false;
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

	public Multimap<Attribute, AttributeModifier> un_un_pla(Player player) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
		UUID uuid = UUID.fromString("ac159e63-a757-4f7d-8c2c-73469a715ff3");

		if (!CuriosHandler.hascurio(player, Init.nightmarecharm.get())) {
			modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
		} else {
			modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.10, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.10, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.10, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.10, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.10, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.10, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.10, AttributeModifier.Operation.MULTIPLY_TOTAL));
			modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", -0.10, AttributeModifier.Operation.MULTIPLY_TOTAL));

		}

		return modifierMultimap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("·佩戴它，感受深渊真正的魅力......").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable("·克服远古病毒的诅咒").withStyle(ChatFormatting.DARK_RED));

		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("·受到的伤害增加33%").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("·攻击伤害降低25%").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("·一旦带上，它将与你融为一体").withStyle(ChatFormatting.DARK_RED));


	}
}
