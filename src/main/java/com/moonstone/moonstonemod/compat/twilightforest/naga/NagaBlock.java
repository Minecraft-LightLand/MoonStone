package com.moonstone.moonstonemod.compat.twilightforest.naga;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import twilightforest.entity.boss.Naga;
import twilightforest.init.TFSounds;

import java.util.List;
import java.util.UUID;

public class NagaBlock extends Item {

	public NagaBlock() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("mblock", ChatFormatting.DARK_GREEN)));

		MinecraftForge.EVENT_BUS.addListener(this::aaa);

		MinecraftForge.EVENT_BUS.addListener(this::bbb);
	}

	private void bbb(LivingDropsEvent event) {
		if (ModList.get().isLoaded("twilightforest")) {
			if (event.getEntity() instanceof Naga naga) {
				int aaa = Mth.nextInt(RandomSource.create(), 1, 5);


				if (aaa == 1) {
					event.getDrops().add(new ItemEntity(naga.level(),
							naga.getX(), naga.getY(), naga.getZ(),
							new ItemStack(this)));
				}

				if (aaa == 2) {
					event.getDrops().add(new ItemEntity(naga.level(),
							naga.getX(), naga.getY(), naga.getZ(),
							new ItemStack(Init.nagastone.get())));
				}

				if (aaa == 3) {
					event.getDrops().add(new ItemEntity(naga.level(),
							naga.getX(), naga.getY(), naga.getZ(),
							new ItemStack(Init.nagaring.get())));
				}
				if (aaa == 4) {
					event.getDrops().add(new ItemEntity(naga.level(),
							naga.getX(), naga.getY(), naga.getZ(),
							new ItemStack(Init.nagaorb.get())));
				}
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (ModList.get().isLoaded("twilightforest")) {
			if (event.getEntity() instanceof Player player) {
				if (CuriosHandler.hascurio(player, this)) {
					if (event.getSource().getEntity() instanceof Naga naga) {
						event.setAmount(event.getAmount() / 2);
						naga.hurt(naga.damageSources().playerAttack(player), event.getAmount() * 1.5f);
						player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL, 1F, 1F);
						player.level().playSound(null, player.getX(), player.getY(), player.getZ(), TFSounds.NAGA_HURT.get(), SoundSource.NEUTRAL, 1F, 1F);
					}
					if (event.getSource().getEntity() != null) {
						if (event.getSource().getEntity() instanceof LivingEntity living && !(event.getSource().getEntity() instanceof Naga naga)) {
							if (event.getSource().getEntity() != null) {
								living.hurt(player.damageSources().playerAttack(player), event.getAmount() / 4);
								player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL, 1F, 1F);
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
			public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
				Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
				CuriosApi.getCuriosHelper()
						.addSlotModifier(linkedHashMultimap, "belt", uuid, 1, AttributeModifier.Operation.ADDITION);
				return linkedHashMultimap;
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public int getFortuneLevel(SlotContext slotContext, @Nullable LootContext lootContext) {
				return 1;
			}

			@Override
			public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting) {
				return 1;
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
			tooltip.add(Component.translatable("§a· +1 §2时运"));
			tooltip.add(Component.translatable("§a· +1 §2抢夺"));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("§a· +25% §2荆棘"));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("§2· 娜迦对你的伤害降低§a 50%"));
			tooltip.add(Component.translatable("§2· 并且你对娜迦的荆棘伤害提升至§a 150%"));
		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));

		}
	}
}
