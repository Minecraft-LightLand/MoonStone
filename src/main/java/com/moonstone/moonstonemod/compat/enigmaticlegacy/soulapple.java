package com.moonstone.moonstonemod.compat.enigmaticlegacy;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

import java.util.List;
import java.util.UUID;

public class soulapple extends Item {
	public soulapple() {
		super(new Properties().stacksTo(1).rarity(Rarity.EPIC).food(
				new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(1.0f).build()));
	}

	@Override
	public int getUseDuration(ItemStack p_41454_) {
		return 32;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
		ItemStack stack = super.finishUsingItem(s, level, living);
		if (living instanceof Player player) {
			if (!player.getTags().contains("add_curio_moonstone")) {
				ICuriosHelper apiHelper = CuriosApi.getCuriosHelper();
				UUID uuid = UUID.fromString("00000000-0000-3004-998f-501a96c8aa2a");
				apiHelper.getCuriosHandler(player).ifPresent(handler -> handler.getStacksHandler("curio").ifPresent(stacks -> {
					if (!stacks.getModifiers().containsKey(uuid)) {
						stacks.addPermanentModifier(new AttributeModifier(uuid, "moonstone_slot", 1, AttributeModifier.Operation.ADDITION));
					}
				}));

				player.addTag("add_curio_moonstone");
			}

			player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 6000, 4));
			player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 6000, 1));
			player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 1));
			player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 2));
			player.addEffect(new MobEffectInstance(MobEffects.JUMP, 6000, 2));


		}
		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("§5-食用后: §d+1 通用饰品槽"));
	}

	@Override
	public boolean isFoil(ItemStack p_41453_) {
		return true;
	}
}
