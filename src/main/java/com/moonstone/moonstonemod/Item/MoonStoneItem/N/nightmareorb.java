package com.moonstone.moonstonemod.Item.MoonStoneItem.N;

import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import com.moonstone.moonstonemod.Item.MoonStoneItem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class nightmareorb extends nightmare {
    public nightmareorb(){
        MinecraftForge.EVENT_BUS.addListener(this::attack);
        MinecraftForge.EVENT_BUS.addListener(this::KnockBack);
        MinecraftForge.EVENT_BUS.addListener(this::hurt);
        MinecraftForge.EVENT_BUS.addListener(this::Heal);
        MinecraftForge.EVENT_BUS.addListener(this::BreakSpeed);
    }

    private void attack(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)){
                            if (stack.getOrCreateTag().getDouble("blood") < 10000) {
                                stack.getOrCreateTag().putDouble("blood", stack.getOrCreateTag().getDouble("blood") + 200);
                            }
                        }
                    }
                }
            });
        }
    }

    private void KnockBack(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player) {
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)) {
                            if (stack.getOrCreateTag().getDouble("blood") != 0) {
                                event.setStrength((float) (event.getStrength() * (1 - (stack.getOrCreateTag().getDouble("blood") / 20 / 2000))));
                            }
                        }
                    }
                }
            });
        }
    }

    private void BreakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() != null) {
            CuriosApi.getCuriosHelper().getCuriosHandler(event.getEntity()).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)) {
                            if (stack.getOrCreateTag().getDouble("blood") != 0) {
                                event.setNewSpeed((float) (event.getNewSpeed() * (1 + (stack.getOrCreateTag().getDouble("blood") / 20 / 1000))));
                            }
                        }
                    }
                }
            });
        }
    }
    private void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)){
                            if (stack.getOrCreateTag().getDouble("blood")!= 0){
                                event.setAmount((float) (event.getAmount() * (1+(stack.getOrCreateTag().getDouble("blood") / 20 / 2000))));
                            }
                        }
                    }
                }
            });
        }

        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)){
                            if (stack.getOrCreateTag().getDouble("blood") > 0){
                                player.heal(player.getMaxHealth() / 20);
                                stack.getOrCreateTag().putDouble("blood", stack.getOrCreateTag().getDouble("blood")-100);
                            }
                        }
                    }
                }
            });
        }
    }
    private void Heal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)){
                            if (stack.getOrCreateTag().getDouble("blood")!= 0){
                                event.setAmount((float) (event.getAmount() * (1+(stack.getOrCreateTag().getDouble("blood") / 20 / 1000))));
                            }
                        }
                    }
                }
            });
        }
    }
    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundTag unused){
        return CurioItemCapability.createProvider(new ICurio() {

            @Override
            public ItemStack getStack() {return stack;}
            @NotNull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
                return DropRule.ALWAYS_KEEP;
            }

            @Override
            public boolean canEquip(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    if (!Handler.hascurio(player, InIt.nightmareeye.get())){
                        return false;
                    }
                    return !Handler.hascurio(player, stack.getItem());
                }

                return true;

            }
        });
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        if (Screen.hasShiftDown()){
            tooltip.add(Component.translatable("· 每杀死一个生物将增加200点血之精华").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("· 受到伤害后消耗100点血之精华，并回复5%最大生命值").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 血之精华可以增加你的各方面属性").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(" - 生命回复，挖掘速度加成："+stack.getOrCreateTag().getDouble("blood") / 20 / 1000 * 100 + "%").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(" - 击退抗性，攻击伤害加成："+stack.getOrCreateTag().getDouble("blood") / 20 / 2000 * 100 + "%").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 血之精华:"+stack.getOrCreateTag().getDouble("blood") + "/10000").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }else {
            tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("· 唯有承受深渊之恐的人").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("· 才有资格使用此物品").withStyle(ChatFormatting.DARK_RED));
    }
}
