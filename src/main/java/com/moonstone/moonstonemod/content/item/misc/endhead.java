package com.moonstone.moonstonemod.content.item.misc;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class endhead extends EpicItem {
	public endhead() {
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
	}

	private void aaa(LivingHurtEvent E) {
		if (E.getEntity() instanceof Player p_40714_) {
			if (CuriosHandler.hascurio(p_40714_, this)) {
				if (E.getSource().is(DamageTypes.ARROW) || E.getSource().getEntity() instanceof Projectile) {
					if (!p_40714_.getCooldowns().isOnCooldown(this)) {
						Level p_40713_ = p_40714_.level();
						if (CuriosHandler.hascurio(p_40714_, this)) {

							SoundEvent soundevent = SoundEvents.ENDERMAN_HURT;
							double d0 = p_40714_.getX();
							double d1 = p_40714_.getY();
							double d2 = p_40714_.getZ();


							p_40713_.playSound(null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
							p_40714_.getCooldowns().addCooldown(this, 100);
							for (int i = 0; i < 16; ++i) {
								double d3 = p_40714_.getX() + (p_40714_.getRandom().nextDouble() - 0.5D) * 16.0D;
								double d4 = Mth.clamp(p_40714_.getY() + (double) (p_40714_.getRandom().nextInt(16) - 8), p_40713_.getMinBuildHeight(), p_40713_.getMinBuildHeight() + ((ServerLevel) p_40713_).getLogicalHeight() - 1);
								double d5 = p_40714_.getZ() + (p_40714_.getRandom().nextDouble() - 0.5D) * 16.0D;
								if (p_40714_.isPassenger()) {
									p_40714_.stopRiding();
								}
								net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit event = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(p_40714_, d3, d4, d5);
								if (p_40714_.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
									break;
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

			@Override
			public void curioTick(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {

					Vec3 playerPos = player.position().add(0, 0.75, 0);
					int range = 14;
					List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

					for (LivingEntity living : entities) {
						if (living instanceof EnderMan EnderMan) {
							EnderMan.goalSelector.addGoal(1, new AvoidEntityGoal<>(EnderMan, Player.class, 6.0F, 1.0D, 1.2D));
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

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("· 受到弹射物伤害会瞬移 (5s)").withStyle(ChatFormatting.GOLD));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("· 末影人会惧怕于你").withStyle(ChatFormatting.GOLD));

	}
}
