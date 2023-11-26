package com.moonstone.moonstonemod.Item.MoonStoneItem.I;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import com.moonstone.moonstonemod.Item.ML_Randerer.Renderer.industryboxRanerer;
import com.moonstone.moonstonemod.Item.MoonStoneItem.industry;
import com.moonstone.moonstonemod.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class industrybox extends industry {
    private static final String industry = "industry";

    private void aaa(AnvilUpdateEvent event) {
        ItemStack you = event.getRight();
        ItemStack zuo = event.getLeft().copy();

        if (zuo.getDamageValue() != 0){
            if (you.is(this)
                    && you.getOrCreateTag().getDouble(industry) >= 0.2)
            {
                ItemStack stack = event.getLeft().copy();

                stack.setDamageValue(0);
                event.setCost(1);
                event.setMaterialCost(1);

                event.setOutput(stack);
            }
        }
    }
    private void cccc(LivingEntityUseItemEvent.Finish event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){

            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(this)){
                            if (Handler.hascurio(player, this)){

                                if (stack.getOrCreateTag().getDouble(industry) < 0.2){
                                    if (event.getItem().is(InIt.ectoplasmcube.get())){
                                        stack.getOrCreateTag().putDouble(industry,stack.getOrCreateTag().getDouble(industry)+ 0.02 );
                                    }
                                }
                            }
                        }
                    }
                }
            });



        }
    }
    public industrybox() {
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
        MinecraftForge.EVENT_BUS.addListener(this::ccc);
        MinecraftForge.EVENT_BUS.addListener(this::cccc);

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
                    industryboxRanerer.i += 3;
                    industryboxRanerer.d += 0.07f;

                    if (!player.level().isClientSide &&  player.tickCount % 20 == 0) {
                        if (stack.getOrCreateTag().getDouble(industry) > -0.33) {
                            stack.getOrCreateTag().putDouble(industry, stack.getOrCreateTag().getDouble(industry) - 0.00003);
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
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().removeAttributeModifiers(the_pla(player, stack));
                }
            }

            @Override
            public void onEquip(SlotContext slotContext, ItemStack prevStack) {
                if (slotContext.entity() instanceof Player player){
                    player.getAttributes().addTransientAttributeModifiers(the_pla(player, stack));
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
    private void ccc(LivingHealEvent event) {
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
                                double a = stack.getOrCreateTag().getDouble(industry);
                                event.setAmount((float) (event.getAmount() *(1 + a)));
                            }
                        }
                    }
                }
            });

        }
    }
    public Multimap<Attribute, AttributeModifier> the_pla(Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        double a = stack.getOrCreateTag().getDouble(industry);
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("dbef6173-b792-3041-821b-b11445f44ee8"), MoonstoneMod.MODID + "ppgbgb", a, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("dbef6173-b792-3041-821b-b11445f44ee8"), MoonstoneMod.MODID + "ppgbgb", a, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("dbef6173-b792-3041-821b-b11445f44ee8"), MoonstoneMod.MODID + "ppgbgb", a, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("-33%到+20%的属性加成").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("这些属性为：生命回复,伤害,攻速,移速").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("但是需要偶尔食用灵质立方来为反应增加燃料").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("Now:  "+stack.getOrCreateTag().getDouble(industry) * 100 + "%").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("当反应炉的能量输出大于20%时").withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.translatable("在铁砧上可修复任何物品耐久").withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("修复后 此物品将消失").withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("· 在开启饰品栏上方的按钮后").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("· 召唤一个小反应炉旋转").withStyle(ChatFormatting.GRAY));

    }
}
