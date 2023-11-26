package com.moonstone.moonstonemod.content.item.T;

import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.EpicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class twistedrock extends EpicItem {
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
					Vec3 playerPos = player.position().add(0, 0.75, 0);
					int range = 4;
					List<ItemEntity> itemEntities = player.level().getEntitiesOfClass(ItemEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
					for (ItemEntity item : itemEntities) {
						if (item.getItem().is(Items.NETHERRACK)) {
							ExperienceOrb experienceOrb = new ExperienceOrb(item.level(), item.getX(), item.getY(), item.getZ(), 5);
							item.level().addFreshEntity(experienceOrb);
							item.discard();
						}
					}
				}
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
		tooltip.add(Component.translatable("· 将附近以掉落物形势存在的下界岩转换成经验").withStyle(ChatFormatting.DARK_RED));
	}
}
