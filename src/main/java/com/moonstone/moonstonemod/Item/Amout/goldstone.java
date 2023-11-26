package com.moonstone.moonstonemod.Item.Amout;

import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class goldstone extends AAA {
    public goldstone(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
    }

    public ItemStack itemStack;
    private void aaa(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (Handler.hascurio(player, this)){
            if (itemStack != null) {
                if (itemStack.hasTag())
                    event.setNewSpeed(event.getNewSpeed() + (event.getNewSpeed() * ((float) itemStack.getOrCreateTag().getInt("double_stone_moonstone_gold") / 100)));
            }
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundTag unused){
        return CurioItemCapability.createProvider(new ICurio() {

            @Override
            public ItemStack getStack() {
                itemStack = stack;
                return stack;
            }

            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player) {
                    final int bbb = Mth.nextInt(RandomSource.create(), -15, 30);
                    CompoundTag tag = stack.getOrCreateTag();
                    CompoundTag compoundTag = new CompoundTag();
                    stack.addTagElement("abc", compoundTag);
                    if (stack.hasTag()) {
                        if (stack.getOrCreateTag().getInt("double_stone_moonstone_gold") == 0) {
                            tag.putInt("double_stone_moonstone_gold", bbb);
                        }
                    }
                }

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
        if (!stack.isEmpty()) {
            if (stack.hasTag()) {
                tooltip.add(Component.translatable("· 挖掘速度加成："+ stack.getOrCreateTag().getInt("double_stone_moonstone_gold")).withStyle(ChatFormatting.GOLD));
            }else {
                tooltip.add(Component.translatable("· 不要被金钱遮蔽了双眼！").withStyle(ChatFormatting.GRAY));
            }
        }
        if (Screen.hasShiftDown()){
            tooltip.add(Component.translatable("· 这是由黄金元素所打造的神秘护身符").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("· 它可以强化你的灵魂，也可以削弱你灵魂").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("· 只有最幸运的人才可以驾驭于这块护身符之上").withStyle(ChatFormatting.GRAY));
        }else {
            tooltip.add(Component.translatable("按下“SHIFT”查看").withStyle(ChatFormatting.GRAY));

        }
    }
}