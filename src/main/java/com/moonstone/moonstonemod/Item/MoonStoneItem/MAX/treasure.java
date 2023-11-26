package com.moonstone.moonstonemod.Item.MoonStoneItem.MAX;

import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.Collection;
import java.util.List;

public class treasure extends Item {
    public treasure(){
        super(new Properties().stacksTo(1).rarity(Rarity.create("asdas", ChatFormatting.GOLD)));
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
    }

    private void aaa(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                Collection<ItemEntity> drop = event.getDrops();
                for (ItemEntity entity : drop){
                    ItemStack stack = entity.getItem();
                    if (stack.getMaxStackSize() != 1){
                        stack.setCount(stack.getCount() * 2);
                        entity.setItem(stack);
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
        tooltip.add(Component.translatable("· 双倍战利品掉落").withStyle(ChatFormatting.GOLD));

    }

}
