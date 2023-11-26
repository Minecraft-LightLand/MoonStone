package com.moonstone.moonstonemod.Item.medicine.med;

import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Item.medicine.extend.medIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class reanimation extends medIC {
    public reanimation() {
         MinecraftForge.EVENT_BUS.addListener(this::aaa);
    }



    private void aaa(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (!player.getCooldowns().isOnCooldown(this)) {
                    if (event.getAmount() > player.getHealth()) {
                        player.heal(player.getMaxHealth() / 2);
                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 4));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 0.8F, 0.8F);

                        player.getCooldowns().addCooldown(this, 3000);
                    }
                }
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
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("·使实验者的复杂神经系统开始重构导致坏死细胞复活").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("·同时恢复重要器官的低等功能").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("·复活").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("·5分钟冷却").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
        tooltip.add(Component.translatable(""));
    }

}

