package com.moonstone.moonstonemod.content.item.laji;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Init;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class diemug extends Item {

	public diemug() {
		super(new Properties().stacksTo(1).rarity(Rarity.EPIC).food(
				new FoodProperties.Builder().alwaysEat().nutrition(0).saturationMod(0.0f).build()));
		MinecraftForge.EVENT_BUS.addListener(this::ccc);

	}

	private void ccc(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty() && !you.isEmpty()) {
			if (zuo.is(Init.evilmug.get()) && you.is(Init.ectoplasmprism.get())) {
				event.setCost(30);
				event.setMaterialCost(1);
				event.setOutput(Init.diemug.get().getDefaultInstance());
			}
		}

	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
		living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1));
		living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1));
		living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0));
		living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0));

		living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0));
		living.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1));


		return Init.diemug.get().getDefaultInstance();
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
				modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonstoneMod.MODID + "ec", 0.35, AttributeModifier.Operation.MULTIPLY_TOTAL));

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
		tooltip.add(Component.translatable("可怕的圣杯里装着恐怖的液体").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
		tooltip.add(Component.translatable("或许喝一口并不是坏事").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
	}
}


