package com.moonstone.moonstonemod.compat.twilightforest.dna;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import twilightforest.init.TFDimensionSettings;

import java.util.List;
import java.util.UUID;

public class Sensitivity extends Item {
	public Sensitivity() {
		super(new Properties().stacksTo(1).defaultDurability(7200).rarity(Rarity.RARE));

		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (ModList.get().isLoaded("twilightforest")) {
					if (player.level().dimensionTypeId() == TFDimensionSettings.TWILIGHT_DIM_TYPE) {
						event.setAmount(event.getAmount() * 0.8f);
					}
				}
			}
		}
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (ModList.get().isLoaded("twilightforest")) {
					if (player.level().dimensionTypeId() == TFDimensionSettings.TWILIGHT_DIM_TYPE) {
						event.setAmount(event.getAmount() * 0.9f);
					}
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

			@Override
			public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
				Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();

				CuriosApi.getCuriosHelper()
						.addSlotModifier(linkedHashMultimap, "charm", uuid, 1, AttributeModifier.Operation.ADDITION);


				return linkedHashMultimap;
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
		if (ModList.get().isLoaded("twilightforest")) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("+20% 在暮色森林的伤害抗性").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("-10% 在暮色森林的伤害").withStyle(ChatFormatting.GOLD));

			} else {
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("·按下 SHIFT 查看详情").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
			}
		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));
		}
	}
}


