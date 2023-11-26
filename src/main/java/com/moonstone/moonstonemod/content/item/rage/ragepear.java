package com.moonstone.moonstonemod.content.item.rage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.Rage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.enchanting.EnchantmentLevelSetEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class ragepear extends Rage {
	public ragepear() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);

	}

	private void aaa(EnchantmentLevelSetEvent event) {
		List<Player> players = event.getLevel().getEntitiesOfClass(Player.class, new AABB(event.getPos().offset(-16, -16, -16), event.getPos().offset(16, 16, 16)));

		for (Player player : players) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setEnchantLevel(event.getEnchantLevel() + 10);
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

				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
				Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();

				CuriosApi.getCuriosHelper()
						.addSlotModifier(linkedHashMultimap, "charm", uuid, 1, AttributeModifier.Operation.ADDITION);


				return linkedHashMultimap;
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {
				}
			}

			@Override
			public boolean canEquip(SlotContext slotContext) {
				boolean aaa = false;
				if (slotContext.entity() instanceof Player player) {
					aaa = !CuriosHandler.hascurio(player, stack.getItem());
				}
				return aaa;

			}
		});
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable("附魔出的物品其附魔等级增加3级").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("所需附魔等级增加15级").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));

		} else {
			tooltip.add(Component.translatable("· Shift").withStyle(ChatFormatting.RED));
		}
	}
}
