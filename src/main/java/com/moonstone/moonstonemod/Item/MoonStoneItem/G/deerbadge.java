package com.moonstone.moonstonemod.Item.MoonStoneItem.G;

import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class deerbadge extends AAA {
    public deerbadge() {
        MinecraftForge.EVENT_BUS.addListener(this::aaa);

    }

    private void aaa(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (event.getItem().is(Items.GOLDEN_CARROT)){
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
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
        tooltip.add(Component.translatable("· 吃下金胡萝卜会获得生命恢复效果").withStyle(ChatFormatting.GOLD));

    }
}
