package com.moonstone.moonstonemod.content.item.medicine.extend;

import net.minecraft.ChatFormatting;
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

public class apple extends Item {
	public apple() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("asda", ChatFormatting.RED)).food(
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
			if (!player.getTags().contains("add_nec_moonstone")) {
				ICuriosHelper apiHelper = CuriosApi.getCuriosHelper();
				UUID uuid = UUID.fromString("00000000-0000-300f-95e1-2830b5159532");
				apiHelper.getCuriosHandler(player).ifPresent(handler -> handler.getStacksHandler("necora").ifPresent(stacks -> {
					if (!stacks.getModifiers().containsKey(uuid)) {
						stacks.addPermanentModifier(new AttributeModifier(uuid, "acc_mid_asd", 3, AttributeModifier.Operation.ADDITION));
					}
				}));

				player.addTag("add_nec_moonstone");
			}

			player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 2000, 2));
		}
		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("享用我......").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
	}

}

