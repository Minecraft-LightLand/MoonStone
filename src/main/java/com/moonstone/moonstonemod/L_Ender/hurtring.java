package com.moonstone.moonstonemod.L_Ender;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.moonstone.moonstonemod.AAA;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class hurtring extends AAA {

    public hurtring(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
        MinecraftForge.EVENT_BUS.addListener(this::bbb);
    }
    private void bbb(AnvilUpdateEvent event) {
        ItemStack you = event.getRight();
        ItemStack zuo = event.getLeft().copy();
        if (!zuo.isEmpty()) {
            if (you != null) {
                if (zuo.is(InIt.twistedring.get()) && you.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("cataclysm:bulwark_of_the_flame")))) {
                    event.setCost(25);
                    event.setMaterialCost(25);
                    event.setOutput(this.asItem().getDefaultInstance());
                }
            }
        }
    }
    private void aaa(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (ModList.get().isLoaded("cataclysm")) {
                if (Handler.hascurio(player, this)) {
                    if (Mth.nextInt(RandomSource.create(), 1, 4) == 1) {
                        event.getEntity().addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 100, 1));
                        event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.YELLOW_WOOL.defaultBlockState()));
                        event.getEntity().knockback(2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));
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
                tooltip.add(Component.translatable("攻击时有25%的概率造成重击").withStyle(ChatFormatting.GOLD));
                tooltip.add(Component.translatable("使其眩晕5秒，并造成击退").withStyle(ChatFormatting.GOLD));
            } else {
                tooltip.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.GOLD));
            }
        }else {
            tooltip.add(Component.translatable("请安装 灾变 模组来使用它").withStyle(ChatFormatting.RED));
        }
    }
}
