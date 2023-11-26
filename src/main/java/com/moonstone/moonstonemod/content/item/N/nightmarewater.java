package com.moonstone.moonstonemod.content.item.N;

import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;

import java.util.List;

public class nightmarewater extends Item {

	public nightmarewater() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("ad", ChatFormatting.RED)).food(
				new FoodProperties.Builder().alwaysEat().nutrition(0).saturationMod(0.0f).build()));
		MinecraftForge.EVENT_BUS.addListener(this::ccc);

	}

	private void ccc(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty() && !you.isEmpty()) {
			if (zuo.is(Init.diemug.get()) && you.is(Init.ectoplasmprism.get())) {
				event.setCost(30);
				event.setMaterialCost(1);
				event.setOutput(new ItemStack(this));
			}
		}

	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
		living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 0));
		living.addEffect(new MobEffectInstance(MobEffects.POISON, 300, 0));
		living.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 300, 0));
		living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300, 0));
		living.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 2));

		if (CuriosHandler.hascurio(living, Init.nightmareeye.get())) {
			living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 2));
			living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1));
			living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 2));
			living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1));
			living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0));
			living.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2));
			living.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0));
			living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1));
		}


		return this.getDefaultInstance();
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("腐朽，虚弱......").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
		tooltip.add(Component.translatable("深渊，永恒......").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("· 唯有承受深渊之恐的人").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable("· 才有资格使用此物品").withStyle(ChatFormatting.DARK_RED));


	}
}



