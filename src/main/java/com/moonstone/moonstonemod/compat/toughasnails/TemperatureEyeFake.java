package com.moonstone.moonstonemod.compat.toughasnails;

import com.moonstone.moonstonemod.content.item.EpicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class TemperatureEyeFake extends EpicItem {

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);

		tooltip.add(Component.translatable("请安装 意志坚定 模组来使用它").withStyle(ChatFormatting.RED));

	}
}
