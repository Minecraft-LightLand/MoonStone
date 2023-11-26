package com.moonstone.moonstonemod.Item.MoonStoneItem.M;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class mbottle extends Item {
    public mbottle() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("mblock", ChatFormatting.GREEN)));

    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundTag unused){
        return CurioItemCapability.createProvider(new ICurio() {

            @Override
            public ItemStack getStack() {
                return stack;
            }
            @Override
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
                Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
                CuriosApi.getCuriosHelper()
                        .addSlotModifier(linkedHashMultimap, "charm", uuid, 2, AttributeModifier.Operation.ADDITION);
                return linkedHashMultimap;
            }
            @NotNull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
                return DropRule.ALWAYS_KEEP;
            }

            @Override
            public int getFortuneLevel(SlotContext slotContext, @Nullable LootContext lootContext) {
                if (slotContext.entity().hasEffect(MobEffects.DIG_SLOWDOWN)){
                    return 2;
                }
                return 0;
            }

            @Override
            public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting) {
                if (slotContext.entity().hasEffect(MobEffects.WEAKNESS)){
                    return 2;
                }
                return 0;
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
        tooltip.add(Component.translatable("§2· 当你身上的§a虚弱§2效果生效时："));
        tooltip.add(Component.translatable("§a· +2 §2抢夺"));
        tooltip.add(Component.translatable("§2· 当你身上的§a挖掘疲劳§2效果生效时："));
        tooltip.add(Component.translatable("§a· +2 §2时运"));
    }

}

