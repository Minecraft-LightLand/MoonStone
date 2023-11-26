package com.moonstone.moonstonemod.init;


import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class Tab {

	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoonstoneMod.MODID);
	public static final RegistryObject<CreativeModeTab> ITEM = TABS.register("moonstone_item", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(Init.ectoplasmball.get()))
			.title(Component.nullToEmpty("moonstone_tab"))
			.displayItems((a, b) -> {
				b.accept(new ItemStack(Init.soulbook.get()));

				b.accept(new ItemStack(Init.sevencurse.get()));
				b.accept(new ItemStack(Init.maxamout.get()));
				b.accept(new ItemStack(Init.warcharm.get()));
				b.accept(new ItemStack(Init.blackhead.get()));


				b.accept(new ItemStack(Init.ectoplasmstone.get()));
				b.accept(new ItemStack(Init.goldstone.get()));
				b.accept(new ItemStack(Init.twistedstone.get()));
				b.accept(new ItemStack(Init.battery.get()));
				b.accept(new ItemStack(Init.blackeorb.get()));
				b.accept(new ItemStack(Init.whiteorb.get()));
				b.accept(new ItemStack(Init.soulbattery.get()));


				b.accept(new ItemStack(Init.blessingseye.get()));
				b.accept(new ItemStack(Init.plague.get()));
				b.accept(new ItemStack(Init.soulapple.get()));
				b.accept(new ItemStack(Init.soulelytra.get()));

				b.accept(new ItemStack(Init.ectoplasmball.get()));
				b.accept(new ItemStack(Init.ectoplasmapple.get()));
				b.accept(new ItemStack(Init.ectoplasmbadge.get()));
				b.accept(new ItemStack(Init.ectoplasmcloub.get()));
				b.accept(new ItemStack(Init.ectoplasmcube.get()));
				b.accept(new ItemStack(Init.ectoplasmhorseshoe.get()));
				b.accept(new ItemStack(Init.ectoplasmslate.get()));
				b.accept(new ItemStack(Init.ectoplasmprism.get()));
				b.accept(new ItemStack(Init.ectoplasmbattery.get()));


				b.accept(new ItemStack(Init.mring.get()));
				b.accept(new ItemStack(Init.morb.get()));
				b.accept(new ItemStack(Init.mshell.get()));
				b.accept(new ItemStack(Init.mblock.get()));
				b.accept(new ItemStack(Init.mbottle.get()));
				b.accept(new ItemStack(Init.mbox.get()));
				b.accept(new ItemStack(Init.mbattery.get()));
				b.accept(new ItemStack(Init.mkidney.get()));
				b.accept(new ItemStack(Init.meye.get()));


				b.accept(new ItemStack(Init.badgeofthedead.get()));
				b.accept(new ItemStack(Init.diamondhorseshoe.get()));
				b.accept(new ItemStack(Init.goldhorseshoe.get()));
				b.accept(new ItemStack(Init.ironhorseshoe.get()));
				b.accept(new ItemStack(Init.superhorseshoe.get()));
				b.accept(new ItemStack(Init.woodenhorseshoe.get()));
				b.accept(new ItemStack(Init.greedcrystal.get()));
				b.accept(new ItemStack(Init.warcrystal.get()));
				b.accept(new ItemStack(Init.biggreedcrystal.get()));
				b.accept(new ItemStack(Init.bigwarcrystal.get()));
				b.accept(new ItemStack(Init.fortunecrystal.get()));
				b.accept(new ItemStack(Init.mayhemcrystal.get()));
				b.accept(new ItemStack(Init.slate.get()));
				b.accept(new ItemStack(Init.bigslate.get()));
				b.accept(new ItemStack(Init.slatecube.get()));


				b.accept(new ItemStack(Init.soulcube.get()));
				b.accept(new ItemStack(Init.soul.get()));
				b.accept(new ItemStack(Init.soulball.get()));
				b.accept(new ItemStack(Init.soulcloub.get()));

				b.accept(new ItemStack(Init.twistedyoke.get()));
				b.accept(new ItemStack(Init.twistedrock.get()));
				b.accept(new ItemStack(Init.twistedbadge.get()));
				b.accept(new ItemStack(Init.twistedring.get()));
				b.accept(new ItemStack(Init.twistedbadge.get()));
				b.accept(new ItemStack(Init.twistedhand.get()));
				b.accept(new ItemStack(Init.twstone.get()));

				b.accept(new ItemStack(Init.goldbook.get()));
				b.accept(new ItemStack(Init.undead.get()));
				b.accept(new ItemStack(Init.deerbadge.get()));

				b.accept(new ItemStack(Init.redamout.get()));
				b.accept(new ItemStack(Init.blueamout.get()));
				b.accept(new ItemStack(Init.greedamout.get()));

				b.accept(new ItemStack(Init.decayedstone.get()));
				b.accept(new ItemStack(Init.treasure.get()));
				b.accept(new ItemStack(Init.nanorobot.get()));
				b.accept(new ItemStack(Init.nanocube.get()));

				b.accept(new ItemStack(Init.hurtring.get()));

				b.accept(new ItemStack(Init.hellbattery.get()));
				b.accept(new ItemStack(Init.evilcandle.get()));
				b.accept(new ItemStack(Init.evilmug.get()));
				b.accept(new ItemStack(Init.obsidianring.get()));
				b.accept(new ItemStack(Init.diemug.get()));

				b.accept(new ItemStack(Init.medicinebox.get()));
				b.accept(new ItemStack(Init.polyphagia.get()));
				b.accept(new ItemStack(Init.quadriceps.get()));
				b.accept(new ItemStack(Init.masticatory.get()));
				b.accept(new ItemStack(Init.calcification.get()));
				b.accept(new ItemStack(Init.reanimation.get()));
				b.accept(new ItemStack(Init.necora.get()));
				b.accept(new ItemStack(Init.apple.get()));

				b.accept(new ItemStack(Init.atpoverdose.get()));
				b.accept(new ItemStack(Init.autolytic.get()));
				b.accept(new ItemStack(Init.putrefactive.get()));
				b.accept(new ItemStack(Init.ambush.get()));
				b.accept(new ItemStack(Init.fermentation.get()));
				b.accept(new ItemStack(Init.regenerative.get()));

				b.accept(new ItemStack(Init.waterdnarune.get()));
				b.accept(new ItemStack(Init.airdnarune.get()));


				b.accept(new ItemStack(Init.nagablock.get()));
				b.accept(new ItemStack(Init.nagastone.get()));
				b.accept(new ItemStack(Init.nagaring.get()));
				b.accept(new ItemStack(Init.nagaorb.get()));

				b.accept(new ItemStack(Init.lichhead.get()));
				b.accept(new ItemStack(Init.liferune.get()));
				b.accept(new ItemStack(Init.snowrune.get()));
				b.accept(new ItemStack(Init.blackrune.get()));
				b.accept(new ItemStack(Init.gammaeye.get()));

				b.accept(new ItemStack(Init.reshuffle.get()));
				b.accept(new ItemStack(Init.sensitivity.get()));
				b.accept(new ItemStack(Init.iondeficit.get()));
				b.accept(new ItemStack(Init.ionsurge.get()));
				b.accept(new ItemStack(Init.treeorb.get()));

				b.accept(new ItemStack(Init.timegold.get()));
				b.accept(new ItemStack(Init.nightmareeye.get()));
				b.accept(new ItemStack(Init.nightmareorb.get()));
				b.accept(new ItemStack(Init.nightmarestone.get()));

				b.accept(new ItemStack(Init.nightmarewater.get()));
				b.accept(new ItemStack(Init.nightmaremoai.get()));
				b.accept(new ItemStack(Init.nightmarecharm.get()));
				b.accept(new ItemStack(Init.nightmaretreasure.get()));
				b.accept(new ItemStack(Init.nightmareanchor.get()));

				b.accept(new ItemStack(Init.rageapple.get()));
				b.accept(new ItemStack(Init.rageeye.get()));
				b.accept(new ItemStack(Init.ragepear.get()));
				b.accept(new ItemStack(Init.ragecharm.get()));
				b.accept(new ItemStack(Init.ragebox.get()));
				b.accept(new ItemStack(Init.DNADish.get()));

				b.accept(new ItemStack(Init.doomstone.get()));
				b.accept(new ItemStack(Init.doomfruit.get()));

				b.accept(new ItemStack(Init.tp.get()));
				b.accept(new ItemStack(Init.conch.get()));
				b.accept(new ItemStack(Init.irradiatedorb.get()));

				b.accept(new ItemStack(Init.industrybox.get()));
				b.accept(new ItemStack(Init.industryeye.get()));
				b.accept(new ItemStack(Init.temperatureeye.get()));
				b.accept(new ItemStack(Init.endhead.get()));

			})
			.build());
}


