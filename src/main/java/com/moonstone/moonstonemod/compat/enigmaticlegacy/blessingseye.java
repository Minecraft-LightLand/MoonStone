package com.moonstone.moonstonemod.compat.enigmaticlegacy;

import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.EpicItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class blessingseye extends EpicItem {
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		boolean a = super.onLeftClickEntity(stack, player, entity);
		if (entity instanceof LivingEntity living) {
			if (stack.getOrCreateTag().getInt("health") != 0) {
				stack.getOrCreateTag().remove("health");
			}
			if (stack.getOrCreateTag().getInt("armor") != 0) {
				stack.getOrCreateTag().remove("armor");
			}
			if (stack.getOrCreateTag().getInt("attack") != 0) {
				stack.getOrCreateTag().remove("attack");
			}
			if (stack.getOrCreateTag().getInt("attack_speed") != 0) {
				stack.getOrCreateTag().remove("attack_speed");
			}

			if (stack.getOrCreateTag().getInt("max_health") != 0) {
				stack.getOrCreateTag().remove("max_health");
			}
			stack.getOrCreateTag().putInt("health", (int) living.getHealth());
			stack.getOrCreateTag().putInt("armor", living.getArmorValue());

			if (living.getAttributes().getInstance(Attributes.ATTACK_DAMAGE) != null) {
				stack.getOrCreateTag().putInt("attack", (int) living.getAttributes().getValue(Attributes.ATTACK_DAMAGE));
			}
			if (living.getAttributes().getInstance(Attributes.ATTACK_SPEED) != null) {
				stack.getOrCreateTag().putInt("attack_speed", (int) living.getAttributes().getValue(Attributes.ATTACK_SPEED));
			}
			if (living.getAttributes().getInstance(Attributes.MAX_HEALTH) != null) {
				stack.getOrCreateTag().putInt("max_health", (int) living.getAttributes().getValue(Attributes.MAX_HEALTH));
			}

		}
		return a;
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
					player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1));
				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public boolean canEquip(SlotContext slotContext) {
				boolean aaa = false;
				if (slotContext.entity() instanceof Player player) {
					aaa = !CuriosHandler.hascurio(player, stack.getItem());
				}
				return aaa;

			}
		});
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("§5-§6左键§5生物可调取此生物的一些属性"));
		tooltip.add(Component.translatable("§5-如果属性为§d0§5则代表不存在或不拥有"));
		tooltip.add(Component.translatable("§6-属性如下："));
		tooltip.add(Component.translatable("§5-此生物的最大生命值:§6" + stack.getOrCreateTag().getInt("max_health")));
		tooltip.add(Component.translatable("§5-此生物的当前生命值:§6" + stack.getOrCreateTag().getInt("health")));
		tooltip.add(Component.translatable("§5-此生物的护甲值:§6" + stack.getOrCreateTag().getInt("armor")));
		tooltip.add(Component.translatable("§5-此生物的攻击速度:§6" + stack.getOrCreateTag().getInt("attack_speed")));
		tooltip.add(Component.translatable("§5-此生物的攻击伤害:§6" + stack.getOrCreateTag().getInt("attack")));
		tooltip.add(Component.translatable("§6-佩戴后获得夜视效果"));


	}
}
