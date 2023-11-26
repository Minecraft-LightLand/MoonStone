package com.moonstone.moonstonemod.content.item.misc;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class MaxAmout extends Item {
	public MaxAmout() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("asdas", ChatFormatting.GOLD)));

		MinecraftForge.EVENT_BUS.addListener(this::bbb);

	}

	private void bbb(LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {

				if (event.getSource().getEntity() != null) {
					if (event.getSource().getEntity() instanceof LivingEntity living) {
						if (!(living instanceof Guardian)) {
							if (event.getSource().getEntity() != null) {
								living.hurt(living.damageSources().playerAttack(player), event.getAmount() / 5);
								player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL, 1F, 1F);

							}
						}
					}
				}

				event.setAmount(event.getAmount() * 0.85f);
				if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
					player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20, 2));
				}
			}
		}
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
				player.heal(event.getAmount() / 10);

				if (Mth.nextInt(RandomSource.create(), 1, 4) == 1) {
					player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
					event.getEntity().knockback(2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));
					event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.YELLOW_WOOL.defaultBlockState()));

					player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0));

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
					if (player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
						player.removeEffect(MobEffects.DIG_SLOWDOWN);
					}
				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public void onEquip(SlotContext slotContext, ItemStack prevStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().addTransientAttributeModifiers(swim(player, stack));
				}
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().removeAttributeModifiers(swim(player, stack));
				}
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
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-·攻击会削弱敌人").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("-·并且减少15%受到伤害").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-·受伤时有概率获得伤害吸收效果").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("-·增加10%吸血").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-·攻击时有概率击飞敌人并让其减速").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("-·并获得力量效果").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-·受到伤害后将返还20%给进攻者").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("-·免疫挖掘疲劳效果").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("-·增加75%游泳速度").withStyle(ChatFormatting.GOLD));
		} else {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-·[SHIFT]·-").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
			tooltip.add(Component.translatable("-·这是远古之遗...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("-·是它们的一切...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("-·而你......").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("-·能否再创辉煌?...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable(""));

		}
	}

	public Multimap<Attribute, AttributeModifier> swim(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
		modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("82a617a0-56fe-4ead-82d4-9d968ca7c777"), MoonstoneMod.MODID + "souddsl", 0.75, AttributeModifier.Operation.MULTIPLY_TOTAL));
		return modifierMultimap;
	}
}


