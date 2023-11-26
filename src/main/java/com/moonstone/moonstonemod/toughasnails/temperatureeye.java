package com.moonstone.moonstonemod.toughasnails;

import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import toughasnails.api.temperature.ITemperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureLevel;

import java.util.List;

public class temperatureeye extends AAA {

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundTag unused){
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
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    ITemperature data = TemperatureHelper.getTemperatureData(player);
                    data.setLevel(TemperatureLevel.NEUTRAL);
                }
            }

            @Override
            public boolean canEquip(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    return !Handler.hascurio(player, stack.getItem());
                }
                return true;

            }
        });
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (ModList.get().isLoaded("toughasnails")) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("· 你将不再受到温度的限制").withStyle(ChatFormatting.GOLD));

            } else {
                tooltip.add(Component.translatable("·按下 SHIFT 查看详情").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            }
        }else {
            tooltip.add(Component.translatable("请安装 意志坚定 模组来使用它").withStyle(ChatFormatting.RED));
        }
    }
}
