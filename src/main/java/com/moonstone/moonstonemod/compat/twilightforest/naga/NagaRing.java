package com.moonstone.moonstonemod.compat.twilightforest.naga;

import com.moonstone.moonstonemod.content.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import twilightforest.init.TFItems;

import java.util.List;

public class NagaRing extends Item {
	public NagaRing() {
		super(new Properties().stacksTo(1).rarity(Rarity.create("mblock", ChatFormatting.DARK_GREEN)));

		MinecraftForge.EVENT_BUS.addListener(this::aaa);

		MinecraftForge.EVENT_BUS.addListener(this::bbb);
	}

	private void bbb(LivingHurtEvent event) {
		if (ModList.get().isLoaded("twilightforest")) {
			if (event.getEntity() instanceof Player player) {
				if (CuriosHandler.hascurio(player, this)) {
					event.setAmount(event.getAmount() * 1.25f);
				}
			}
		}
	}

	private void aaa(LivingHurtEvent event) {
		if (ModList.get().isLoaded("twilightforest")) {
			if (event.getEntity() instanceof Player player) {
				if (CuriosHandler.hascurio(player, this)) {
					if (event.getSource().getEntity() instanceof Mob mob) {
						if (mob.isInvertedHealAndHarm()) {
							event.setAmount(event.getAmount() * 0.85f);
						}
					}

					if (player.getItemBySlot(EquipmentSlot.CHEST).is(TFItems.NAGA_CHESTPLATE.get()) &&
							player.getItemBySlot(EquipmentSlot.LEGS).is(TFItems.NAGA_LEGGINGS.get())) {
						if (event.getSource().is(DamageTypeTags.IS_EXPLOSION)) {
							event.setAmount(event.getAmount() / 2);
						}
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
		if (ModList.get().isLoaded("twilightforest")) {
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("§2· 当你穿戴全套的娜迦装备"));
			tooltip.add(Component.translatable("§2· 则额外增加§a 50%§2的爆炸伤害抗性"));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("§a· +25% §2生命恢复"));
			tooltip.add(Component.translatable(""));
			tooltip.add(Component.translatable("§a· -15% §2来自亡灵生物的伤害"));
			tooltip.add(Component.translatable(""));
		} else {
			tooltip.add(Component.translatable("请安装 暮色森林 模组来使用它").withStyle(ChatFormatting.RED));

		}
	}
}
