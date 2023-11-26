package com.moonstone.moonstonemod.Item.MoonStoneItem.M;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class mbattery extends Item {
    public mbattery() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("mblock", ChatFormatting.GREEN)));
        MinecraftForge.EVENT_BUS.addListener(this::saa);
    }


    private void saa(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (event.getEntity() instanceof Zombie){
                    event.setAmount(event.getAmount() * 1.5f);
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
                    Vec3 playerPos = player.position().add(0, 0.75, 0);
                    int range = 8;
                    List<Zombie> entities = player.level().getEntitiesOfClass(Zombie.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                    for (Zombie zombie : entities) {
                        zombie.goalSelector.addGoal(1,new AvoidEntityGoal<>(zombie,Player.class,6.0F, 1.0D, 1.2D));
                    }
                }
            }

            @NotNull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
                return DropRule.ALWAYS_KEEP;
            }
            @Override
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
                Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
                modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonstoneMod.MODID+"ec", 1, AttributeModifier.Operation.ADDITION));
                modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID+"ec", 0.12, AttributeModifier.Operation.MULTIPLY_TOTAL));
                modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonstoneMod.MODID+"ec", 0.18, AttributeModifier.Operation.MULTIPLY_TOTAL));
                return modifierMultimap;
            }

            @Override
            public int getFortuneLevel(SlotContext slotContext, @Nullable LootContext lootContext) {
                return 2;
            }

            @Override
            public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting) {
                return 2;
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
        tooltip.add(Component.translatable("§2· 僵尸看见你会逃跑"));
        tooltip.add(Component.translatable("§2· 并且对僵尸造成额外50%的伤害"));
        tooltip.add(Component.translatable("§a· +2 §2时运"));
        tooltip.add(Component.translatable("§a· +2 §2抢夺"));
    }

}
