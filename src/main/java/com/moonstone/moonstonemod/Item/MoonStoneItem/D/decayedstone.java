package com.moonstone.moonstonemod.Item.MoonStoneItem.D;

import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

public class decayedstone extends AAA {
    public decayedstone(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
    }

    private void bbb(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                ItemStack HEAD = player.getItemBySlot(EquipmentSlot.HEAD);
                if (!HEAD.isEmpty()) {
                    if (HEAD.getMaxDamage() != 0) {
                        HEAD.setDamageValue(HEAD.getDamageValue() - HEAD.getMaxDamage() / 100);
                    }
                }
                ItemStack CHEST = player.getItemBySlot(EquipmentSlot.CHEST);
                if (!CHEST.isEmpty()) {
                    if (CHEST.getMaxDamage() != 0) {
                        CHEST.setDamageValue(CHEST.getDamageValue() - CHEST.getMaxDamage() / 100);
                    }
                }
                ItemStack LEGS = player.getItemBySlot(EquipmentSlot.LEGS);
                if (!LEGS.isEmpty()) {
                    if (LEGS.getMaxDamage() != 0) {
                        LEGS.setDamageValue(LEGS.getDamageValue() - LEGS.getMaxDamage() / 100);
                    }
                }
                ItemStack FEET = player.getItemBySlot(EquipmentSlot.FEET);
                if (!FEET.isEmpty()) {
                    if (FEET.getMaxDamage() != 0) {
                        FEET.setDamageValue(FEET.getDamageValue() - FEET.getMaxDamage() / 100);
                    }
                }
            }
        }

    }

    private void aaa(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player PP){
            if (Handler.hascurio(PP, this)){
                PP.heal(event.getAmount() / 10);
            }
        }
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                    player.heal(player.getMaxHealth() / 10);

                    ItemStack HEAD = player.getItemBySlot(EquipmentSlot.HEAD);
                    if (!HEAD.isEmpty()) {
                        if (HEAD.getMaxDamage() != 0) {
                            HEAD.hurtAndBreak((int) ((event.getAmount() + 1) * 9), player, (p) -> p.broadcastBreakEvent(EquipmentSlot.HEAD));
                        }
                    }
                    ItemStack CHEST = player.getItemBySlot(EquipmentSlot.CHEST);
                    if (!CHEST.isEmpty()) {
                        if (CHEST.getMaxDamage() != 0) {
                            CHEST.hurtAndBreak((int) ((event.getAmount() + 1) * 9), player, (p) -> p.broadcastBreakEvent(EquipmentSlot.CHEST));
                        }
                    }
                    ItemStack LEGS = player.getItemBySlot(EquipmentSlot.LEGS);
                    if (!LEGS.isEmpty()) {
                        if (LEGS.getMaxDamage() != 0) {
                            LEGS.hurtAndBreak((int) ((event.getAmount() + 1) * 9), player, (p) -> p.broadcastBreakEvent(EquipmentSlot.LEGS));
                        }
                    }
                    ItemStack FEET = player.getItemBySlot(EquipmentSlot.FEET);
                    if (!FEET.isEmpty()) {
                        if (FEET.getMaxDamage() != 0) {
                            FEET.hurtAndBreak((int) ((event.getAmount() + 1) * 9), player, (p) -> p.broadcastBreakEvent(EquipmentSlot.FEET));
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

            @NotNull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
                return DropRule.ALWAYS_KEEP;
            }

            @Override
            public void onEquip(SlotContext slotContext, ItemStack prevStack) {
                stack.getOrCreateTag().putBoolean("color_moonstone", true);
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
        tooltip.add(Component.translatable("§4· 受到伤害时有§c20%§4的概率回复§c10%§4的最大生命值"));
        tooltip.add(Component.translatable("§4· 但身着盔甲会受到§c9§4倍来自伤害的耐久损耗"));
        tooltip.add(Component.translatable("§4· 杀死生物后会恢复§c1%§4的盔甲耐久"));
        tooltip.add(Component.translatable("§c +10% 吸血"));

    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }
}
