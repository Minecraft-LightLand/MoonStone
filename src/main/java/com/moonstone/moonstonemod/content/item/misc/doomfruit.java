package com.moonstone.moonstonemod.content.item.misc;

import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

import java.util.List;
import java.util.UUID;

public class doomfruit extends Item {
	public doomfruit() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("adfdvf", ChatFormatting.RED)).food(
				new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(1.0f).build()));

		MinecraftForge.EVENT_BUS.addListener(this::ccc);
	}

	private void ccc(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty() && !you.isEmpty()) {
			if (zuo.is(Init.mbattery.get()) && you.is(Items.ENCHANTED_GOLDEN_APPLE)) {
				event.setCost(30);
				event.setMaterialCost(1);
				event.setOutput(new ItemStack(this));
			}
		}

	}

	@Override
	public int getUseDuration(ItemStack p_41454_) {
		return 32;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
		ItemStack stack = super.finishUsingItem(s, level, living);
		if (living instanceof Player player) {
			if (!player.getTags().contains("add_curio_moonstone_doomfruit")) {
				ICuriosHelper apiHelper = CuriosApi.getCuriosHelper();
				UUID uuid = UUID.fromString("0cf25168-1b5a-4ac9-90eb-45bb20b0afab");
				apiHelper.getCuriosHandler(player).ifPresent(handler -> handler.getStacksHandler("curio").ifPresent(stacks -> {
					if (!stacks.getModifiers().containsKey(uuid)) {
						stacks.addPermanentModifier(new AttributeModifier(uuid, "doom_fruit_slot", 1, AttributeModifier.Operation.ADDITION));
					}
				}));

				player.addTag("add_curio_moonstone_doomfruit");
			}

			player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 6000, 2));


		}
		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("使食用后以永久+1").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("通用饰品槽").withStyle(ChatFormatting.RED));
	}

}

