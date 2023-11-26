package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.content.TimeParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = MoonstoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class Reg {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES;
	public static final RegistryObject<SimpleParticleType> TIME;

	static {
		PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MoonstoneMod.MODID);
		TIME = PARTICLE_TYPES.register("time", () -> {
			return new SimpleParticleType(false);
		});

	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(TIME.get(), TimeParticle.Provider::new);

	}
}
