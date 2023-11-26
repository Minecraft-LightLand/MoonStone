package com.moonstone.moonstonemod.Item.MoonStoneItem.T;

import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
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
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class twistedyoke extends AAA {
    public twistedyoke(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
        MinecraftForge.EVENT_BUS.addListener(this::ccc);
        MinecraftForge.EVENT_BUS.addListener(this::ddd);
    }
    private void ccc(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, this)) {
                event.setAmount(event.getAmount() * 0.85f);
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, this)) {
                player.heal(event.getAmount() / 5);
            }
        }
    }
    private void ddd(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, this)) {
                player.heal(player.getMaxHealth() / 3);
            }
        }
    }
    private void aaa(CriticalHitEvent event) {
        Player player = event.getEntity();
        if (Handler.hascurio(player, this)){
           if (player.getHealth() == player.getMaxHealth()){
               event.setDamageModifier(event.getDamageModifier() * 1.1f);
           }
        }
    }
    private void bbb(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, this)) {
                event.setAmount(event.getAmount() / 4);
            }
        }
    }
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
        tooltip.add(Component.translatable("· -75% 生命恢复速度").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("· +10% 满血时的暴击伤害").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("· +15% 伤害抗性").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("· +20% 吸血").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("· +30% 杀戮吸血").withStyle(ChatFormatting.RED));

    }
}
