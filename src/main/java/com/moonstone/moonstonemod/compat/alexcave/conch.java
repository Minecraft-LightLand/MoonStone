package com.moonstone.moonstonemod.compat.alexcave;

import com.github.alexmodguy.alexscaves.server.entity.living.DeepOneBaseEntity;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.misc.CaveItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class conch extends CaveItem {
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
					if (player.onGround() && !player.isInWater()) {
						player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
					}
					if (ModList.get().isLoaded("alexscaves")) {
						Vec3 playerPos = player.position().add(0, 0.75, 0);
						int range = 16;
						List<DeepOneBaseEntity> entities = player.level().getEntitiesOfClass(DeepOneBaseEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
						for (DeepOneBaseEntity deepOneEntity : entities) {
							deepOneEntity.setSummonedBy(player, 60);
							deepOneEntity.setReputationOf(player.getUUID(), 100);
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
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable("· 你将不再需要空气").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("· 减少100%在水中的阻力").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable("· 增加100%在水中的所有速度").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· Deep One 将与你保持绝对的友好").withStyle(ChatFormatting.GOLD));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("· 离开水源会让你虚弱").withStyle(ChatFormatting.GOLD));
		} else {
			tooltip.add(Component.translatable("-·[SHIFT]·-").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));

		}
	}
}
