package com.moonstone.moonstonemod.Item.MoonStoneItem.R;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Item.MoonStoneItem.rage;
import com.moonstone.moonstonemod.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import java.util.UUID;

public class ragecharm extends rage {

    public ragecharm(){
        MinecraftForge.EVENT_BUS.addListener(this::bbb);

    }




    private void bbb(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                event.setAmount(event.getAmount() * 3);

            }
        }
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){

                if (event.getEntity().getHealth() / event.getEntity().getMaxHealth() <= 0.2){
                    if (player.getHealth() > event.getEntity().getHealth()){
                        event.getEntity().kill();
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
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().removeAttributeModifiers(world(player, stack));
                }
            }

            @Override
            public void onEquip(SlotContext slotContext, ItemStack prevStack) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().addTransientAttributeModifiers(world(player, stack));
                }
            }

            @Override
            public boolean canEquip(SlotContext slotContext) {
                boolean aaa = false;
                if (slotContext.entity() instanceof Player player){
                    aaa = !Handler.hascurio(player, stack.getItem());
                }
                return aaa;

            }
        });
    }


    public Multimap<Attribute, AttributeModifier> world (Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("58c97c29-2c25-4320-a1d2-8e5651de506b"), MoonstoneMod.MODID + "souaasl", 1, AttributeModifier.Operation.MULTIPLY_TOTAL));

        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {

            tooltip.add(Component.translatable("· +100% 最大生命值").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("· +200% 受到的伤害").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 当目标生物的血量降低到20%以下").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("· 且你的当前生命值大于目标血量时").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("· 目标将直接死亡").withStyle(ChatFormatting.RED));

        } else {
            tooltip.add(Component.translatable("· Shift").withStyle(ChatFormatting.RED));
        }

        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("· 强者愈弱......").withStyle(ChatFormatting.RED));

    }
}
