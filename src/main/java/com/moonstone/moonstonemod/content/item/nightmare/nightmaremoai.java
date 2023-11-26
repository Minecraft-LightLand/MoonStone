package com.moonstone.moonstonemod.content.item.nightmare;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.Nightmare;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class nightmaremoai extends Nightmare {
	public nightmaremoai() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::bbb);
	}

	public static int time;

	private void bbb(LivingEvent.LivingTickEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				EvokerFangs fangs = new EvokerFangs(EntityType.EVOKER_FANGS, player.level());
				EvokerFangs fangs1 = new EvokerFangs(EntityType.EVOKER_FANGS, player.level());
				if (time > 0) {
					double x = Mth.nextDouble(RandomSource.create(), -5, 5);
					double z = Mth.nextDouble(RandomSource.create(), -5.1, 5.2);
					double y = Mth.nextDouble(RandomSource.create(), -1, 1);
					double x1 = Mth.nextDouble(RandomSource.create(), -5.11, 5.11);
					double z1 = Mth.nextDouble(RandomSource.create(), -5.11, 5.21);
					double y1 = Mth.nextDouble(RandomSource.create(), -1, 1);
					fangs1.moveTo(player.getX() + x1, player.getY() + y1, player.getZ() + z1);
					fangs.moveTo(player.getX() + x, player.getY() + y, player.getZ() + z);

					fangs.setOwner(player);
					fangs1.setOwner(player);


					player.level().addFreshEntity(fangs);
					player.level().addFreshEntity(fangs1);
					if (player.level() instanceof ServerLevel serverLevel) {
						serverLevel.sendParticles(ParticleTypes.SOUL, player.getX(), player.getY(), player.getZ(), 8, 5.0D, 5.0D, 5.0D, 1.0D);
						serverLevel.sendParticles(ParticleTypes.SCULK_SOUL, player.getX(), player.getY(), player.getZ(), 8, 5.0D, 5.0D, 5.0D, 1.0D);

					}
					time -= 1;
				}
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (!player.getCooldowns().isOnCooldown(this)) {
					if (Mth.nextInt(RandomSource.create(), 1, 4) == 1) {
						time = 100;
						player.getCooldowns().addCooldown(this, 200);
						Vec3 playerPos = player.position().add(0, 0.75, 0);
						int range = 15;
						List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
						for (LivingEntity living : entities) {
							if (!living.is(player)) {

								living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
								player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2));
								player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));

							}
						}

					}
				}

				if (!player.getCooldowns().isOnCooldown(this)) {
					if (event.getAmount() >= player.getHealth()) {
						event.setAmount(0);
						player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 4));
						player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 2));
						player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
						player.getCooldowns().addCooldown(this, 6000);
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
				if (slotContext.entity() instanceof Player player) {

				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {

				}
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
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable(""));
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable("· 受到伤害后有25%的概率释放一段时间的尖牙攻击").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 在尖牙攻击释放期间移速和伤害抗性得以提升").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 但会使此物品陷入10秒的冷却").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 当此物品不在冷却期间时").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("· 受到致命伤害后将复活，但会使此物品进入更长时间的冷却").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
		} else {
			tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
		}
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("· 唯有承受深渊之恐的人").withStyle(ChatFormatting.DARK_RED));
		tooltip.add(Component.translatable("· 才有资格使用此物品").withStyle(ChatFormatting.DARK_RED));
	}
}
