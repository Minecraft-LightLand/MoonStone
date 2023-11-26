package com.moonstone.moonstonemod.Item.mcmod;

import com.github.L_Ender.cataclysm.entity.BossMonsters.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan.The_Leviathan_Entity;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import com.moonstone.moonstonemod.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class sevencurse extends Item {
    private final String ao_man = "ao_man";
    private final String ji_du = "ji_du";
    private final String bao_shi = "bao_shi";
    private final String tan_lan = "tan_lan";
    private final String se_yu = "se_yu";
    private final String bao_nu = "bao_nu";
    private final String bao = "bao";
    private final String lan_duo = "lan_duo";
    public sevencurse(){
        super(new Properties().stacksTo(1).defaultDurability(1).rarity(Rarity.create("asd", ChatFormatting.RED)));
        MinecraftForge.EVENT_BUS.addListener(this::KILL);
        MinecraftForge.EVENT_BUS.addListener(this::CriticalHitEvent);
        MinecraftForge.EVENT_BUS.addListener(this::HURT);
        MinecraftForge.EVENT_BUS.addListener(this::eatStart);
        MinecraftForge.EVENT_BUS.addListener(this::eatFinish);
        MinecraftForge.EVENT_BUS.addListener(this::loot);
        MinecraftForge.EVENT_BUS.addListener(this::drop);
        MinecraftForge.EVENT_BUS.addListener(this::HURT_bao_nu);

    }

    private void HURT_bao_nu(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {

            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)) {
                            if (Handler.hascurio(player, this)) {
                                if (stack.getOrCreateTag().getDouble(bao) < 0.5) {
                                    stack.getOrCreateTag().putDouble(bao, stack.getOrCreateTag().getDouble(bao) + 0.05);
                                }
                            }
                        }
                    }
                }
            });

        }
    }


    private void KILL(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (ModList.get().isLoaded("cataclysm")) {
                CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(this)) {

                                if (event.getEntity() instanceof Netherite_Monstrosity_Entity) {
                                    stack.getOrCreateTag().putBoolean(ao_man, true);

                                }
                                if (event.getEntity() instanceof Ender_Guardian_Entity) {
                                    stack.getOrCreateTag().putBoolean(ji_du, true);

                                }
                                if (event.getEntity() instanceof Ignis_Entity) {
                                    stack.getOrCreateTag().putBoolean(bao_shi, true);

                                }
                                if (event.getEntity() instanceof The_Harbinger_Entity) {
                                    stack.getOrCreateTag().putBoolean(tan_lan, true);

                                }
                                if (event.getEntity() instanceof The_Leviathan_Entity) {
                                    stack.getOrCreateTag().putBoolean(se_yu, true);

                                }
                                if (event.getEntity() instanceof WitherBoss) {
                                    stack.getOrCreateTag().putBoolean(bao_nu, true);

                                }
                                if (event.getEntity() instanceof EnderDragon) {
                                    stack.getOrCreateTag().putBoolean(lan_duo, true);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void loot(LootingLevelEvent event) {
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)){
                            if (Handler.hascurio(player, this)){
                                if (stack.getOrCreateTag().getBoolean(tan_lan)){
                                    event.setLootingLevel(event.getLootingLevel() * 2);
                                }else {
                                    event.setLootingLevel(0);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    private void drop(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player)CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (stack.is(this)){
                        if (Handler.hascurio(player, this)) {
                            if (stack.getOrCreateTag().getBoolean(tan_lan)) {
                                Collection<ItemEntity> drop = event.getDrops();
                                for (ItemEntity entity : drop) {
                                    ItemStack stack1 = entity.getItem();
                                    if (stack1.getMaxStackSize() != 1) {
                                        stack1.setCount(stack1.getCount() * 2);
                                        entity.setItem(stack1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
    private void eatStart(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)) {
                            if (Handler.hascurio(player, this)) {
                                if (Handler.hascurio(player, this)) {
                                    if (stack.getOrCreateTag().getBoolean(bao_shi)) {
                                        if (event.getItem().getUseAnimation() == UseAnim.EAT) {
                                            event.setDuration(event.getDuration() / 2);
                                        }
                                    }else {
                                        if (event.getItem().getUseAnimation() == UseAnim.EAT) {

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    private void eatFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        NonNullList<Boolean> renderStates = stacksHandler.getRenders();
                        String identifier = entry.getKey();
                        SlotContext slotContext = new SlotContext(identifier, player, i, false,
                                renderStates.size() > i && renderStates.get(i));
                        if (stack.is(this)) {
                            if (Handler.hascurio(player, this)) {
                                if (Handler.hascurio(player, this)) {
                                    if (event.getItem().getUseAnimation() == UseAnim.EAT) {

                                        if (event.getItem().is(InIt.sevenorb.get())){
                                            stack.hurtAndBreak(1,player,p->CuriosApi.getCuriosHelper().onBrokenCurio(slotContext));
                                        }
                                    }


                                    if (stack.getOrCreateTag().getBoolean(bao_shi)) {
                                        if (event.getItem().getUseAnimation() == UseAnim.EAT) {
                                            player.heal(player.getMaxHealth() / 10 +1);
                                        }
                                    }else {
                                        if (event.getItem().getUseAnimation() == UseAnim.EAT) {
                                            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 1200, 1));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    private void HURT(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {

            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)) {
                            if (Handler.hascurio(player, this)) {
                                if (stack.getOrCreateTag().getBoolean(ji_du)){
                                    event.setAmount(event.getAmount() * 0.75f);
                                }else {
                                    event.setAmount(event.getAmount() * 1.5f);


                                }
                            }
                        }
                    }
                }
            });

        }

        if (event.getSource().getEntity() instanceof Player player) {

            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)) {
                            if (Handler.hascurio(player, this)) {

                                if (!stack.getOrCreateTag().getBoolean(ji_du)){
                                    event.setAmount(event.getAmount() * 0.85f);
                                }

                                if (!player.level().isDay()) {
                                    if (!stack.getOrCreateTag().getBoolean(lan_duo)) {
                                        event.setAmount(event.getAmount() * 0.7f);
                                    } else {
                                        event.setAmount(event.getAmount() * 1.35f);
                                    }
                                }
                            }
                        }
                    }
                }
            });

        }
    }

    private void CriticalHitEvent(CriticalHitEvent event) {
        Player player = event.getEntity();
        CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (stack.is(this)){
                        if (Handler.hascurio(player, this)){
                            if (stack.getOrCreateTag().getBoolean(ao_man)){
                                event.setDamageModifier(event.getDamageModifier() * 1.25f);
                            }else {
                                event.setDamageModifier(event.getDamageModifier() * 0.75f);
                            }
                        }
                    }
                }
            }
        });

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
                    if (!stack.getOrCreateTag().getBoolean(se_yu)){
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN ,100 ,0));
                    }else {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100 ,0));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,100 ,0));
                    }
                    if (!player.level().isClientSide && player.tickCount % 20 == 1) {
                        if (stack.getOrCreateTag().getDouble(bao) > 0) {
                            stack.getOrCreateTag().putDouble(bao, stack.getOrCreateTag().getDouble(bao) - 0.01);
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
            public boolean canUnequip(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    if (player.isCreative()){
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void onEquip(SlotContext slotContext, ItemStack prevStack) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().addTransientAttributeModifiers(the_pla(player, stack));

                    /*


                    stack.getOrCreateTag().putBoolean(ao_man,false);

                    stack.getOrCreateTag().putBoolean(ji_du,false);

                    stack.getOrCreateTag().putBoolean(bao_shi,false);

                    stack.getOrCreateTag().putBoolean(tan_lan,false);

                    stack.getOrCreateTag().putBoolean(bao_nu,false);

                    stack.getOrCreateTag().putBoolean(se_yu,false);

                    stack.getOrCreateTag().putBoolean(lan_duo,false);

                     */
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                if (slotContext.entity() instanceof Player player) {
                    player.getAttributes().removeAttributeModifiers(the_pla(player, stack));
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
        if (ModList.get().isLoaded("cataclysm")) {
            if (Screen.hasShiftDown()) {
                if (!stack.getOrCreateTag().getBoolean(ao_man)) {
                    tooltip.add(Component.translatable("傲慢").withStyle(ChatFormatting.DARK_RED));
                    tooltip.add(Component.translatable("· -25% 暴击伤害").withStyle(ChatFormatting.RED));
                } else {
                    tooltip.add(Component.translatable("傲慢").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.translatable("· +25% 暴击伤害").withStyle(ChatFormatting.GOLD));

                }
                if (!stack.getOrCreateTag().getBoolean(ji_du)) {
                    tooltip.add(Component.translatable("嫉妒").withStyle(ChatFormatting.DARK_RED));
                    tooltip.add(Component.translatable("· 受到的伤害增加50%").withStyle(ChatFormatting.RED));
                } else {
                    tooltip.add(Component.translatable("嫉妒").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.translatable("· 受到的伤害减少25%").withStyle(ChatFormatting.GOLD));

                }
                if (!stack.getOrCreateTag().getBoolean(bao_shi)) {
                    tooltip.add(Component.translatable("暴食").withStyle(ChatFormatting.DARK_RED));
                    tooltip.add(Component.translatable("· 食用食物后获得饥饿效果").withStyle(ChatFormatting.RED));
                } else {
                    tooltip.add(Component.translatable("暴食").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.translatable("· 进食速度翻倍，食用食物后回复10%最大生命值").withStyle(ChatFormatting.GOLD));

                }
                if (!stack.getOrCreateTag().getBoolean(tan_lan)) {
                    tooltip.add(Component.translatable("贪婪").withStyle(ChatFormatting.DARK_RED));
                    tooltip.add(Component.translatable("· 你将无法获得任何抢夺等级加成").withStyle(ChatFormatting.RED));
                } else {
                    tooltip.add(Component.translatable("贪婪").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.translatable("· 任何来源的抢夺等级X2，双倍战利品掉落").withStyle(ChatFormatting.GOLD));

                }
                if (!stack.getOrCreateTag().getBoolean(se_yu)) {
                    tooltip.add(Component.translatable("色欲").withStyle(ChatFormatting.DARK_RED));
                    tooltip.add(Component.translatable("· 永久的缓慢效果,-15%伤害").withStyle(ChatFormatting.RED));
                } else {
                    tooltip.add(Component.translatable("色欲").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.translatable("· 永久的速度和力量效果/").withStyle(ChatFormatting.GOLD));

                }
                if (!stack.getOrCreateTag().getBoolean(bao_nu)) {
                    tooltip.add(Component.translatable("暴怒").withStyle(ChatFormatting.DARK_RED));
                    tooltip.add(Component.translatable("· 受到伤害后减少5%移动速度，可叠加至10层").withStyle(ChatFormatting.RED));
                } else {
                    tooltip.add(Component.translatable("暴怒").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.translatable("· 受到伤害后增加5%伤害,攻速，可叠加至10层").withStyle(ChatFormatting.GOLD));

                }

                if (!stack.getOrCreateTag().getBoolean(lan_duo)) {
                    tooltip.add(Component.translatable("懒惰").withStyle(ChatFormatting.DARK_RED));
                    tooltip.add(Component.translatable("· 当夜晚来临，降低30%攻击伤害").withStyle(ChatFormatting.RED));
                } else {
                    tooltip.add(Component.translatable("懒惰").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.translatable("· 当夜晚来临，增加35%攻击伤害").withStyle(ChatFormatting.GOLD));

                }

            } else {
                tooltip.add(Component.translatable("· SHIFT").withStyle(ChatFormatting.GRAY));

            }
        }else {
            tooltip.add(Component.translatable("请安装 灾变 模组来使用它").withStyle(ChatFormatting.RED));

        }

    }
    public Multimap<Attribute, AttributeModifier> the_pla(Player player, ItemStack stack){
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        double z = stack.getOrCreateTag().getDouble(bao);

        if (!stack.getOrCreateTag().getBoolean(bao_nu)){
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("ca7b21a6-76a5-4ea6-af48-b90f66e4f2b1"), MoonstoneMod.MODID + "asdsadadddssadsdfdfdg",-z, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }else {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("c6dd33f1-e851-40b4-840d-6af5ebe4ea3b"), MoonstoneMod.MODID + "asdsadadddssadsdfdfdg", z, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("c6dd33f1-e851-40b4-840d-6af5ebe4ea3b"), MoonstoneMod.MODID + "asdsadadddssadsdfdfdg",z, AttributeModifier.Operation.MULTIPLY_TOTAL));

        }
        return modifierMultimap;
    }
}

