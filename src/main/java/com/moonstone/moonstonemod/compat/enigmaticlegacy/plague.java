package com.moonstone.moonstonemod.compat.enigmaticlegacy;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.compat.CuriosHandler;
import com.moonstone.moonstonemod.content.CurioItemCapability;
import com.moonstone.moonstonemod.content.item.EpicItem;
import com.moonstone.moonstonemod.init.Init;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.event.CurioEquipEvent;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;

public class plague extends EpicItem {

	public plague() {
		MinecraftForge.EVENT_BUS.addListener(this::aaaa);
		MinecraftForge.EVENT_BUS.addListener(this::cccc);
		MinecraftForge.EVENT_BUS.addListener(this::hhh);
		MinecraftForge.EVENT_BUS.addListener(this::hhhh);

		MinecraftForge.EVENT_BUS.addListener(this::eye);

	}

	private void eye(CurioEquipEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (event.getStack().is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("enigmaticlegacy:enigmatic_eye")))) {
				if (!player.getTags().contains("the_world")) {
					player.addItem(new ItemStack(this));
					player.addTag("the_world");
				}
			}
		}
	}

	public double the_plague;
	public double the_un_plague;
	public double the_world;

	private void hhhh(LivingHealEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (the_world >= 1) {
					event.setAmount(event.getAmount() * 2.2f);
				}
			}
		}

	}

	private void hhh(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (the_world >= 1) {
					if (event.getEntity() instanceof Player player1) {
						event.setAmount(event.getAmount() * 1.5f);
					}
					if (event.getEntity() instanceof Mob) {
						event.setAmount(event.getAmount() * 2f);
					}
					if (event.getEntity() instanceof Animal) {
						event.setAmount(event.getAmount() * 3);
					}
					if (event.getEntity() instanceof WitherBoss) {
						event.setAmount(event.getAmount() * 4);
					}
					if (event.getEntity() instanceof EnderDragon) {
						event.setAmount(event.getAmount() * 4);
					}
					event.setAmount(event.getAmount() * 1.2f);
				}
			}
		}
		if (event.getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				if (the_world >= 1) {
					if (event.getSource().is(DamageTypes.MAGIC)) {
						event.setAmount(event.getAmount() * 0.4f);
					}
					if (event.getSource().is(DamageTypes.IN_FIRE)) {
						event.setAmount(event.getAmount() * 0.4f);
					}
					if (event.getSource().is(DamageTypes.FALL)) {
						event.setAmount(event.getAmount() * 0.4f);
					}
					event.setAmount(event.getAmount() * 0.6f);
				}
			}
		}
	}

	private void aaaa(LivingDeathEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			if (CuriosHandler.hascurio(player, this)) {
				the_un_plague += 1;
			}
		}
	}

	private void cccc(LivingEntityUseItemEvent.Finish event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity instanceof Player player) {
			if (event.getItem().is(Items.GOLDEN_APPLE)) {
				the_plague += 7;
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

					if (the_plague > 0) {
						the_plague -= 0.05;
					}
					if (the_un_plague > 0) {
						the_un_plague -= 0.05;
					}
					if (the_world >= 1) {
						player.getAttributes().addTransientAttributeModifiers(world(player, stack));
						player.getAttributes().removeAttributeModifiers(un_un_pla(player, stack));
						player.getAttributes().removeAttributeModifiers(the_pla(player, stack));
					}

					if (stack.getOrCreateTag().getDouble("the_plague_size") < 99) {
						if (stack.getOrCreateTag().getDouble("un_plague") < 100) {
							stack.getOrCreateTag().putDouble("the_plague_size", stack.getOrCreateTag().getDouble("the_plague_size") + 0.0005);
						}
					}
					if (the_plague > 0) {
						if (stack.getOrCreateTag().getDouble("the_plague_size") > 1) {
							stack.getOrCreateTag().putDouble("the_plague_size", stack.getOrCreateTag().getDouble("the_plague_size") - 0.05);
						}
					}
					if (stack.getOrCreateTag().getDouble("un_plague") >= 100) {
						the_world = 1;
					}
					if (the_un_plague > 0) {
						if (stack.getOrCreateTag().getDouble("un_plague") < 100) {
							//0.005
							stack.getOrCreateTag().putDouble("un_plague", stack.getOrCreateTag().getDouble("un_plague") + 0.005);
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

			@NotNull
			@Override
			public SoundInfo getEquipSound(SlotContext slotContext) {
				return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC, 0, 0);
			}

			@Override
			public void onEquip(SlotContext slotContext, ItemStack prevStack) {
				if (slotContext.entity() instanceof Player player) {

					player.getAttributes().addTransientAttributeModifiers(un_un_pla(player, stack));
					player.getAttributes().addTransientAttributeModifiers(the_pla(player, stack));
				}
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				if (slotContext.entity() instanceof Player player) {
					player.getAttributes().removeAttributeModifiers(un_un_pla(player, stack));
					player.getAttributes().removeAttributeModifiers(the_pla(player, stack));
					player.getAttributes().removeAttributeModifiers(world(player, stack));
					the_world = 0;
				}
			}

			@Override
			public boolean canUnequip(SlotContext slotContext) {
				if (slotContext.entity() instanceof Player player) {
					if (player.isCreative()) {
						return true;
					}
					return CuriosHandler.hascurio(player, Init.nightmareeye.get());
				}
				return false;
			}
		});
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		if (!stack.isEmpty()) {
			if (stack.hasTag()) {
				if (stack.getOrCreateTag().getDouble("un_plague") >= 100) {
					tooltip.add(Component.translatable("      ·》 主宰者啊 《·").withStyle(ChatFormatting.DARK_RED));
					tooltip.add(Component.translatable("·》 为世界带来永恒的终极黑暗吧 《·").withStyle(ChatFormatting.DARK_RED));
					tooltip.add(Component.translatable("""
							+20%所有伤害
							+30%攻击伤害
							+40%伤害抗性
							+50%移动速度
							+50%对玩家的伤害
							+50%最大生命值
							+60%对魔法，火焰，摔落伤害的抗性
							+66%攻击速度
							+75%游泳速度
							+90%击退抗性
							+100%对怪物的伤害
							+110%生命恢复速度
							+200%对友好生物的伤害
							+400%对boss的伤害""").withStyle(ChatFormatting.DARK_RED));
				}
			}
			if (stack.getOrCreateTag().getDouble("un_plague") < 100) {
				if (Screen.hasAltDown()) {
					if (stack.hasTag()) {
						tooltip.add(Component.translatable("§5· 研究进度:"));
						tooltip.add(Component.translatable("§5·-[§6" + (float) stack.getOrCreateTag().getDouble("un_plague") + "§5% ]-·"));
						tooltip.add(Component.translatable("§5· 病毒数量:"));
						tooltip.add(Component.translatable("§5·-[§6" + (float) stack.getOrCreateTag().getDouble("the_plague_size") + "§5% ]-·"));
					} else {
						tooltip.add(Component.translatable("§5· 研究进度:"));
						tooltip.add(Component.translatable("·-[" + "0% ]-·").withStyle(ChatFormatting.RED));
						tooltip.add(Component.translatable(""));
						tooltip.add(Component.translatable("§5· 病毒数量:"));
						tooltip.add(Component.translatable("·-[" + "0% ]-·").withStyle(ChatFormatting.RED));
						tooltip.add(Component.translatable(""));
					}
				} else {
					tooltip.add(Component.translatable("§5按下§6ALT§5查看进度"));
				}

				if (Screen.hasControlDown()) {
					double aaa = stack.getOrCreateTag().getDouble("un_plague");
					double bbb = stack.getOrCreateTag().getDouble("the_plague_size");
					tooltip.add(Component.translatable("§5-研究点数会严重影响你的生命体征：").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
					tooltip.add(Component.translatable(" ·攻伤值：-" + ((float) aaa) / 150 + "%").withStyle(ChatFormatting.RED));
					tooltip.add(Component.translatable(" ·护甲值：-" + ((float) aaa) / 100 + "%").withStyle(ChatFormatting.RED));
					tooltip.add(Component.translatable(" ·攻速值：-" + ((float) aaa) / 200 + "%").withStyle(ChatFormatting.RED));
					tooltip.add(Component.translatable(""));
					tooltip.add(Component.translatable("§5-病毒数量也会影响你的生命体征：").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
					tooltip.add(Component.translatable(" ·生命值：-" + ((float) bbb) / 150 + "%").withStyle(ChatFormatting.RED));
					tooltip.add(Component.translatable(" ·移速值：-" + ((float) bbb) / 150 + "%").withStyle(ChatFormatting.RED));
				} else {
					tooltip.add(Component.translatable("§c按下§4CTRL§c查看详情"));
				}
				if (Screen.hasShiftDown()) {
					tooltip.add(Component.translatable("§5-这是一种危险的§6基因病毒！"));
					tooltip.add(Component.translatable("§5-当它植入你的体内时，它将与你融为一体"));
					tooltip.add(Component.translatable(""));
					tooltip.add(Component.translatable("§5-你必须§6猎杀§5生物来培育这种病毒"));
					tooltip.add(Component.translatable("§5-但在研究点数到达最高峰之前，你必须来抑制它的复制分裂"));
					tooltip.add(Component.translatable("§5-当它的数量达到一定的值时，此病毒将会侵蚀并尝试杀死宿主"));
					tooltip.add(Component.translatable("§5-但研究点数也会影响你的正常生命体征"));
					tooltip.add(Component.translatable(""));
					tooltip.add(Component.translatable("§5-吃下§6金苹果§5来抵挡它"));
					tooltip.add(Component.translatable(""));
				} else {
					tooltip.add(Component.translatable(""));
					tooltip.add(Component.translatable("§5按下§6SHIFT§5查看详情"));
					tooltip.add(Component.translatable(""));
				}
				tooltip.add(Component.translatable("· 只有连接凌驾时空之地的人才能获得此物品").withStyle(ChatFormatting.DARK_PURPLE));
				tooltip.add(Component.translatable("· 或许它能改变天地...").withStyle(ChatFormatting.DARK_PURPLE));
				tooltip.add(Component.translatable("· 万物始于此...但终将毁灭").withStyle(ChatFormatting.DARK_PURPLE));
				tooltip.add(Component.translatable(""));
				tooltip.add(Component.translatable("· 你会成为世界的主宰吗？").withStyle(ChatFormatting.DARK_RED));
			}
		}
	}

	public Multimap<Attribute, AttributeModifier> un_un_pla(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();

		double a = stack.getOrCreateTag().getDouble("un_plague");

		modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("24b26788-1ce6-44a6-a4df-45202850a9ef"), MoonstoneMod.MODID + "sojjmjmul", -a / 150, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("a9ca1491-c6b2-4502-a585-94336fb07e75"), MoonstoneMod.MODID + "soudfsdl", -a / 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("24b26788-1ce6-44a6-a4df-45202850a9ef"), MoonstoneMod.MODID + "sojjmasdadjmul", -a / 200, AttributeModifier.Operation.MULTIPLY_TOTAL));

		return modifierMultimap;
	}

	public Multimap<Attribute, AttributeModifier> the_pla(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
		double a = stack.getOrCreateTag().getDouble("the_plague_size");

		modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("4c2145c9-be1a-4ae4-bb9f-0003bd221a98"), MoonstoneMod.MODID + "souaasdsdfasddsl", -a / 150, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("64906813-adac-4aa9-8ac9-f25dbb677102"), MoonstoneMod.MODID + "souaasddsl", -a / 150, AttributeModifier.Operation.MULTIPLY_TOTAL));
		return modifierMultimap;
	}

	public Multimap<Attribute, AttributeModifier> world(Player player, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
		modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("a4ef9810-6c0b-45a6-9895-6bbebc70e001"), MoonstoneMod.MODID + "souaasdsdsadfasdnyhasddsl", 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("facc8bc4-a623-47dc-af89-ba979ac159b5"), MoonstoneMod.MODID + "souaasudfdgfkigthikikddsl", 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("7a195143-a6cd-4efb-8e57-deac4b7636d7"), MoonstoneMod.MODID + "souaasukiibgbgkfdikddsl", 0.66, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("f251c109-8214-4882-b0b4-332441843a50"), MoonstoneMod.MODID + "souaasukiidbhnunsakikddsl", 0.3, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("7a195143-a6cd-4efb-8e57-deac4b7636d7"), MoonstoneMod.MODID + "souaasukiibgbgkfdikddsl", 0.75, AttributeModifier.Operation.MULTIPLY_TOTAL));
		modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.fromString("f251c109-8214-4882-b0b4-332441843a50"), MoonstoneMod.MODID + "souaasukiidbhnunsakikddsl", 0.9, AttributeModifier.Operation.MULTIPLY_TOTAL));
		return modifierMultimap;
	}
}
