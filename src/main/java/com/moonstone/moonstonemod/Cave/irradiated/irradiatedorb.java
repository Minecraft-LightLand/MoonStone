package com.moonstone.moonstonemod.Cave.irradiated;

import com.github.alexmodguy.alexscaves.client.particle.ACParticleRegistry;
import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Item.cave;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class irradiatedorb extends cave {
    public irradiatedorb(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
    }

    private void aaa(LivingHurtEvent event) {
        if (ModList.get().isLoaded("alexscaves")){
            if (event.getSource().getEntity() instanceof Player player){
                if (Handler.hascurio(player, this)){
                    if (Mth.nextInt(RandomSource.create(), 1, 5) == 1){
                        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(player.level(), event.getEntity().getX(), event.getEntity().getY() + 0.20000000298023224, event.getEntity().getZ());
                        areaeffectcloud.setParticle(ACParticleRegistry.GAMMAROACH.get());
                        areaeffectcloud.setFixedColor(7853582);
                        areaeffectcloud.addEffect(new MobEffectInstance(ACEffectRegistry.IRRADIATED.get(), 2000));
                        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.POISON, 2000, 2));
                        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 2000, 2));
                        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 2000, 0));
                        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2000, 2));
                        areaeffectcloud.setRadius(6.6F);
                        areaeffectcloud.setDuration(600);
                        areaeffectcloud.setWaitTime(10);
                        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());

                        event.getEntity().level().addFreshEntity(areaeffectcloud);
                    }


                    event.getEntity().addEffect(new MobEffectInstance(ACEffectRegistry.IRRADIATED.get(), 600, 0));

                    if (!player.hasEffect(ACEffectRegistry.IRRADIATED.get())){
                        event.setAmount(event.getAmount() / 2);
                    }else {
                        event.setAmount(event.getAmount() * 1.33f);
                    }
                }
            }

            if (event.getEntity() instanceof Player player){
                if (Handler.hascurio(player, this)){
                    if (!player.hasEffect(ACEffectRegistry.IRRADIATED.get())){
                        event.setAmount(event.getAmount() * 1.5f);
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
                    if (ModList.get().isLoaded("alexscaves")){
                        player.removeEffect(MobEffects.POISON);
                        player.removeEffect(MobEffects.WITHER);
                        player.removeEffect(MobEffects.WEAKNESS);
                        player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);

                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        int range = 16;
                            List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                            for (LivingEntity livingEntity : entities){
                                livingEntity.addEffect(new MobEffectInstance(ACEffectRegistry.IRRADIATED.get(), 200, 0));
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
            tooltip.add(Component.translatable("· 对你周边的生物持续施加Irradiated的效果").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 且你的攻击将附加Irradiated").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 攻击有概率释放一团Irradiated Cloud").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 它的范围非常广，且持续时间极长,效果及其严重").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 你对拥有此效果的生物额外造成33%的伤害").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("· 如果没有Irradiated效果 则减少50%的伤害和伤害抗性").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("· 免疫虚弱，缓慢，中毒，凋零").withStyle(ChatFormatting.GOLD));
        }else {
            tooltip.add(Component.translatable("-·[SHIFT]·-").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        }
    }
}
