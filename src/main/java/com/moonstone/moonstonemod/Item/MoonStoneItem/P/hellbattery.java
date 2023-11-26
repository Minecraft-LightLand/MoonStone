package com.moonstone.moonstonemod.Item.MoonStoneItem.P;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class hellbattery extends AAA {
    public hellbattery(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
        MinecraftForge.EVENT_BUS.addListener(this::ccc);
    }
    private void ccc(AnvilUpdateEvent event) {
        ItemStack you = event.getRight();
        ItemStack zuo = event.getLeft().copy();
        if (!zuo.isEmpty() && !you.isEmpty()) {
            if (zuo.is(InIt.battery.get()) && you.is(Items.NETHERITE_INGOT)) {
                event.setCost(30);
                event.setMaterialCost(1);
                event.setOutput(InIt.hellbattery.get().getDefaultInstance());
            }
        }
    }
    private void bbb(LivingExperienceDropEvent event){
        Player player = event.getAttackingPlayer();
        if (Handler.hascurio(player, this)){
            event.setDroppedExperience((int) (event.getDroppedExperience() * 1.5));
        }
    }
    private void aaa(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (player.level().isRaining()){
                    event.setAmount(event.getAmount() + 3);
                }

              int a =  Mth.nextInt(RandomSource.create(),1, 5);
              if (a == 1){
                  player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.NEUTRAL, 0.1F, 0.1F);
                  player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.NEUTRAL, 0.1F, 0.1F);


                  Vec3 playerPos = player.position().add(0, 0.75, 0);
                  int range = 6;
                  List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                  for (LivingEntity living : entities){
                      if (!living.is(player)){
                          living.hurt(player.damageSources().lightningBolt(), 8);

                          if (living.level() instanceof ServerLevel serverLevel){
                              serverLevel.sendParticles(ParticleTypes.SOUL,living.getX(), living.getY(), living.getZ(), 30, 3.0D, 3.0D, 3.0D, 0.33D);
                          }
                      }
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

            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().addTransientAttributeModifiers(SOUL(player, stack));

                    if (player.level().isRaining()) {
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        int range = 1;
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                        for (LivingEntity living : entities) {
                            if (!living.is(player)) {
                                living.hurt(player.damageSources().lightningBolt(), 1);
                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.NEUTRAL, 0.03F, 0.03F);
                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.NEUTRAL, 0.03F, 0.03F);
                                if (living.level() instanceof ServerLevel serverLevel){
                                    serverLevel.sendParticles(ParticleTypes.SOUL,living.getX(), living.getY(), living.getZ(), 2, 3.0D, 3.0D, 3.0D, 0.33D);
                                }
                            }
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
            public void onEquip(SlotContext slotContext, ItemStack prevStack) {
                if (slotContext.entity() instanceof Player player){
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().removeAttributeModifiers(SOUL(player, stack));
                }
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
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 你的每一级灵魂疾行都会增加15%的移速").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 并且你的灵魂疾行可以在任何方块上生效").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 攻击时有20%的概率对周围生物造成8点闪电伤害").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 雨天攻击附加额外3点伤害").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 并且增加25%的攻速和护甲").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 并且持续对靠近你的生物造成伤害").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· +150% 经验掉落").withStyle(ChatFormatting.GOLD));
        }else {
            tooltip.add(Component.translatable("-·杜拉赫尔的力量").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-·按下 SHIFT 查看详情").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));

        }
    }
    public Multimap<Attribute, AttributeModifier> SOUL(Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeinstance = HashMultimap.create();
        double i = (double) EnchantmentHelper.getEnchantmentLevel(Enchantments.SOUL_SPEED, player) / 10 * 1.5;

        attributeinstance.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(UUID.fromString("08e81c68-0a2f-4625-882b-ddcf3c86e351"), "moonstone_speed_boost", i, AttributeModifier.Operation.MULTIPLY_TOTAL));
        if (player.level().isRaining()){
            attributeinstance.put(Attributes.ATTACK_SPEED,new AttributeModifier(UUID.fromString("08e81c68-0a2f-4625-882b-ddcf3c86e351"), "moonstone_speed_sadsboost", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
            attributeinstance.put(Attributes.ARMOR,new AttributeModifier(UUID.fromString("08e81c68-0a2f-4625-882b-ddcf3c86e351"), "moonstone_speed_basfdfdoost", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return attributeinstance;
    }
}

