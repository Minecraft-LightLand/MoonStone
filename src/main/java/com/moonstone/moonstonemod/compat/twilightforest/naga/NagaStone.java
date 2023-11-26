package com.moonstone.moonstonemod.compat.twilightforest.naga;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import twilightforest.entity.boss.Naga;
import twilightforest.init.TFSounds;

import java.util.List;

public class NagaStone extends Item {
	public NagaStone() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("mblock", ChatFormatting.DARK_GREEN)));
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::kkk);
	}

	private void kkk(LivingKnockBackEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.setStrength(event.getStrength() * 0);
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (ModList.get().isLoaded("twilightforest")) {
			if (event.getEntity() instanceof Player player) {
				if (CuriosHandler.hascurio(player, this)) {
					if (event.getSource().getEntity() instanceof Naga naga) {
						if (naga.isCharging()) {
							naga.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4));
							naga.setDazed(true);
							player.level().playSound(null, player.getX(), player.getY(), player.getZ(), TFSounds.NAGA_HURT.get(), SoundSource.NEUTRAL, 1F, 1F);
						}
					}
				}
			}

			if (event.getSource().getEntity() instanceof Player player) {
				if (CuriosHandler.hascurio(player, this)) {
					if (event.getEntity() instanceof Naga naga) {
						if (naga.isDazed()) {
							event.setAmount(event.getAmount() * 1.5f);
						}
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
			public void curioTick(SlotContext slotContext) {


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
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("§2· 任何东西都无法击退你"));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("§2· 娜迦撞到你就像撞到石头上一样"));
			tooltip.add(Component.translatable("§2· 随后娜迦会进入眩晕状态"));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("§2· 你对眩晕的娜迦造成的伤害提升50%"));

		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));

		}
	}
}
