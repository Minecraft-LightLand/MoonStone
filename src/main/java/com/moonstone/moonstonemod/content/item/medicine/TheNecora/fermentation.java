package com.moonstone.moonstonemod.content.item.medicine.TheNecora;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.medicine.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class fermentation extends TheNecoraIC {
	public fermentation() {

		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {


				if (player.getCooldowns().isOnCooldown(this)) {
					event.setAmount(event.getAmount() * 0.3f);
				} else {
					event.setAmount(event.getAmount() * 4);
					player.getCooldowns().addCooldown(this, 200);
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
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 每次攻击都会使此基因片段进入10秒失活状态").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 且在失活状态时伤害降低70%").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 但失活状态结束后的第一击，伤害提升300%").withStyle(ChatFormatting.RED));
		} else {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-[SHIFT]").withStyle(ChatFormatting.DARK_RED));
			tooltip.add(Component.translatable("保持活性状态的基因促使僵尸待生物靠近时会予以致命一击").withStyle(ChatFormatting.RED));
		}
	}
}



