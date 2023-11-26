package com.moonstone.moonstonemod.mixin;


import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.InIt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract ItemStack eat(Level p_21067_, ItemStack p_21068_);

    @Inject(at = @At("RETURN"), method = "onSoulSpeedBlock", cancellable = true)
    public void moonstone$onSoulSpeedBlock(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, InIt.hellbattery.get())) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "getJumpPower", cancellable = true)
    public void moonstone$getJumpPower(CallbackInfoReturnable<Float> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, InIt.quadriceps.get())) {
                cir.setReturnValue(cir.getReturnValue() * 1.5f);
            }
        }
    }


    @Inject(at = @At("RETURN"), method = "canStandOnFluid", cancellable = true)
    public void moonstone$canStandOnFluid(FluidState p_204042_, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, InIt.evilcandle.get())) {
                if (p_204042_.is(Fluids.LAVA)) {
                    cir.setReturnValue(true);
                }
            }

            if (Handler.hascurio(player, InIt.ambush.get())) {
                cir.setReturnValue(true);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "travel", cancellable = true)
    public void moonstone$travel(Vec3 p_21280_, CallbackInfo ci) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, InIt.conch.get())) {
                if (player.isInWater()) {
                    if (!player.onGround()) {
                        player.moveRelative(player.getSpeed(), p_21280_);
                        player.setAirSupply(20);
                    }
                }
            }
        }
    }


}



