package com.moonstone.moonstonemod.Item.MoonStoneItem.MAX;

import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class blackhead extends Item {
    public blackhead(){
        super(new Properties().stacksTo(1).rarity(Rarity.create("blackhead", ChatFormatting.GOLD)));
        MinecraftForge.EVENT_BUS.addListener(this::aaaa);
        MinecraftForge.EVENT_BUS.addListener(this::bbb);

    }

    private void bbb(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, this)){
                if (player.getCooldowns().isOnCooldown(this)){
                    player.getCooldowns().removeCooldown(this);
                    player.heal(player.getMaxHealth() / 10);
                }
            }
        }
    }

    public float dmg;

    private void aaaa(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, this)) {
                if (player.getCooldowns().isOnCooldown(this)){
                    dmg += event.getAmount() / 2;
                }

                if (!player.getCooldowns().isOnCooldown(this)){
                    event.setAmount(event.getAmount()* 0);
                    player.getCooldowns().addCooldown(this, 200);

                    if (event.getSource().getEntity() instanceof LivingEntity living){
                        living.knockback(2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));
                        living.hurt(living.damageSources().playerAttack(player), dmg + 1);

                        player.level().playSound(null,player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_HURT, SoundSource.NEUTRAL, 0.5F, 0.5F);

                        dmg = 0;
                    }
                }
            }
        }

        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (!player.getCooldowns().isOnCooldown(this)) {
                    LivingEntity l = event.getEntity();
                    if (l instanceof Player player1) {
                        player.getCooldowns().addCooldown(this, 140);
                        Iterable<ItemStack> itemStacks = player1.getAllSlots();
                        for (ItemStack stack : itemStacks) {
                            if (!stack.isEmpty()) {
                                player1.getCooldowns().addCooldown(stack.getItem(), 50);
                            }
                        }
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

            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){

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
            tooltip.add(Component.translatable("· 增加黑魔球的属性").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 攻击禁用其所有的物品和武器，持续2.5秒").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 同时此物品会进入冷却状态，持续7秒").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 受到伤害后直接免疫此次攻击").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 并且自动反击攻击者").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 但有10秒钟冷却").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 在物品冷却期间受到的伤害将转换成下次反击的伤害").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 杀死生物后此物品间隔减少至0秒").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 并回复10%的血量").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
        }else {
            tooltip.add(Component.translatable("-·[SHIFT]·-").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            tooltip.add(Component.translatable("-·纯粹的黑暗魔法").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("-·使你藐视万物").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("-·但......").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("-·代价是什么?...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable(""));
        }
        tooltip.add(Component.translatable("Dmg:"+dmg).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));


    }
}


