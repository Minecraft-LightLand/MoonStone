package com.moonstone.moonstonemod.Item.MoonStoneItem.P;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonstoneMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;

public class bigwarcrystal extends AAA {
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
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
                Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
                modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonstoneMod.MODID+"ec", 0.225, AttributeModifier.Operation.MULTIPLY_TOTAL));
                return modifierMultimap;
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
}
