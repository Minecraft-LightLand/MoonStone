package com.moonstone.moonstonemod.content.book;

import com.moonstone.moonstonemod.content.item.EpicItem;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.PatchouliAPI;

import javax.annotation.Nonnull;

public class Soulbook extends EpicItem {
	private static final ResourceLocation BOOK_ID = new ResourceLocation(MoonstoneMod.MODID, "soul_book");

	@Nonnull
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player instanceof ServerPlayer player1) {
			PatchouliAPI.get().openBookGUI(player1, BOOK_ID);
		}
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}
}
