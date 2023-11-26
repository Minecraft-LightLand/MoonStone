package com.moonstone.moonstonemod.content.item.misc;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
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

public class EndCharm extends Item {
	public EndCharm() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("blackhead", ChatFormatting.GOLD)));
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

		} else {
			tooltip.add(Component.translatable("-·[SHIFT]·-").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
			tooltip.add(Component.translatable("-·当你站在世界至高峰").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("-·万物臣服于你").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("-·但代价...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("-·是无尽的孤独与寂寞").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable(""));
		}
	}
}
