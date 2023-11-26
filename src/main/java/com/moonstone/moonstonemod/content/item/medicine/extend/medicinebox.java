package com.moonstone.moonstonemod.content.item.medicine.extend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.init.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class medicinebox extends TheNecoraIC {
	public medicinebox() {
		MinecraftForge.EVENT_BUS.addListener(this::bbb);
		MinecraftForge.EVENT_BUS.addListener(this::cccc);

		MinecraftForge.EVENT_BUS.addListener(this::jump);
		MinecraftForge.EVENT_BUS.addListener(this::hurt);
		MinecraftForge.EVENT_BUS.addListener(this::apple);
		MinecraftForge.EVENT_BUS.addListener(this::die);
	}

	public static int jump_size;
	public static int do_jump;


	public static int hurt_size;
	public static int do_hurt;
	public static int apple;
	public static int do_apple;

	private void die(PlayerEvent.PlayerRespawnEvent event) {
		Player player = event.getEntity();
		CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
			Map<String, ICurioStacksHandler> curios = handler.getCurios();
			for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
				ICurioStacksHandler stacksHandler = entry.getValue();
				IDynamicStackHandler stackHandler = stacksHandler.getStacks();
				for (int i = 0; i < stacksHandler.getSlots(); i++) {
					ItemStack stack = stackHandler.getStackInSlot(i);
					if (!stack.isEmpty()) {
						if (stack.is(this)) {
							if (stack.getOrCreateTag().get("reanimation_die") == null) {
								if (CuriosHandler.hascurio(player, this)) {
									player.addItem(new ItemStack(Init.reanimation.get()));
									CompoundTag tag = new CompoundTag();
									stack.getOrCreateTag().put("reanimation_die", tag);
								}
							}
						}
					}
				}
			}
		});

	}

	private void apple(LivingEntityUseItemEvent.Finish event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				ItemStack stack = event.getItem();
				if (stack.is(Items.GOLDEN_APPLE)) {
					apple += 1;
					if (do_apple == 8) {
						player.addItem(new ItemStack(Init.masticatory.get()));
					}
				}
			}
		}
	}

	private void hurt(LivingHurtEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				hurt_size += 1;
				if (do_hurt == 350) {
					player.addItem(new ItemStack(Init.calcification.get()));
				}
			}
		}
	}

	private void jump(LivingEvent.LivingJumpEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				jump_size += 1;
				if (do_jump == 500) {
					player.addItem(new ItemStack(Init.quadriceps.get()));
				}
			}
		}
	}

	private void cccc(LivingEntityUseItemEvent.Finish event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				CompoundTag tag = new CompoundTag();
				ItemStack stack = event.getItem();

				if (stack.is(Items.ENCHANTED_GOLDEN_APPLE)) {
					player.addItem(Init.polyphagia.get().getDefaultInstance());
				}
			}
		}
	}

	private void bbb(AnvilUpdateEvent event) {
		ItemStack you = event.getRight();
		ItemStack zuo = event.getLeft().copy();
		if (!zuo.isEmpty()) {
			if (you != null) {
				if (zuo.is(Items.CHEST) && you.is(Init.ectoplasmcube.get())) {
					event.setCost(1);
					event.setMaterialCost(1);
					event.setOutput(this.asItem().getDefaultInstance());
				}
			}
		}
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
					if (jump_size > 0) {
						stack.getOrCreateTag().putInt("the_jump_size", stack.getOrCreateTag().getInt("the_jump_size") + 1);
						jump_size -= 1;
					}
					do_jump = stack.getOrCreateTag().getInt("the_jump_size");

					if (hurt_size > 0) {
						stack.getOrCreateTag().putInt("the_hurt_size", stack.getOrCreateTag().getInt("the_hurt_size") + 1);
						hurt_size -= 1;
					}
					do_hurt = stack.getOrCreateTag().getInt("the_hurt_size");

					if (apple > 0) {
						stack.getOrCreateTag().putInt("the_apple_size", stack.getOrCreateTag().getInt("the_apple_size") + 1);
						apple -= 1;
					}
					do_apple = stack.getOrCreateTag().getInt("the_apple_size");

				}
			}

			@Override
			public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
				Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();

				CuriosApi.getCuriosHelper()
						.addSlotModifier(linkedHashMultimap, "medicine", uuid, 3, AttributeModifier.Operation.ADDITION);


				return linkedHashMultimap;
			}

			@NotNull
			@Override
			public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
				return DropRule.ALWAYS_KEEP;
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
		tooltip.add(Component.translatable("如果你找到一些好的药物...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
		tooltip.add(Component.translatable("或许我可以给你打造一个全新的身体...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("佩戴此物品，杀死或开取战利品箱可能会发现一些药物").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
		tooltip.add(Component.translatable("你可以选择将发现的药物融合或直接佩戴").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
		tooltip.add(Component.translatable(""));
		tooltip.add(Component.translatable("必须佩戴此物品才能发现药物!").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));

	}
}
