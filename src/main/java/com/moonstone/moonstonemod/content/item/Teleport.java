package com.moonstone.moonstonemod.content.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class Teleport extends EpicItem {
	@Override
	public void releaseUsing(ItemStack stack, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
		int i = this.getUseDuration(stack) - p_40670_;
		if (p_40669_ instanceof Player player) {
			float f = BowItem.getPowerForTime(i);
			if (f == 1.0f && !player.getCooldowns().isOnCooldown(stack.getItem())) {
				if (player instanceof ServerPlayer serverPlayer) {
					List<? extends Player> players = serverPlayer.level().players();
					for (Player p : players) {
						if (p != null) {
							stack.getOrCreateTag().putString("player_name", p.getScoreboardName());
							if (stack.getDisplayName().toString().contains(p.getScoreboardName())) {
								if (serverPlayer.level() == p.level()) {
									serverPlayer.teleportTo(p.getX(), p.getY(), p.getZ());
									serverPlayer.level().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 1F, 1F);
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public int getUseDuration(ItemStack p_40680_) {
		return 72000;
	}

	public UseAnim getUseAnimation(ItemStack p_40678_) {
		return UseAnim.BOW;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
		InteractionResultHolder<ItemStack> stackInteractionResultHolder = super.use(level, player, p_41434_);

		player.startUsingItem(p_41434_);
		return stackInteractionResultHolder;
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable("使用铁砧给物品重新命名，并长按右键寻找玩家").withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.translatable("再次长按右键传送到与物品名称相匹配的玩家附近").withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.translatable("只能在同一维度生效").withStyle(ChatFormatting.GRAY));


		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("测试阶段物品，谨慎使用").withStyle(ChatFormatting.RED));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("name:" + stack.getDisplayName()));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("playerName:" + stack.getOrCreateTag().getString("player_name")));

	}
}
