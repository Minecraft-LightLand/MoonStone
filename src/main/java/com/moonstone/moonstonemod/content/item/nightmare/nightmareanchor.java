package com.moonstone.moonstonemod.content.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.Nightmare;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class nightmareanchor extends Nightmare {

	public nightmareanchor() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void aaa(LivingDeathEvent event) {
		if (event.getEntity() instanceof Player player) {
			CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
				Map<String, ICurioStacksHandler> curios = handler.getCurios();
				for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
					ICurioStacksHandler stacksHandler = entry.getValue();
					IDynamicStackHandler stackHandler = stacksHandler.getStacks();
					for (int i = 0; i < stacksHandler.getSlots(); i++) {
						ItemStack stack = stackHandler.getStackInSlot(i);
						if (stack.is(this)) {
							if (CuriosHandler.hascurio(player, this)) {

								double playerX = player.getX();
								double playerY = player.getY();
								double playerZ = player.getZ();

								stack.getOrCreateTag().putDouble("x", playerX);
								stack.getOrCreateTag().putDouble("y", playerY);
								stack.getOrCreateTag().putDouble("z", playerZ);


								stack.getOrCreateTag().putString("level", player.level().dimension().toString());
							}
						}
					}
				}
			});

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
			public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
				Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
				CuriosApi.getCuriosHelper()
						.addSlotModifier(linkedHashMultimap, "charm", uuid, 1, AttributeModifier.Operation.ADDITION);
				return linkedHashMultimap;
			}

			@Override
			public boolean canEquip(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					if (!CuriosHandler.hascurio(player, Init.nightmareeye.get())) {
						return false;
					}
					return !CuriosHandler.hascurio(player, stack.getItem());
				}

				return true;

			}
		});
	}

	@Override
	public void releaseUsing(ItemStack stack, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
		int i = this.getUseDuration(stack) - p_40670_;

		if (p_40669_ instanceof Player player) {
			float f = BowItem.getPowerForTime(i);
			if (f == 1.0f) {
				if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
					if (player.level().dimension().toString().contains(stack.getOrCreateTag().getString("level"))) {
						if (stack.getOrCreateTag().getDouble("x") != 0
								&& stack.getOrCreateTag().getDouble("y") != 0
								&& stack.getOrCreateTag().getDouble("z") != 0) {
							player.teleportTo(stack.getOrCreateTag().getDouble("x"),
									stack.getOrCreateTag().getDouble("y"),
									stack.getOrCreateTag().getDouble("z"));
							player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.NEUTRAL, 1F, 1F);
							player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 2));
							player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 2));
							player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 2));
							player.getCooldowns().addCooldown(stack.getItem(), 300);
						}
					}
				}
			}
		}
	}

	@Override
	public int getUseDuration(ItemStack p_40680_) {
		return 72000;
	}

	public UseAnim getUseAnimation(ItemStack p_40678_) {
		return UseAnim.BOW;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
		InteractionResultHolder<ItemStack> stackInteractionResultHolder = super.use(level, player, p_41434_);

		player.startUsingItem(p_41434_);
		return stackInteractionResultHolder;
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable(""));
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable("· 佩戴它并死亡").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 可记录这一次的死亡地点").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 右键传送到上记录点").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 但必须在同一维度").withStyle(ChatFormatting.RED));

		} else {
			tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
		}
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("· 唯有承受深渊之恐的人").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable("· 才有资格使用此物品").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("·X :" + (int) stack.getOrCreateTag().getDouble("x")).withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("·Y :" + (int) stack.getOrCreateTag().getDouble("y")).withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("·Z :" + (int) stack.getOrCreateTag().getDouble("z")).withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable("·Level :" + stack.getOrCreateTag().getString("level")).withStyle(ChatFormatting.RED));
	}
}
