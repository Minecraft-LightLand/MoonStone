package com.moonstone.moonstonemod.content.item.medicine.med;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.medicine.extend.medIC;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class polyphagia extends medIC {
	public polyphagia() {
		MinecraftForge.EVENT_BUS.addListener(this::cccc);

	}

	public int a_parasite_size;

	private void cccc(LivingEntityUseItemEvent.Finish event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity instanceof Player player) {
			ItemStack stack = event.getItem();
			if (stack.getUseAnimation() == UseAnim.EAT) {
				if (CuriosHandler.hascurio(player, this)) {
					a_parasite_size += 1;
					if (player.getFoodData().getSaturationLevel() > 2) {
						player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() - 2);
					}
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
					if (a_parasite_size > 0) {
						stack.getOrCreateTag().putInt("the_parasite", stack.getOrCreateTag().getInt("the_parasite") + 3);
						a_parasite_size -= 1;
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
					player.getAttributes().addTransientAttributeModifiers(lvl_1(player, stack));
				}
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().removeAttributeModifiers(lvl_1(player, stack));
				}
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
			tooltip.add(Component.translatable("·使实验者易感到饥饿，且消化力减弱").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·且需要更多的食物来恢复生命值").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("·过量的进食会增加实验者的体脂率").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·也会略微增加肌肉密度").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("·每增加300点食物点数").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·则提高10%各方面的肌肉属性").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable("·但由于体脂率的增加，会减少实验者的移动速度").withStyle(ChatFormatting.RED));
			tooltip.add(Component.translatable(""));
			if (Screen.hasAltDown()) {
				tooltip.add(Component.translatable("食物点数：" + stack.getOrCreateTag().getInt("the_parasite")).withStyle(ChatFormatting.RED));
			} else {
				tooltip.add(Component.translatable("ALT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
			}
		} else {
			tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
		}
	}


	public Multimap<Attribute, AttributeModifier> lvl_1(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();

		float aaa = 0;
		if (stack.getOrCreateTag().getInt("the_parasite") < 300) {
			aaa = 0;
		}
		if (stack.getOrCreateTag().getInt("the_parasite") > 300
				&& stack.getOrCreateTag().getInt("the_parasite") < 600) {
			aaa = 0.1f;
		}
		if (stack.getOrCreateTag().getInt("the_parasite") > 600
				&& stack.getOrCreateTag().getInt("the_parasite") < 900) {
			aaa = 0.2f;
		}

		if (stack.getOrCreateTag().getInt("the_parasite") > 900) {
			aaa = 0.3f;
		}

		modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("581a3f25-db2c-4e52-9823-62e9e4730daa"), MoonstoneMod.MODID + "jisakdmksasrrradmul", aaa, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("581a3f25-db2c-4e52-9823-62e9e4730daa"), MoonstoneMod.MODID + "jisakdmksasrrradmul", aaa, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("581a3f25-db2c-4e52-9823-62e9e4730daa"), MoonstoneMod.MODID + "jisakdmksasrrradmul", aaa, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("581a3f25-db2c-4e52-9823-62e9e4730daa"), MoonstoneMod.MODID + "jisakdmksasrrradmul", aaa, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("581a3f25-db2c-4e52-9823-62e9e4730daa"), MoonstoneMod.MODID + "jisakdmksasrrradmul", aaa, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("581a3f25-db2c-4e52-9823-62e9e4730daa"), MoonstoneMod.MODID + "jisakdmksasrrradmul", -aaa / 2, AttributeModifier.Operation.MULTIPLY_TOTAL));

		return modifierMultimap;
	}
}

