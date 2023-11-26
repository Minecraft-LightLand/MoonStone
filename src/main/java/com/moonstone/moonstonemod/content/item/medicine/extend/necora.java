package com.moonstone.moonstonemod.content.item.medicine.extend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class necora extends TheNecoraIC {
	public necora() {

		MinecraftForge.EVENT_BUS.addListener(this::aaa);
		MinecraftForge.EVENT_BUS.addListener(this::bbb);
		MinecraftForge.EVENT_BUS.addListener(this::ccc);
		MinecraftForge.EVENT_BUS.addListener(this::ddd);
	}

	private void ddd(LivingHealEvent event) {
		if (event.getEntity() instanceof Player player) {

			event.setAmount(event.getAmount() * 1.5f);

		}
	}

	private void ccc(LivingDropsEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (Mth.nextInt(RandomSource.create(), 1, 3) == 1) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), new ItemStack(Items.ROTTEN_FLESH)));
				}
			}
		}
	}

	private void bbb(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (event.getSource().is(DamageTypes.MAGIC)) {
					event.setAmount(event.getAmount() * 0.3f);
				}
			}
		}
	}

	private void aaa(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntity() instanceof Player player) {
			CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
				Map<String, ICurioStacksHandler> curios = handler.getCurios();
				for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
					String identifier = entry.getKey();
					ICurioStacksHandler stacksHandler = entry.getValue();
					IDynamicStackHandler stackHandler = stacksHandler.getStacks();
					for (int i = 0; i < stacksHandler.getSlots(); i++) {
						NonNullList<Boolean> renderStates = stacksHandler.getRenders();
						SlotContext slotContext = new SlotContext(identifier, player, i, false,
								renderStates.size() > i && renderStates.get(i));
						ItemStack stack = stackHandler.getStackInSlot(i);
						if (!stack.isEmpty()) {
							if (stack.is(this)) {
								if (event.getItem().is(Items.ROTTEN_FLESH)) {
									player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
									player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0));
									stack.hurtAndBreak(-1440, player, ddd -> CuriosApi.getCuriosHelper().onBrokenCurio(slotContext));
								}
							}
						}
					}
				}
			});

		}
	}

	private Multimap<Attribute, AttributeModifier> Head(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
		double acc = 0.8;
		if (CuriosHandler.hascurio(player, Init.autolytic.get())) {
			acc = 0;
		}
		multimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(
				UUID.fromString("00000000-0000-300f-95e1-2830b5159532"),
				"ara",
				-acc,
				AttributeModifier.Operation.MULTIPLY_TOTAL));

		multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
				UUID.fromString("00000000-0000-300f-95e1-2830b5159532"),
				"ara",
				3,
				AttributeModifier.Operation.ADDITION));

		multimap.put(Attributes.ARMOR, new AttributeModifier(
				UUID.fromString("00000000-0000-300f-95e1-2830b5159532"),
				"ara",
				4,
				AttributeModifier.Operation.ADDITION));


		return multimap;
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
					if (CuriosHandler.hascurio(player, Init.warcrystal.get())
							&& CuriosHandler.hascurio(player, Init.bigwarcrystal.get())
							&& CuriosHandler.hascurio(player, Init.mayhemcrystal.get())) {
						if (stack.getOrCreateTag().get("warcharm") == null) {
							player.addItem(new ItemStack(Init.warcharm.get()));
							CompoundTag tag = new CompoundTag();
							stack.getOrCreateTag().put("warcharm", tag);
						}
					}


					if (!CuriosHandler.hascurio(player, Init.putrefactive.get())) {
						if (!player.level().isClientSide && player.tickCount % 20 == 0) {
							stack.hurtAndBreak(1, player, ddd -> CuriosApi.getCuriosHelper().onBrokenCurio(slotContext));
						}
					} else {
						if (!player.level().isClientSide && player.tickCount % 100 == 0) {
							stack.hurtAndBreak(1, player, ddd -> CuriosApi.getCuriosHelper().onBrokenCurio(slotContext));
						}
					}


					Vec3 playerPos = player.position().add(0, 0.75, 0);
					int range = 12;
					List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

					for (LivingEntity living : entities) {
						if (living instanceof Villager villager) {
							villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, Player.class, 6.0F, 1.0D, 1.2D));
						}
						if (living instanceof IronGolem ironGolem) {
							ironGolem.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(ironGolem, Player.class, true));
						}

						if (living instanceof Mob mob) {
							if (mob.isInvertedHealAndHarm()) {

								mob.setTarget(null);

							}
						}


					}


					if (player.getItemBySlot(EquipmentSlot.HEAD).isEmpty() &&
							(player.level().isDay() &&
									player.level().canSeeSky(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ())))) {
						player.setSecondsOnFire(2);
					}
				}
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
			}

			@Override
			public void onEquip(SlotContext slotContext, ItemStack prevStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().addTransientAttributeModifiers(Head(player, stack));
				}
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().removeAttributeModifiers(Head(player, stack));
				}
			}

			@Override
			public boolean canUnequip(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					return player.isCreative();
				}
				return false;
			}

			@Override
			public boolean canEquip(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					return !CuriosHandler.hascurio(player, stack.getItem());
				}
				return true;

			}
		});
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-丧尸化所带来的5条诅咒：").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable(" -你对水产生强烈排斥，以至于你的水中运动能力大大下降").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(" -若你的头上没有任何物品或方块阻挡，你将会燃烧").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(" -丧尸带来的颜面变化使得村民惧怕你，铁傀儡讨厌你").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(" -你必须时刻吃下腐肉来维持你的丧尸形态，否则Necora病毒将彻底从你的体内消失").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(" -一旦植入，它将与你融为一体").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-丧尸化所带来的5条祝福").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable(" -肌肉密度的增加使你的伤害增加3点，防御增加4点").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(" -受到的魔法伤害减少70%").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(" -杀死任何生物都有概率掉落腐肉，并且吃下腐肉可以获得额外Buff").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(" -亡灵生物与你保持中立").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(" -生命回复速度增加50%").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
		} else {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("-[SHIFT]").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
			tooltip.add(Component.translatable("该病原体尚未被归类").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("且早期医学报告中表明它具有").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("超级的再生能力和强烈的代谢需求").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable("但是它的大部分基因结构仍然是未解之谜....").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
			tooltip.add(Component.translatable(""));
		}
	}
}
