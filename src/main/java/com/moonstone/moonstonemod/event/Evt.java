package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.compat.alexcave.conchloot;
import com.moonstone.moonstonemod.compat.twilightforest.base.Loot;
import com.moonstone.moonstonemod.compat.twilightforest.base.TreeLoot;
import com.moonstone.moonstonemod.content.item.CaveItem;
import com.moonstone.moonstonemod.content.item.MSLoot.*;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.Do;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.Industry;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.Nightmare;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.Rage;
import com.moonstone.moonstonemod.content.item.medicine.extend.TheNecoraIC;
import com.moonstone.moonstonemod.content.item.medicine.extend.medIC;
import com.moonstone.moonstonemod.init.Init;
import com.moonstone.moonstonemod.init.MoonstoneMod;
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
	public Evt() {
		MinecraftForge.EVENT_BUS.addListener(this::lootLoot4);


		MinecraftForge.EVENT_BUS.addListener(this::max_charm);

	}

	private void max_charm(LivingDropsEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, Init.redamout.get())
					&& CuriosHandler.hascurio(player, Init.blueamout.get())
					&& CuriosHandler.hascurio(player, Init.greedamout.get())) {
				if (event.getEntity() instanceof ElderGuardian elderGuardian) {
					event.getDrops().add(new ItemEntity(elderGuardian.level(), elderGuardian.getX(), elderGuardian.getY(), elderGuardian.getZ(), new ItemStack(Init.maxamout.get())));
				}
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void RenderTooltipEven4t(RenderTooltipEvent.Color tooltipEvent) {
		ItemStack stack = tooltipEvent.getItemStack();

		if (stack.getItem() instanceof Industry) {
			tooltipEvent.setBorderStart(0xFFC9E2E9);
			tooltipEvent.setBorderEnd(0xFF010303);
		}

		if (stack.getItem() instanceof CaveItem) {
			tooltipEvent.setBorderStart(0xFFFFFF00);
			tooltipEvent.setBorderEnd(0xFFFFA500);
		}
		if (stack.getItem() instanceof Do ||
				stack.is(Init.doomfruit.get())) {
			tooltipEvent.setBorderStart(0xFF188A10);
			tooltipEvent.setBorderEnd(0xFFFE3000);
		}
		if (stack.is(Init.iondeficit.get()) ||
				stack.is(Init.ionsurge.get()) ||
				stack.is(Init.reshuffle.get()) ||
				stack.is(Init.sensitivity.get()) ||
				stack.is(Init.treeorb.get()) ||
				stack.is(Init.lichhead.get()) ||
				stack.is(Init.liferune.get()) ||
				stack.is(Init.snowrune.get()) ||
				stack.is(Init.nagablock.get()) ||
				stack.is(Init.nagaorb.get()) ||
				stack.is(Init.nagaring.get()) ||
				stack.is(Init.nagastone.get())) {
			tooltipEvent.setBorderStart(0xFF006400);
			tooltipEvent.setBorderEnd(0xFF8B4513);
		}


		if (stack.getItem() instanceof TheNecoraIC
				|| stack.getItem() instanceof medIC ||
				stack.getItem() instanceof Rage ||
				stack.is(Init.sevencurse.get()) ||
				stack.is(Init.sevenorb.get()) ||
				stack.is(Init.diemug.get()) ||
				stack.is(Init.evilcandle.get()) ||
				stack.is(Init.evilmug.get()) ||
				stack.is(Init.obsidianring.get())) {
			tooltipEvent.setBorderStart(0xFFDC143C);
			tooltipEvent.setBorderEnd(0xFFDC143C);
		}


		if (stack.getItem() instanceof Nightmare ||
				stack.is(Init.nightmarewater.get())) {
			tooltipEvent.setBorderStart(0xFFDA70D6);
			tooltipEvent.setBorderEnd(0xFF5F9EA0);
		}
		if (stack.is(Init.twistedbadge.get()) ||
				stack.is(Init.twistedhand.get()) ||
				stack.is(Init.twistedring.get()) ||
				stack.is(Init.twistedrock.get()) ||
				stack.is(Init.twistedstone.get()) ||
				stack.is(Init.twistedyoke.get()) ||
				stack.is(Init.twstone.get())) {
			tooltipEvent.setBorderStart(0xFFDC143C);
			tooltipEvent.setBorderEnd(0xFF8B4513);
		}
		if (stack.is(Init.ectoplasmapple.get()) ||
				stack.is(Init.ectoplasmbadge.get()) ||
				stack.is(Init.ectoplasmball.get()) ||
				stack.is(Init.ectoplasmbattery.get()) ||
				stack.is(Init.ectoplasmcloub.get()) ||
				stack.is(Init.ectoplasmcube.get()) ||
				stack.is(Init.ectoplasmhorseshoe.get()) ||
				stack.is(Init.ectoplasmprism.get()) ||
				stack.is(Init.ectoplasmslate.get()) ||
				stack.is(Init.ectoplasmstone.get())) {
			tooltipEvent.setBorderStart(0xFF87CEFA);
			tooltipEvent.setBorderEnd(0xFFF8F8FF);
		}
		if (stack.is(Init.mbattery.get()) ||
				stack.is(Init.mblock.get()) ||
				stack.is(Init.mbottle.get()) ||
				stack.is(Init.mbox.get()) ||
				stack.is(Init.meye.get()) ||
				stack.is(Init.mkidney.get()) ||
				stack.is(Init.morb.get()) ||
				stack.is(Init.mring.get()) ||
				stack.is(Init.mshell.get())) {
			tooltipEvent.setBorderStart(0xFF006400);
			tooltipEvent.setBorderEnd(0xFF006400);
		}
		if (stack.is(Init.blackhead.get()) ||
				stack.is(Init.fortunecrystal.get()) ||
				stack.is(Init.mayhemcrystal.get()) ||
				stack.is(Init.nanocube.get()) ||
				stack.is(Init.treasure.get()) ||
				stack.is(Init.timegold.get()) ||
				stack.is(Init.maxamout.get()) ||
				stack.is(Init.warcharm.get())) {
			tooltipEvent.setBorderStart(0xFFFFFF00);
			tooltipEvent.setBorderEnd(0xFFFFA500);
		}

		if (stack.is(Init.deerbadge.get()) ||
				stack.is(Init.goldbook.get()) ||
				stack.is(Init.undead.get()) ||
				stack.is(Init.goldstone.get())) {
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
