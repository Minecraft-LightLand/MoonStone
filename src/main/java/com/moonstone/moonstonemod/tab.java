package com.moonstone.moonstonemod;


import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class tab {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoonstoneMod.MODID);
    public static final RegistryObject<CreativeModeTab> ITEM = TABS.register("moonstone_item",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(InIt.ectoplasmball.get()))
            .title(Component.nullToEmpty("moonstone_tab"))
            .displayItems((a,b)->{
                b.accept(new ItemStack(InIt.soulbook.get()));

                b.accept(new ItemStack(InIt.sevencurse.get()));
                b.accept(new ItemStack(InIt.maxamout.get()));
                b.accept(new ItemStack(InIt.warcharm.get()));
                b.accept(new ItemStack(InIt.blackhead.get()));


                b.accept(new ItemStack(InIt.ectoplasmstone.get()));
                b.accept(new ItemStack(InIt.goldstone.get()));
                b.accept(new ItemStack(InIt.twistedstone.get()));
                b.accept(new ItemStack(InIt.battery.get()));
                b.accept(new ItemStack(InIt.blackeorb.get()));
                b.accept(new ItemStack(InIt.whiteorb.get()));
                b.accept(new ItemStack(InIt.soulbattery.get()));


                b.accept(new ItemStack(InIt.blessingseye.get()));
                b.accept(new ItemStack(InIt.plague.get()));
                b.accept(new ItemStack(InIt.soulapple.get()));
                b.accept(new ItemStack(InIt.soulelytra.get()));

                b.accept(new ItemStack(InIt.ectoplasmball.get()));
                b.accept(new ItemStack(InIt.ectoplasmapple.get()));
                b.accept(new ItemStack(InIt.ectoplasmbadge.get()));
                b.accept(new ItemStack(InIt.ectoplasmcloub.get()));
                b.accept(new ItemStack(InIt.ectoplasmcube.get()));
                b.accept(new ItemStack(InIt.ectoplasmhorseshoe.get()));
                b.accept(new ItemStack(InIt.ectoplasmslate.get()));
                b.accept(new ItemStack(InIt.ectoplasmprism.get()));
                b.accept(new ItemStack(InIt.ectoplasmbattery.get()));


                b.accept(new ItemStack(InIt.mring.get()));
                b.accept(new ItemStack(InIt.morb.get()));
                b.accept(new ItemStack(InIt.mshell.get()));
                b.accept(new ItemStack(InIt.mblock.get()));
                b.accept(new ItemStack(InIt.mbottle.get()));
                b.accept(new ItemStack(InIt.mbox.get()));
                b.accept(new ItemStack(InIt.mbattery.get()));
                b.accept(new ItemStack(InIt.mkidney.get()));
                b.accept(new ItemStack(InIt.meye.get()));


                b.accept(new ItemStack(InIt.badgeofthedead.get()));
                b.accept(new ItemStack(InIt.diamondhorseshoe.get()));
                b.accept(new ItemStack(InIt.goldhorseshoe.get()));
                b.accept(new ItemStack(InIt.ironhorseshoe.get()));
                b.accept(new ItemStack(InIt.superhorseshoe.get()));
                b.accept(new ItemStack(InIt.woodenhorseshoe.get()));
                b.accept(new ItemStack(InIt.greedcrystal.get()));
                b.accept(new ItemStack(InIt.warcrystal.get()));
                b.accept(new ItemStack(InIt.biggreedcrystal.get()));
                b.accept(new ItemStack(InIt.bigwarcrystal.get()));
                b.accept(new ItemStack(InIt.fortunecrystal.get()));
                b.accept(new ItemStack(InIt.mayhemcrystal.get()));
                b.accept(new ItemStack(InIt.slate.get()));
                b.accept(new ItemStack(InIt.bigslate.get()));
                b.accept(new ItemStack(InIt.slatecube.get()));


                b.accept(new ItemStack(InIt.soulcube.get()));
                b.accept(new ItemStack(InIt.soul.get()));
                b.accept(new ItemStack(InIt.soulball.get()));
                b.accept(new ItemStack(InIt.soulcloub.get()));

                b.accept(new ItemStack(InIt.twistedyoke.get()));
                b.accept(new ItemStack(InIt.twistedrock.get()));
                b.accept(new ItemStack(InIt.twistedbadge.get()));
                b.accept(new ItemStack(InIt.twistedring.get()));
                b.accept(new ItemStack(InIt.twistedbadge.get()));
                b.accept(new ItemStack(InIt.twistedhand.get()));
                b.accept(new ItemStack(InIt.twstone.get()));

                b.accept(new ItemStack(InIt.goldbook.get()));
                b.accept(new ItemStack(InIt.undead.get()));
                b.accept(new ItemStack(InIt.deerbadge.get()));

                b.accept(new ItemStack(InIt.redamout.get()));
                b.accept(new ItemStack(InIt.blueamout.get()));
                b.accept(new ItemStack(InIt.greedamout.get()));

                b.accept(new ItemStack(InIt.decayedstone.get()));
                b.accept(new ItemStack(InIt.treasure.get()));
                b.accept(new ItemStack(InIt.nanorobot.get()));
                b.accept(new ItemStack(InIt.nanocube.get()));

                b.accept(new ItemStack(InIt.hurtring.get()));

                b.accept(new ItemStack(InIt.hellbattery.get()));
                b.accept(new ItemStack(InIt.evilcandle.get()));
                b.accept(new ItemStack(InIt.evilmug.get()));
                b.accept(new ItemStack(InIt.obsidianring.get()));
                b.accept(new ItemStack(InIt.diemug.get()));

                b.accept(new ItemStack(InIt.medicinebox.get()));
                b.accept(new ItemStack(InIt.polyphagia.get()));
                b.accept(new ItemStack(InIt.quadriceps.get()));
                b.accept(new ItemStack(InIt.masticatory.get()));
                b.accept(new ItemStack(InIt.calcification.get()));
                b.accept(new ItemStack(InIt.reanimation.get()));
                b.accept(new ItemStack(InIt.necora.get()));
                b.accept(new ItemStack(InIt.apple.get()));

                b.accept(new ItemStack(InIt.atpoverdose.get()));
                b.accept(new ItemStack(InIt.autolytic.get()));
                b.accept(new ItemStack(InIt.putrefactive.get()));
                b.accept(new ItemStack(InIt.ambush.get()));
                b.accept(new ItemStack(InIt.fermentation.get()));
                b.accept(new ItemStack(InIt.regenerative.get()));

                b.accept(new ItemStack(InIt.waterdnarune.get()));
                b.accept(new ItemStack(InIt.airdnarune.get()));


                b.accept(new ItemStack(InIt.nagablock.get()));
                b.accept(new ItemStack(InIt.nagastone.get()));
                b.accept(new ItemStack(InIt.nagaring.get()));
                b.accept(new ItemStack(InIt.nagaorb.get()));

                b.accept(new ItemStack(InIt.lichhead.get()));
                b.accept(new ItemStack(InIt.liferune.get()));
                b.accept(new ItemStack(InIt.snowrune.get()));
                b.accept(new ItemStack(InIt.blackrune.get()));
                b.accept(new ItemStack(InIt.gammaeye.get()));

                b.accept(new ItemStack(InIt.reshuffle.get()));
                b.accept(new ItemStack(InIt.sensitivity.get()));
                b.accept(new ItemStack(InIt.iondeficit.get()));
                b.accept(new ItemStack(InIt.ionsurge.get()));
                b.accept(new ItemStack(InIt.treeorb.get()));

                b.accept(new ItemStack(InIt.timegold.get()));
                b.accept(new ItemStack(InIt.nightmareeye.get()));
                b.accept(new ItemStack(InIt.nightmareorb.get()));
                b.accept(new ItemStack(InIt.nightmarestone.get()));

                b.accept(new ItemStack(InIt.nightmarewater.get()));
                b.accept(new ItemStack(InIt.nightmaremoai.get()));
                b.accept(new ItemStack(InIt.nightmarecharm.get()));
                b.accept(new ItemStack(InIt.nightmaretreasure.get()));
                b.accept(new ItemStack(InIt.nightmareanchor.get()));

                b.accept(new ItemStack(InIt.rageapple.get()));
                b.accept(new ItemStack(InIt.rageeye.get()));
                b.accept(new ItemStack(InIt.ragepear.get()));
                b.accept(new ItemStack(InIt.ragecharm.get()));
                b.accept(new ItemStack(InIt.ragebox.get()));
                b.accept(new ItemStack(InIt.DNADish.get()));

                b.accept(new ItemStack(InIt.doomstone.get()));
                b.accept(new ItemStack(InIt.doomfruit.get()));

                b.accept(new ItemStack(InIt.tp.get()));
                b.accept(new ItemStack(InIt.conch.get()));
                b.accept(new ItemStack(InIt.irradiatedorb.get()));

                b.accept(new ItemStack(InIt.industrybox.get()));
                b.accept(new ItemStack(InIt.industryeye.get()));
                b.accept(new ItemStack(InIt.temperatureeye.get()));
                b.accept(new ItemStack(InIt.endhead.get()));

            })
            .build());
}


