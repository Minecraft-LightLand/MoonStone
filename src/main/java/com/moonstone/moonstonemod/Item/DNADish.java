package com.moonstone.moonstonemod.Item;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Item.MoonStoneItem.rage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DNADish extends rage {
    public DNADish(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
        MinecraftForge.EVENT_BUS.addListener(this::tick);
        MinecraftForge.EVENT_BUS.addListener(this::t);
    }
    private String string;
    private void t(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(this)) {
            if ((int) (stack.getOrCreateTag().getFloat(string) / 250) != 0) {
                event.getToolTip().add(Component.translatable(string + "生物类DNA采集样本 " + (int) (stack.getOrCreateTag().getFloat(string) / 250) + " 级").withStyle(ChatFormatting.DARK_RED));
                float aaa = (int) (stack.getOrCreateTag().getFloat(string) / 250);
                float do_this = aaa / 10;
                int do_t = (int) (do_this * 100);
                if (do_this <= 1) {
                    event.getToolTip().add(Component.translatable("对" + string + "生物的伤害增加" + do_t + "%").withStyle(ChatFormatting.RED));
                } else {
                    event.getToolTip().add(Component.translatable("对" + string + "生物的伤害增加" + 100 + "%").withStyle(ChatFormatting.RED));
                }
            } else {
                event.getToolTip().add(Component.translatable(string + "生物类DNA采集样本 " + 0 + " 级").withStyle(ChatFormatting.DARK_RED));

            }
        }
    }


    private void aaa(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)) {
                            if (Handler.hascurio(player, this)) {
                                LivingEntity living = event.getEntity();
                                String name = living.getEncodeId();

                                if (name != null) {
                                    if (stack.getOrCreateTag().getFloat(name) < 10) {
                                        stack.getOrCreateTag().putFloat(name, stack.getOrCreateTag().getFloat(name) + 1);
                                    }
                                }

                                string = name;

                                if (!player.level().isClientSide) {
                                    player.displayClientMessage(Component.literal("已采集 " + name + " 的DNA! " + " 目前为" + stack.getOrCreateTag().getFloat(name) + "点").withStyle(ChatFormatting.RED), true);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    private String S;
    private void tick(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)) {
                            if (Handler.hascurio(player, this)) {

                                LivingEntity target = event.getEntity();

                                ResourceLocation registryName = ForgeRegistries.ENTITY_TYPES.getKey(target.getType());
                                Set<String> a = stack.getOrCreateTag().getAllKeys();
                                for (String s : a){
                                    if (registryName != null && registryName.equals(new ResourceLocation(s))) {
                                        S = s;

                                        if ((float) ((int) (stack.getOrCreateTag().getFloat(s) / 250)) < 10) {
                                            float aaa = (int)(stack.getOrCreateTag().getFloat(s) / 250);
                                            float do_this = aaa / 10;
                                            event.setAmount(event.getAmount() * (1 + do_this));
                                        }else {
                                            event.setAmount(event.getAmount() * (1 + 1));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        if (Screen.hasShiftDown()){
            tooltip.add(Component.translatable("· 杀死生物后增加1点生物点数").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("· 每增加250点生物点数将增加一级该生物的样本等级").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("· 每级样本等级都将增加10%对该生物的伤害").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("· 最多增加100%的伤害").withStyle(ChatFormatting.RED));
        }else {
            tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("·你最近一次采集了 "+string+" 的DNA").withStyle(ChatFormatting.RED));

    }
}
