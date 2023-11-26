package com.moonstone.moonstonemod.Item.MoonStoneItem.M;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.CurioItemCapability;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonstoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.UUID;


public class mbox extends Item {
    public mbox() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("mblock", ChatFormatting.GREEN)));
    }

    private static final Component CONTAINER_TITLE = Component.translatable("container.enderchest");

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (p_41433_ instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new SimpleMenuProvider((p_53124_, p_53125_, p_53126_) -> {
                PlayerEnderChestContainer playerenderchestcontainer = serverPlayer.getEnderChestInventory();
                return ChestMenu.threeRows(p_53124_, p_53125_, playerenderchestcontainer);
                }, CONTAINER_TITLE));
        }


        return super.use(p_41432_, p_41433_, p_41434_);
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundTag unused){
        return CurioItemCapability.createProvider(new ICurio() {

            @Override
            public ItemStack getStack() {
                return stack;
            }

            @NotNull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit) {
                return DropRule.ALWAYS_KEEP;
            }
            @Override
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
                Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
                modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonstoneMod.MODID+"ec", 6, AttributeModifier.Operation.ADDITION));

                return modifierMultimap;
            }
            @Override
            public boolean canEquip(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    return !Handler.hascurio(player, stack.getItem());
                }
                return true;

            }
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("§2· 右键§2打开背包"));

    }
}

