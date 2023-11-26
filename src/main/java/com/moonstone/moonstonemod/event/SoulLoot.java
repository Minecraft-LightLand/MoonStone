package com.moonstone.moonstonemod.event;

import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class SoulLoot extends Item {
	public SoulLoot() {
		super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::zom);
		MinecraftForge.EVENT_BUS.addListener(this::skeleton);
		MinecraftForge.EVENT_BUS.addListener(this::wither_skeleton);
		MinecraftForge.EVENT_BUS.addListener(this::horse);

		MinecraftForge.EVENT_BUS.addListener(this::waterzom);

	}

	private void waterzom(LivingDropsEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {


			if (ModList.get().isLoaded("alexscaves")) {


				if (event.getEntity().getType() == ACEntityRegistry.NUCLEEPER.get()) {
					Random random = new Random();
					if (random.nextInt(100) <= 20) {
						event.getDrops().add(new ItemEntity(player.level(),
								player.getX(), player.getY(), player.getZ(),
								new ItemStack(Init.irradiatedorb.get())));
					}
				}


			}

			if (ModList.get().isLoaded("cataclysm")) {
				Random random = new Random();

				EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation("cataclysm:ignis"));
				if (type != null) {
					if (event.getEntity().getType() == type) {
						event.getDrops().add(new ItemEntity(event.getEntity().level(),
								event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
								new ItemStack(Init.timegold.get())));
					}
				}
			}
			//``````````````````````````````````````````
			if (CuriosHandler.hascurio(player, Init.medicinebox.get())) {
				Random random = new Random();
				if (event.getEntity() instanceof Drowned drowned) {
					if (random.nextInt(100) <= 5) {
						event.getDrops().add(new ItemEntity(drowned.level(),
								drowned.getX(), drowned.getY(), drowned.getZ(),
								new ItemStack(Init.waterdnarune.get())));
					}
				}
			}
			//``````````````````````````````````````````
			if (CuriosHandler.hascurio(player, Init.nightmareeye.get())) {
				if (event.getEntity() instanceof Evoker evoker) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.nightmaremoai.get())));
				}

				if (event.getEntity() instanceof ElderGuardian elderGuardian) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.nightmarecharm.get())));
				}


				if (event.getEntity() instanceof Warden warden) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.nightmareanchor.get())));
				}


			}


			if (CuriosHandler.hascurio(player, Init.necora.get())) {
				Random random = new Random();


				if (event.getEntity() instanceof Drowned drowned) {
					if (random.nextInt(100) < 4) {
						event.getDrops().add(new ItemEntity(drowned.level(),
								drowned.getX(), drowned.getY(), drowned.getZ(),
								new ItemStack(Init.autolytic.get())));
					}
					if (random.nextInt(100) < 3) {
						event.getDrops().add(new ItemEntity(drowned.level(),
								drowned.getX(), drowned.getY(), drowned.getZ(),
								new ItemStack(Init.ambush.get())));
					}
				}

				if (event.getEntity() instanceof Zombie zombie) {
					if (random.nextInt(100) == 1) {
						event.getDrops().add(new ItemEntity(zombie.level(),
								zombie.getX(), zombie.getY(), zombie.getZ(),
								new ItemStack(Init.atpoverdose.get())));
					}
				}

				if (event.getEntity() instanceof Skeleton skeleton) {
					if (random.nextInt(100) < 3) {
						event.getDrops().add(new ItemEntity(skeleton.level(),
								skeleton.getX(), skeleton.getY(), skeleton.getZ(),
								new ItemStack(Init.putrefactive.get())));
					}
				}

				if (event.getEntity() instanceof Pillager pillager) {
					if (random.nextInt(100) < 5) {
						event.getDrops().add(new ItemEntity(pillager.level(),
								pillager.getX(), pillager.getY(), pillager.getZ(),
								new ItemStack(Init.fermentation.get())));
					}
				}
			}
		}
	}

	private void horse(LivingDropsEvent event) {
		if (event.getEntity() instanceof Horse) {
			Random random = new Random();
			if (random.nextInt(100) < 10) {
				event.getDrops().add(new ItemEntity(event.getEntity().level(),
						event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
						new ItemStack(Init.woodenhorseshoe.get())));
			}
		}
	}

	private void wither_skeleton(LivingDropsEvent event) {
		if (event.getEntity() instanceof WitherSkeleton) {
			Random random = new Random();
			if (random.nextInt(100) < 2) {
				event.getDrops().add(new ItemEntity(event.getEntity().level(),
						event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
						new ItemStack(Init.twistedbadge.get())));
			}
		}
	}

	private void skeleton(LivingDropsEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (event.getEntity() instanceof Skeleton) {
				Random random = new Random();
				if (random.nextInt(100) < 2) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.badgeofthedead.get())));
				}
			}
		}
	}

	private void zom(LivingDropsEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (event.getEntity() instanceof Zombie) {
				Random random = new Random();
				int aaa = random.nextInt(100);
				if (aaa == 1) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.morb.get())));
				}
				if (aaa == 2) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.mblock.get())));
				}
				if (aaa == 3) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.mbottle.get())));
				}
				if (aaa == 4) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.mshell.get())));
				}
				if (aaa == 5) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.mring.get())));
				}
				if (aaa == 6) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.mkidney.get())));
				}
				if (aaa == 7) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.meye.get())));
				}
			}
		}
	}

	private void aaa(LivingDropsEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (event.getEntity() instanceof Zombie ||
					event.getEntity() instanceof Spider ||
					event.getEntity() instanceof Creeper ||
					event.getEntity() instanceof Skeleton) {
				int aaa = Mth.nextInt(RandomSource.create(), 1, 15);
				if (aaa == 1) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.ectoplasmball.get())));
				}
				if (aaa == 2) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.ectoplasmcloub.get())));
				}

				if (aaa == 3) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.soulball.get())));
				}
				if (aaa == 4) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.soul.get())));
				}
				if (aaa == 5) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(),
							event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
							new ItemStack(Init.soulcloub.get())));
				}
			}
		}
	}
}
