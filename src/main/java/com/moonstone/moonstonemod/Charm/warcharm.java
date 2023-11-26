package com.moonstone.moonstonemod.Charm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import com.moonstone.moonstonemod.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;


public class warcharm extends Item {
    public warcharm() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("blackhead", ChatFormatting.GOLD)));
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
        MinecraftForge.EVENT_BUS.addListener(this::eee);
        MinecraftForge.EVENT_BUS.addListener(this::CCC);
    }



    private void eee(ShieldBlockEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (event.getDamageSource().getEntity() !=null && event.getDamageSource().getEntity() instanceof LivingEntity living) {
                    living.hurt(living.damageSources().playerAttack(player), event.getBlockedDamage() / 5);
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
                    living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                    living.level().playSound(null,living.getX(), living.getY(), living.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL, 1F, 1F);

                }
            }
        }
    }

    private void CCC(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
                player.heal(event.getAmount() / 10);
            }
        }
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)) {
                if (event.getSource().is(DamageTypes.LAVA)){
                    event.setAmount(event.getAmount() / 4);
                }


                if (event.getSource().getEntity() != null) {
                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        if (event.getSource().getEntity() != null) {
                            living.hurt(living.damageSources().playerAttack(player), event.getAmount() / 5);
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL, 1F, 1F);

                        }
                    }
                }
            }
        }
    }


    private void bbb(LivingDeathEvent  event) {
        if (event.getSource().getEntity() instanceof Player player) {
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()){

                            if (Handler.hascurio(player, InIt.warcharm.get())){
                                if (stack.getOrCreateTag().getDouble("rage_size")<3) {
                                    stack.getOrCreateTag().putDouble("rage_size", stack.getOrCreateTag().getDouble("rage_size") + 0.1);
                                }
                            }
                        }
                    }
                }
            });

        }
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundTag unused) {
        return CurioItemCapability.createProvider(new ICurio() {

            @Override
            public ItemStack getStack() {
                return stack;
            }

            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player) {
                    player.clearFire();
                    if (stack.getOrCreateTag().getDouble("rage_size")> 0){
                        stack.getOrCreateTag().putDouble("rage_size",stack.getOrCreateTag().getDouble("rage_size")-0.0015);
                    }
                }
            }
            @NotNull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
                return DropRule.ALWAYS_KEEP;
            }

            @Override
            public void onEquip(SlotContext slotContext, ItemStack prevStack) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().addTransientAttributeModifiers(rage_lvl(player , stack));
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().removeAttributeModifiers(rage_lvl(player , stack));

                }
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
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-·杀死生物后增加一级狂暴状态(可叠加)").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("-·在此状态下,大部分属性将以狂暴状态等级的强度获得提升").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-·受到的伤害将返还20%给进攻者").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-·攻击附加缓慢效果").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("-·减少75%来自岩浆的伤害").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("-·并快速熄灭你身上的火焰").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-·举盾时自动反击进攻者").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("-·并造成虚弱和缓慢效果").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("·+100% 抗击退").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("·+10% 吸血").withStyle(ChatFormatting.GOLD));
            //
            tooltip.add(Component.translatable(""));
        }else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-·[SHIFT]·-").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            tooltip.add(Component.translatable("-·战争使你无所不能").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("-·因为贪婪，生于战争...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("-·你......").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("-·以血肉之躯...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("-·堪比神明!...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable(""));
        }
        tooltip.add(Component.translatable("·[生命，护甲: +"+(int)(stack.getOrCreateTag().getDouble("rage_size")*100)+"%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        tooltip.add(Component.translatable("·[移速，游泳: +"+(int)(stack.getOrCreateTag().getDouble("rage_size")*100/2)+"%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        tooltip.add(Component.translatable("·[伤害，攻速: +"+(int)(stack.getOrCreateTag().getDouble("rage_size")*100/3)+"%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));

    }
    public Multimap<Attribute, AttributeModifier> rage_lvl(Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();

        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("93f46b5e-3f96-4bde-9de9-96e7210441f9"), MoonstoneMod.MODID + "souhasddsl", stack.getOrCreateTag().getDouble("rage_size"), AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("93f46b5e-3f96-4bde-9de9-96e7210441f9"), MoonstoneMod.MODID + "souaasdsdsasddsl", stack.getOrCreateTag().getDouble("rage_size"), AttributeModifier.Operation.MULTIPLY_TOTAL));

        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("93f46b5e-3f96-4bde-9de9-96e7210441f9"), MoonstoneMod.MODID + "sohasddsl", stack.getOrCreateTag().getDouble("rage_size")/2, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("93f46b5e-3f96-4bde-9de9-96e7210441f9"), MoonstoneMod.MODID + "souaasdshasddsl", stack.getOrCreateTag().getDouble("rage_size")/2, AttributeModifier.Operation.MULTIPLY_TOTAL));

        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("93f46b5e-3f96-4bde-9de9-96e7210441f9"), MoonstoneMod.MODID + "souaasadfasdnyhasddsl", stack.getOrCreateTag().getDouble("rage_size")/3, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("93f46b5e-3f96-4bde-9de9-96e7210441f9"), MoonstoneMod.MODID + "souaasfyhasddsl", stack.getOrCreateTag().getDouble("rage_size")/3, AttributeModifier.Operation.MULTIPLY_TOTAL));

            modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.fromString("93f46b5e-3f96-4bde-9de9-96e7210441f9"), MoonstoneMod.MODID + "souaasasdfyhasddsl", 10, AttributeModifier.Operation.MULTIPLY_TOTAL));

        return modifierMultimap;
    }

}

