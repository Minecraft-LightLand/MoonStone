package com.moonstone.moonstonemod.compat.twilightforest.base;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class GammaEye extends Item {
	public GammaEye() {
		super(new Properties().stacksTo(1).defaultDurability(7200).rarity(Rarity.create("asdsa", ChatFormatting.GOLD)));

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
					Entity entity = getPlayerLookTarget(player.level(), player);
					if (entity instanceof LivingEntity living) {

						if (!player.level().isClientSide && player.tickCount % 3 == 0) {
							AreaEffectCloud cloud = new AreaEffectCloud(EntityType.AREA_EFFECT_CLOUD, player.level());
							cloud.setDuration(100);
							cloud.setRadius(1.5f);
							cloud.setWaitTime(1);
							cloud.setParticle(ParticleTypes.SNEEZE);

							living.addEffect(new MobEffectInstance(MobEffects.POISON, 30, 2));
							living.addEffect(new MobEffectInstance(MobEffects.WITHER, 30, 2));
							living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30, 0));
							living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 2));
							living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 0));

							cloud.setRadiusPerTick(-cloud.getRadius() / (float) cloud.getDuration());

							cloud.setPos(new Vec3(living.getX(), living.getY() + Mth.nextDouble(RandomSource.create(), -1, 1), living.getZ()));
							cloud.setOwner(player);
							living.level().addFreshEntity(cloud);

						}
					}
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

	private @Nullable Entity getPlayerLookTarget(Level level, LivingEntity living) {
		Entity pointedEntity = null;
		double range = 18.0;
		Vec3 srcVec = living.getEyePosition();
		Vec3 lookVec = living.getViewVector(1.0F);
		Vec3 destVec = srcVec.add(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range);
		float var9 = 1.0F;
		List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range).inflate(var9, var9, var9));
		double hitDist = 0.0;
		Iterator<Entity> var13 = possibleList.iterator();

		while (true) {
			Entity possibleEntity;
			do {
				while (true) {
					do {
						if (!var13.hasNext()) {
							return pointedEntity;
						}

						possibleEntity = var13.next();
					} while (!possibleEntity.isPickable());

					float borderSize = possibleEntity.getPickRadius();
					AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
					Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);
					if (collisionBB.contains(srcVec)) {
						break;
					}

					if (interceptPos.isPresent()) {
						double possibleDist = srcVec.distanceTo(interceptPos.get());
						if (possibleDist < hitDist || hitDist == 0.0) {
							pointedEntity = possibleEntity;
							hitDist = possibleDist;
						}
					}
				}
			} while (!(0.0 < hitDist) && hitDist != 0.0);

			pointedEntity = possibleEntity;
			hitDist = 0.0;
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		if (ModList.get().isLoaded("twilightforest")) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 你所注视的生物将慢慢腐烂！").withStyle(ChatFormatting.GOLD));

			} else {
				tooltip.add(Component.translatable("·按下 SHIFT 查看详情").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
				tooltip.add(Component.translatable("·看着他们腐烂!...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
			}
		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));
		}
	}
}
