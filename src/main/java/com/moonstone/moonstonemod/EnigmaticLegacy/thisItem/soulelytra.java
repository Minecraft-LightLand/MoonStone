package com.moonstone.moonstonemod.EnigmaticLegacy.thisItem;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.UUID;

public class soulelytra extends ArmorItem {

    public soulelytra() {
        super(new ArmorMaterial() {

            @Override
            public int getDurabilityForType(Type p_266807_) {
                return 5000;
            }

            @Override
            public int getDefenseForType(Type p_267168_) {
                return 10;
            }

            @Override
            public int getEnchantmentValue() {
                return 30;
            }

            @Override
            public SoundEvent getEquipSound() {
                return null;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return null;
            }

            @Override
            public String getName() {
                return "moonstonearmor";
            }

            @Override
            public float getToughness() {
                return 5;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.25F;
            }
        }, Type.CHESTPLATE ,new Properties().stacksTo(1).rarity(Rarity.EPIC));

        MinecraftForge.EVENT_BUS.addListener(this::aaaa);
        MinecraftForge.EVENT_BUS.addListener(this::tick);
    }

    private  void tick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player){
            if (player.getItemBySlot(EquipmentSlot.CHEST).is(this)){
                if (!Handler.hascurio(player,ForgeRegistries.ITEMS.getValue(new ResourceLocation("enigmaticlegacy:cursed_ring")))) {
                    player.addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2));
                }
            }
        }
    }
    Player player1;
    private  void aaaa(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player){
            player1 = player;
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_40392_, ItemStack p_40393_) {
        return true;
    }
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {

        int nextFlightTick = flightTicks + 1;
        if (nextFlightTick % 10 == 0) {
            if (nextFlightTick % 20 == 0) {
                stack.hurtAndBreak(5, entity, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
            }
        }
        return true;
    }
    @Override
    public boolean canElytraFly(ItemStack stack ,LivingEntity entity) {
        if (entity instanceof Player p){
            if (Handler.hascurio(p, ForgeRegistries.ITEMS.getValue(new ResourceLocation("enigmaticlegacy:cursed_ring")))){
                return true;
            }
        }
        return false;
    }
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "moonstone:textures/models/armor/null.png";
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.builder();
        double aaa = 10.0D;
        double bbb = 0.2D;
        double ccc = 0.25D;
        double ddd = 0.15D;
        attributeBuilder.putAll(super.getDefaultAttributeModifiers(slot));
        attributeBuilder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("7f10172d-de69-49d7-81bd-9594286a6827"), "jkasnsadsadhfbsa", aaa, AttributeModifier.Operation.ADDITION));
        attributeBuilder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("7f10172d-de69-49d7-81bd-9594286a6827"), "jsnadnahssafdffb", bbb, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("7f10172d-de69-49d7-81bd-9594286a6827"), "skandjsafgbgbgbn", ddd, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributeBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("7f10172d-de69-49d7-81bd-9594286a6827"), "skandjsafgbgbgbn", ccc, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributeBuilder.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("7f10172d-de69-49d7-81bd-9594286a6827"), "skandjsafgbgbgbn", 0.35, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return (slot == EquipmentSlot.CHEST) ? attributeBuilder.build() : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("enigmaticlegacy:cursed_ring"));

        if (player1 != null) {
            if (Handler.hascurio(player1, item)) {
                tooltip.add(Component.translatable("§5-鞘翅和胸甲的结合体"));
                tooltip.add(Component.translatable("§5-滑翔时消耗耐久更快"));
                tooltip.add(Component.translatable(""));
                tooltip.add(Component.translatable("§5-空中按下R进行加速(服务器无效)"));
                tooltip.add(Component.translatable("§5-不会按照视线加速"));
                tooltip.add(Component.translatable("§5-而是按当前飞行轨迹加速"));
                tooltip.add(Component.translatable(""));
                tooltip.add(Component.translatable("只有承受七咒的人").withStyle(ChatFormatting.GOLD));
                tooltip.add(Component.translatable("才能使用此物品").withStyle(ChatFormatting.GOLD));

            } else {
                tooltip.add(Component.translatable("§5§kaaaaaaaaaaaaa"));
                tooltip.add(Component.translatable("§5§kaaaaaaaaa"));
                tooltip.add(Component.translatable(""));
                tooltip.add(Component.translatable("§5§kaaaaaaaaaaaaa"));
                tooltip.add(Component.translatable("§5§kaaaaaaaaa"));
                tooltip.add(Component.translatable("§5§kaaaaaaaaa"));
                tooltip.add(Component.translatable(""));
                tooltip.add(Component.translatable("只有承受七咒的人").withStyle(ChatFormatting.DARK_RED));
                tooltip.add(Component.translatable("才能使用此物品").withStyle(ChatFormatting.DARK_RED));

            }

        }
    }
}
