package com.moonstone.moonstonemod.compat.cataclysm;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Reg;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class timegold extends Item {

	public timegold() {
		super(new Properties().stacksTo(1).defaultDurability(7200).rarity(Rarity.create("asdsa", ChatFormatting.GOLD)));
		MinecraftForge.EVENT_BUS.addListener(this::aaa);

	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (!player.getCooldowns().isOnCooldown(this)) {
				if (ModList.get().isLoaded("cataclysm")) {
					if (CuriosHandler.hascurio(player, this)) {
						int s = Mth.nextInt(RandomSource.create(), 1, 10);
						if (s == 1) {
							Vec3 playerPos = player.position().add(0, 0.75, 0);
							int range = 16;
							List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
							for (LivingEntity living : entities) {
								if (!living.is(player)) {
									living.hurt(living.damageSources().playerAttack(player), living.getHealth() / 2);
									living.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 9));
									player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BEACON_DEACTIVATE, SoundSource.NEUTRAL, 1.5f, 1.5f);
									if (player.level() instanceof ServerLevel serverLevel) {
										serverLevel.sendParticles(Reg.TIME.get(), player.getX(), player.getY(), player.getZ(), 20, 10, 10, 10, 0);
									}

									player.getCooldowns().addCooldown(this, 1200);
								}
							}
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
		if (ModList.get().isLoaded("cataclysm")) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("受到伤害时有10%的概率触发一次 时停爆发").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("位于时停爆发中心点16格内的所有生物").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("将受到10秒的眩晕和50%当前生命值的伤害").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("1分钟冷却").withStyle(ChatFormatting.GOLD));
			} else {
				tooltip.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.GOLD));
			}
		} else {
			tooltip.add(Component.translatable("请安装 灾变 模组来使用它").withStyle(ChatFormatting.RED));
		}
	}
}

