package com.moonstone.moonstonemod;

import com.mojang.logging.LogUtils;
import com.moonstone.moonstonemod.Item.ML_Randerer.CuriosLayerDefinitions;
import com.moonstone.moonstonemod.Item.ML_Randerer.ML.*;
import com.moonstone.moonstonemod.Item.ML_Randerer.Renderer.*;
import com.moonstone.moonstonemod.Item.MSLoot.*;
import com.moonstone.moonstonemod.Item.key.Key;
import com.moonstone.moonstonemod.Particle.Reg;
import com.moonstone.moonstonemod.TwilightForest.Loot;
import com.moonstone.moonstonemod.TwilightForest.TreeLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import com.moonstone.moonstonemod.Cave.LOOT.*;
import java.awt.*;


@Mod(MoonstoneMod.MODID)
public class MoonstoneMod {
    public static final String MODID = "moonstone";
    private static final Logger LOGGER = LogUtils.getLogger();
    public MoonstoneMod() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        InIt.REGISTRY.register(modEventBus);
        tab.TABS.register(modEventBus);
        modEventBus.addListener(this::registerLayers);
        modEventBus.addListener(this::clientSetup);

        Reg.PARTICLE_TYPES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new Evt());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    /*
    private void enqueue(InterModEnqueueEvent evt){
        InterModComms.sendTo(CuriosApi.MODID, "register_type",
                () -> SlotTypePreset.CHARM.getMessageBuilder().size(2).build());
        InterModComms.sendTo(CuriosApi.MODID, "register_type",
                () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo(CuriosApi.MODID, "register_type",
                () -> SlotTypePreset.RING.getMessageBuilder().build());
        InterModComms.sendTo(CuriosApi.MODID, "register_type",
                () -> SlotTypePreset.BELT.getMessageBuilder().build());
        InterModComms.sendTo(CuriosApi.MODID, "register_type",
                () -> SlotTypePreset.BACK.getMessageBuilder().build());
        InterModComms.sendTo(CuriosApi.MODID, "register_type",
                () -> SlotTypePreset.CURIO.getMessageBuilder().size(0).build());
        InterModComms.sendTo(CuriosApi.MODID, "register_type",
                () -> (new SlotTypeMessage.Builder("the_virus").size(0).icon(new ResourceLocation("item/empty_armor_slot_shield"))).build());
        InterModComms.sendTo(CuriosApi.MODID, "register_type",
                () -> (new SlotTypeMessage.Builder("virus").size(0).icon(new ResourceLocation("item/empty_armor_slot_shield"))).build());

    }

     */


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void lootLoot1(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
            helper.register(new ResourceLocation(MODID, "bastion_treasure"), bastion_treasure.CODEC);
            helper.register(new ResourceLocation(MODID, "abandoned_mineshaft"), abandoned_mineshaft.CODEC);
            helper.register(new ResourceLocation(MODID, "ancient_city"), ancient_city.CODEC);
            helper.register(new ResourceLocation(MODID, "goldbook"), goldbook.CODEC);
            helper.register(new ResourceLocation(MODID, "simple_dungeon"), simple_dungeon.CODEC);
            helper.register(new ResourceLocation(MoonstoneMod.MODID, "end_city"), end_city.CODEC);

            helper.register(new ResourceLocation(MODID, "conchloot"), conchloot.CODEC);


        });
    }

    /*
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void RenderTooltipEvent1(RenderTooltipEvent.Color tooltipEvent){
        ItemStack stack = tooltipEvent.getItemStack();
        if (stack.is(InIt.nightmareeye.get())){
            tooltipEvent.setBorderStart(0x000000ff);
            tooltipEvent.setBorderEnd(0x00c000c0);

        }
    }


     */

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class asd {

        /*
        @SubscribeEvent
        public static void RenderTooltipEvent2(RenderTooltipEvent.Color tooltipEvent){
            ItemStack stack = tooltipEvent.getItemStack();
            if (stack.is(InIt.nightmareeye.get())){
                tooltipEvent.setBorderStart(0x000000ff);
                tooltipEvent.setBorderEnd(0x00c000c0);

            }
        }

         */
        @SubscribeEvent
        public static void lootLoot2(RegisterEvent event) {
            event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
                helper.register(new ResourceLocation(MODID, "bastion_treasure"), bastion_treasure.CODEC);
                helper.register(new ResourceLocation(MODID, "abandoned_mineshaft"), abandoned_mineshaft.CODEC);
                helper.register(new ResourceLocation(MODID, "ancient_city"), ancient_city.CODEC);
                helper.register(new ResourceLocation(MODID, "goldbook"), goldbook.CODEC);
                helper.register(new ResourceLocation(MODID, "simple_dungeon"), simple_dungeon.CODEC);

                helper.register(new ResourceLocation(MODID, "moonstone_tf_loot"), Loot.CODEC);
                helper.register(new ResourceLocation(MoonstoneMod.MODID, "moonstone_tf_tree_loot"), TreeLoot.CODEC);
                helper.register(new ResourceLocation(MoonstoneMod.MODID, "end_city"), end_city.CODEC);


                helper.register(new ResourceLocation(MoonstoneMod.MODID, "conchloot"), conchloot.CODEC);

            });
        }
    }


    private void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(CuriosLayerDefinitions.abysscrown, abysscrownML::createLayer);
        evt.registerLayerDefinition(CuriosLayerDefinitions.mbox, mboxML::createLayer);
        evt.registerLayerDefinition(CuriosLayerDefinitions.soulcube, soulcubeML::createLayer);
        evt.registerLayerDefinition(CuriosLayerDefinitions.slatecube, slatecubeML::createLayer);
        evt.registerLayerDefinition(CuriosLayerDefinitions.ragebox, rageboxML::createLayer);
        evt.registerLayerDefinition(CuriosLayerDefinitions.industrybox, industryboxML::createLayer);

    }

    private void clientSetup(final FMLClientSetupEvent evt) {

        CuriosRendererRegistry.register(InIt.mbox.get(), mboxRanderer::new);
        CuriosRendererRegistry.register(InIt.soulcube.get(), soulcubeRanderer::new);
        CuriosRendererRegistry.register(InIt.slatecube.get(), slatecubeRanderer::new);

        CuriosRendererRegistry.register(InIt.ragebox.get(), rageboxRanderer::new);
        CuriosRendererRegistry.register(InIt.industrybox.get(), industryboxRanerer::new);

    }


    @Mod.EventBusSubscriber(modid = "moonstone", value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Client {
        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent evt) {
            evt.register(Key.keyMapping_g);
            evt.register(Key.keyMapping_r);
        }

        /*
        @SubscribeEvent
        public static void RenderTooltipEvent3(RenderTooltipEvent.Color tooltipEvent){
            ItemStack stack = tooltipEvent.getItemStack();
            if (stack.is(InIt.nightmareeye.get())){
                tooltipEvent.setBorderStart(0x000000ff);
                tooltipEvent.setBorderEnd(0x00c000c0);

            }
        }

         */
        @SubscribeEvent
        public static void lootLoot3(RegisterEvent event) {
            event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
                helper.register(new ResourceLocation(MODID, "bastion_treasure"), bastion_treasure.CODEC);
                helper.register(new ResourceLocation(MODID, "abandoned_mineshaft"), abandoned_mineshaft.CODEC);
                helper.register(new ResourceLocation(MODID, "ancient_city"), ancient_city.CODEC);
                helper.register(new ResourceLocation(MODID, "goldbook"), goldbook.CODEC);
                helper.register(new ResourceLocation(MODID, "simple_dungeon"), simple_dungeon.CODEC);
                helper.register(new ResourceLocation(MODID, "moonstone_tf_loot"), Loot.CODEC);
                helper.register(new ResourceLocation(MoonstoneMod.MODID, "moonstone_tf_tree_loot"), TreeLoot.CODEC);

                helper.register(new ResourceLocation(MoonstoneMod.MODID, "end_city"), end_city.CODEC);


                helper.register(new ResourceLocation(MoonstoneMod.MODID, "conchloot"), conchloot.CODEC);
            });
        }


    }


}
