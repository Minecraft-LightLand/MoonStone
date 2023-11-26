package com.moonstone.moonstonemod;

import com.moonstone.moonstonemod.Cave.LOOT.conchloot;
import com.moonstone.moonstonemod.Item.MSLoot.*;
import com.moonstone.moonstonemod.Item.MoonStoneItem.Do;
import com.moonstone.moonstonemod.Item.MoonStoneItem.industry;
import com.moonstone.moonstonemod.Item.MoonStoneItem.nightmare;
import com.moonstone.moonstonemod.Item.MoonStoneItem.rage;
import com.moonstone.moonstonemod.Item.cave;
import com.moonstone.moonstonemod.Item.medicine.extend.TheNecoraIC;
import com.moonstone.moonstonemod.Item.medicine.extend.medIC;
import com.moonstone.moonstonemod.TwilightForest.Loot;
import com.moonstone.moonstonemod.TwilightForest.TreeLoot;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class Evt {
    private static final Component CONTAINER_TITLE = Component.translatable("container.enderchest");

    /*
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void open(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (Handler.hasCurio(serverPlayer, InIt.mbox.get())) {
                if (Key.keyMapping_g.isDown()) {
                    serverPlayer.openMenu(new SimpleMenuProvider((p_53124_, p_53125_, p_53126_) -> {
                        PlayerEnderChestContainer playerenderchestcontainer = serverPlayer.getEnderChestInventory();
                        return ChestMenu.threeRows(p_53124_, p_53125_, playerenderchestcontainer);
                    }, CONTAINER_TITLE));
                }
            }
        }
    }

     */
    /*
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void heart(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player){

            if (Handler.hasCurio(player, InIt.soulelytra.get())) {
                if (!player.level().isClientSide) {
                    if (player.isFallFlying() && Handler.hasCurio(player, InIt.soulelytra.get())) {
                        if (Key.keyMapping_r.isDown()) {
                            player.level().addParticle(ParticleTypes.DRAGON_BREATH, true, player.getX(), player.getY(), player.getZ(), Mth.nextFloat(RandomSource.create(), 0.1f, 0.5f), Mth.nextFloat(RandomSource.create(), 0.11f, 0.51f), Mth.nextFloat(RandomSource.create(), 0.12f, 0.52f));
                            if (!player.getCooldowns().isOnCooldown(InIt.soulelytra.get())) {
                                player.setDeltaMovement(player.getDeltaMovement().x * 2, player.getDeltaMovement().y * 2, player.getDeltaMovement().z * 2);
                                player.getCooldowns().addCooldown(InIt.soulelytra.get(), 100);
                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_DRAGON_HURT, SoundSource.NEUTRAL, 0.7f, 0.7f);
                            }
                        }
                    }
                }
            }
        }
    }

     */
    public Evt(){
        MinecraftForge.EVENT_BUS.addListener(this::lootLoot4);


        MinecraftForge.EVENT_BUS.addListener(this::max_charm);

    }

    private void max_charm(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, InIt.redamout.get())
                    &&Handler.hascurio(player, InIt.blueamout.get())
                    &&Handler.hascurio(player, InIt.greedamout.get()))
            {
                if (event.getEntity() instanceof ElderGuardian elderGuardian){
                    event.getDrops().add(new ItemEntity(elderGuardian.level(), elderGuardian.getX(), elderGuardian.getY() ,elderGuardian.getZ(), new ItemStack(InIt.maxamout.get())));
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void RenderTooltipEven4t(RenderTooltipEvent.Color tooltipEvent){
        ItemStack stack = tooltipEvent.getItemStack();

        if (stack.getItem() instanceof industry){
            tooltipEvent.setBorderStart(0xFFC9E2E9);
            tooltipEvent.setBorderEnd(0xFF010303);
        }

        if (stack.getItem() instanceof cave){
            tooltipEvent.setBorderStart(0xFFFFFF00);
            tooltipEvent.setBorderEnd(0xFFFFA500);
        }
        if (stack.getItem() instanceof Do||
                stack.is(InIt.doomfruit.get())){
            tooltipEvent.setBorderStart(0xFF188A10);
            tooltipEvent.setBorderEnd(0xFFFE3000);
        }
        if (stack.is(InIt.iondeficit.get())||
                stack.is(InIt.ionsurge.get())||
                stack.is(InIt.reshuffle.get())||
                stack.is(InIt.sensitivity.get())||
                stack.is(InIt.treeorb.get())||
                stack.is(InIt.lichhead.get())||
                stack.is(InIt.liferune.get())||
                stack.is(InIt.snowrune.get())||
                stack.is(InIt.nagablock.get())||
                stack.is(InIt.nagaorb.get())||
                stack.is(InIt.nagaring.get())||
                stack.is(InIt.nagastone.get())){
            tooltipEvent.setBorderStart(0xFF006400);
            tooltipEvent.setBorderEnd(0xFF8B4513);
        }


        if (stack.getItem() instanceof TheNecoraIC
                || stack.getItem() instanceof medIC||
                stack.getItem() instanceof rage ||
                stack.is(InIt.sevencurse.get())||
                stack.is(InIt.sevenorb.get())||
                stack.is(InIt.diemug.get())||
                stack.is(InIt.evilcandle.get())||
                stack.is(InIt.evilmug.get())||
                stack.is(InIt.obsidianring.get())){
            tooltipEvent.setBorderStart(0xFFDC143C);
            tooltipEvent.setBorderEnd(0xFFDC143C);
        }


        if (stack.getItem() instanceof nightmare||
                stack.is(InIt.nightmarewater.get())){
            tooltipEvent.setBorderStart(0xFFDA70D6);
            tooltipEvent.setBorderEnd(0xFF5F9EA0);
        }
        if (stack.is(InIt.twistedbadge.get())||
                stack.is(InIt.twistedhand.get())||
                stack.is(InIt.twistedring.get())||
                stack.is(InIt.twistedrock.get())||
                stack.is(InIt.twistedstone.get())||
                stack.is(InIt.twistedyoke.get())||
                stack.is(InIt.twstone.get()))
        {
            tooltipEvent.setBorderStart(0xFFDC143C);
            tooltipEvent.setBorderEnd(0xFF8B4513);
        }
        if (stack.is(InIt.ectoplasmapple.get())||
                stack.is(InIt.ectoplasmbadge.get())||
                stack.is(InIt.ectoplasmball.get())||
                stack.is(InIt.ectoplasmbattery.get())||
                stack.is(InIt.ectoplasmcloub.get())||
                stack.is(InIt.ectoplasmcube.get())||
                stack.is(InIt.ectoplasmhorseshoe.get())||
                stack.is(InIt.ectoplasmprism.get())||
                stack.is(InIt.ectoplasmslate.get())||
                stack.is(InIt.ectoplasmstone.get())) {
            tooltipEvent.setBorderStart(0xFF87CEFA);
            tooltipEvent.setBorderEnd(0xFFF8F8FF);
        }
        if (stack.is(InIt.mbattery.get())||
                stack.is(InIt.mblock.get())||
                stack.is(InIt.mbottle.get())||
                stack.is(InIt.mbox.get())||
                stack.is(InIt.meye.get())||
                stack.is(InIt.mkidney.get())||
                stack.is(InIt.morb.get())||
                stack.is(InIt.mring.get())||
                stack.is(InIt.mshell.get())) {
            tooltipEvent.setBorderStart(0xFF006400);
            tooltipEvent.setBorderEnd(0xFF006400);
        }
        if (stack.is(InIt.blackhead.get())||
                stack.is(InIt.fortunecrystal.get())||
                stack.is(InIt.mayhemcrystal.get())||
                stack.is(InIt.nanocube.get())||
                stack.is(InIt.treasure.get())||
                stack.is(InIt.timegold.get())||
                stack.is(InIt.maxamout.get())||
                stack.is(InIt.warcharm.get())) {
            tooltipEvent.setBorderStart(0xFFFFFF00);
            tooltipEvent.setBorderEnd(0xFFFFA500);
        }

        if (stack.is(InIt.deerbadge.get())||
                stack.is(InIt.goldbook.get())||
                stack.is(InIt.undead.get())||
                stack.is(InIt.goldstone.get())) {
            tooltipEvent.setBorderStart(0xFFFFFF00);
            tooltipEvent.setBorderEnd(0xFFFFA500);
        }
    }
    private void lootLoot4(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
            helper.register(new ResourceLocation(MoonstoneMod.MODID, "bastion_treasure"), bastion_treasure.CODEC);
            helper.register(new ResourceLocation(MoonstoneMod.MODID, "abandoned_mineshaft"), abandoned_mineshaft.CODEC);
            helper.register(new ResourceLocation(MoonstoneMod.MODID, "ancient_city"), ancient_city.CODEC);
            helper.register(new ResourceLocation(MoonstoneMod.MODID, "goldbook"), goldbook.CODEC);
            helper.register(new ResourceLocation(MoonstoneMod.MODID, "simple_dungeon"), simple_dungeon.CODEC);

            helper.register(new ResourceLocation(MoonstoneMod.MODID, "moonstone_tf_loot"), Loot.CODEC);
            helper.register(new ResourceLocation(MoonstoneMod.MODID, "moonstone_tf_tree_loot"), TreeLoot.CODEC);
            helper.register(new ResourceLocation(MoonstoneMod.MODID, "end_city"), end_city.CODEC);

            helper.register(new ResourceLocation(MoonstoneMod.MODID, "conchloot"), conchloot.CODEC);
        });
    }

}
