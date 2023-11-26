package com.moonstone.moonstonemod.Item.MoonStoneItem.M;

import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class mring extends Item {
    public mring() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("mblock", ChatFormatting.GREEN)));

        MinecraftForge.EVENT_BUS.addListener(this::ddd);
        MinecraftForge.EVENT_BUS.addListener(this::kkk);

    }

    private void kkk(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                event.setStrength(event.getStrength() * 1.8f);
            }
        }

    }

    private  void ddd(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                event.setAmount(event.getAmount() * 1.35f);
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
        tooltip.add(Component.translatable("§a· +35% §2生命恢复速度"));
        tooltip.add(Component.translatable("§a· +80% §2被击退距离"));
    }

}


