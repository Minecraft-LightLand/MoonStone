package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.content.item.misc.Industry;
import com.moonstone.moonstonemod.content.item.medicine.extend.TheNecoraIC;
import com.moonstone.moonstonemod.content.item.medicine.extend.apple;
import com.moonstone.moonstonemod.content.item.medicine.extend.medIC;
import com.moonstone.moonstonemod.content.item.medicine.extend.necora;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

	@Shadow
	private ItemStack tooltipStack;


    /*
    @Inject(at = @At(value = "HEAD"),method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;IIII)V", cancellable = true)
    private void renderItem(LivingEntity p_282619_, Level p_281754_, ItemStack p_281675_, int p_281271_, int p_282210_, int p_283260_, int p_281995_, CallbackInfo ci) {
        GuiGraphics guiGraphics = (GuiGraphics)(Object) this;

        if (p_281675_.is(InIt.necora.get()))
        {
            guiGraphics.blit(new ResourceLocation(MoonstoneMod.MODID, "textures/glint/n.png"),p_281271_,p_282210_, 0, 0, 16, 16, 16,16);
        }
    }


     */


	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawManaged(Ljava/lang/Runnable;)V"), method = "renderTooltipInternal(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;)V", cancellable = true)
	public void moonstone$ClientTooltipPositioner(Font p_282675_, List<ClientTooltipComponent> p_282615_, int p_283230_, int p_283417_, ClientTooltipPositioner p_282442_, CallbackInfo ci) {
		if (tooltipStack.getItem() instanceof Industry) {
			GuiGraphics guiGraphics = (GuiGraphics) (Object) this;
			guiGraphics.blit(new ResourceLocation(MoonstoneMod.MODID, "textures/glint/the_glint.png"), p_283230_ - 16, p_283417_ - 32, 0, 0, 32, 32, 32, 32);
		}

		if (tooltipStack.getItem() instanceof necora ||
				tooltipStack.getItem() instanceof TheNecoraIC ||
				tooltipStack.getItem() instanceof apple ||
				tooltipStack.getItem() instanceof medIC
		) {
			GuiGraphics guiGraphics = (GuiGraphics) (Object) this;

			guiGraphics.blit(new ResourceLocation(MoonstoneMod.MODID, "textures/glint/necora.png"), p_283230_ - 16, p_283417_ - 32, 0, 0, 32, 32, 32, 32);

		}
	}

}