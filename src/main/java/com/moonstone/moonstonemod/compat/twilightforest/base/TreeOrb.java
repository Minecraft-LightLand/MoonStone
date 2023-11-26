package com.moonstone.moonstonemod.compat.twilightforest.base;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import twilightforest.entity.monster.LoyalZombie;
import twilightforest.init.TFEntities;
import twilightforest.init.TFSounds;

import java.util.List;

public class TreeOrb extends Item {
	public TreeOrb() {
		super(new Properties().stacksTo(1).defaultDurability(7200).rarity(Rarity.create("asdsa", ChatFormatting.GOLD)));
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::bbb);
	}

	private void bbb(LivingDeathEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (ModList.get().isLoaded("twilightforest")) {
				if (CuriosHandler.hascurio(player, this)) {
					player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
					MobEffect aaa = MobEffects.REGENERATION;
					int lvl = 0;
					int time = 200;
					if (player.hasEffect(aaa)) {
						time += 100;
						player.addEffect(new MobEffectInstance(aaa, time, lvl));
					}


					if (!player.getCooldowns().isOnCooldown(this)) {
						if (ModList.get().isLoaded("twilightforest")) {
							LoyalZombie zombie = TFEntities.LOYAL_ZOMBIE.get().create(player.level());
							if (zombie != null) {
								zombie.spawnAnim();
							}
							if (zombie != null) {
								zombie.setTame(true);
							}
							if (zombie != null) {
								zombie.setOwnerUUID(player.getUUID());
							}
							if (zombie != null) {
								zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1200, 1));
							}

							if (zombie != null) {
								event.getEntity().level().addFreshEntity(zombie);
							}
							if (zombie != null) {
								zombie.playSound(TFSounds.LOYAL_ZOMBIE_SUMMON.get(), 1.0F, zombie.getVoicePitch());
							}
							if (zombie != null) {
								zombie.level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));
							}
							player.getCooldowns().addCooldown(this, 200);
						}
					}
				}
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (ModList.get().isLoaded("twilightforest")) {
				if (CuriosHandler.hascurio(player, this)) {
					LivingEntity living = event.getEntity();
					if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
						living.level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));
						living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 4));
						player.heal(2);
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
		if (ModList.get().isLoaded("twilightforest")) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 攻击有12.5%的概率使被攻击生物获得6秒缓慢5级效果").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 并吸取一定生命值").withStyle(ChatFormatting.GOLD));

				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 杀死生物后召唤一只驯服的僵尸").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 驯服的僵尸会被削弱").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 并且此技能有10秒冷却").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 杀死生物后获得10秒生命回复效果").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 在有生命回复效果的前提下").withStyle(ChatFormatting.GOLD));
				tooltip.add(Component.translatable("· 每杀死一个生物生命回复效果的时长增加5秒").withStyle(ChatFormatting.GOLD));

			} else {
				tooltip.add(Component.translatable("·按下 SHIFT 查看详情").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
			}
		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));
		}
	}
}
