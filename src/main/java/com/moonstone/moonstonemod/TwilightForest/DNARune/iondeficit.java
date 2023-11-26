package com.moonstone.moonstonemod.TwilightForest.DNARune;

import com.google.common.collect.Lists;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class iondeficit extends Item {
    public iondeficit() {
        super(new Properties().stacksTo(1).defaultDurability(7200).rarity(Rarity.create("asdsa", ChatFormatting.GOLD)));




        MinecraftForge.EVENT_BUS.addListener(this::aaa);
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
        MinecraftForge.EVENT_BUS.addListener(this::ccc);
        MinecraftForge.EVENT_BUS.addListener(this::ddd);

    }
    public static float anInt;
    private void aaa(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player ,this)){
                float a = acc(player);
                double b = (double) a / 20;
                event.setAmount((float) (event.getAmount() * (1+b)));
            }
        }
    }
    private void bbb(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player ,this)){
                float a = acc(player);
                double b = (double) a / 20;
                event.setAmount((float) (event.getAmount() * (1-(b / 3))));
            }
        }
    }
    private void ccc(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();

        if (Handler.hascurio(player ,this)){
            float a = acc(player);
            double b = (double) a / 20;
            event.setNewSpeed((float) (event.getNewSpeed() * (1+(b * 1.33))));
        }

    }
    private void ddd(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player ,this)){
                float a = acc(player);
                double b = (double) a / 20;
                event.setAmount((float) (event.getAmount() * (1+(b / 4))));
            }
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
                    anInt = acc(player);
                }
            }

            @NotNull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
                return DropRule.ALWAYS_KEEP;
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                if (slotContext.entity() instanceof Player player) {


                }

            }

            @Override
            public void onEquip(SlotContext slotContext, ItemStack prevStack) {

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
        if (ModList.get().isLoaded("twilightforest")) {

        } else {
            tooltip.add(Component.translatable("请安装 暮色森林 模组来 获取 它").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("但你 可以 使用它").withStyle(ChatFormatting.RED));
        }
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("每少穿戴一件饰品，就获得一些属性加成").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("目前：").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            tooltip.add(Component.translatable("生命值回复速度：+"+(float)(anInt / 20) * 100+"%").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("所有的攻击伤害：+"+(float)(anInt / 20) / 4 * 100+"%").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("挖掘速度：+"+(float)(anInt / 20) * 1.33 * 100+"%").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("伤害抗性：+"+(float)(anInt / 20) / 3 * 100+"%").withStyle(ChatFormatting.GOLD));

        } else {
            tooltip.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.GOLD));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("少带了"+anInt+"件饰品").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("最大不超过10件").withStyle(ChatFormatting.GOLD));

    }


    private float acc(Player player) {
        float size = 0;
        List<ItemStack> slot_stack = Lists.newArrayList();
        CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                   ItemStack stack = stackHandler.getStackInSlot(i);
                    slot_stack.add(stack);
                }
            }
        });

        for (ItemStack stack : slot_stack){
            if (stack.isEmpty()){
                if (size < 10) {
                    size += 1;
                }else {
                    size = 10;
                }
            }
        }

        return size;

    }
}
