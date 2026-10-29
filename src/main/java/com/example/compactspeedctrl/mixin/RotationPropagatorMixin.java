package com.example.compactspeedctrl.mixin;

import com.example.compactspeedctrl.CompactSpeedControllerBlockEntity;
import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Multiplies the speed passed between two kinetic blocks by our controller's value when one of them is a compact controller.
 */
@Mixin(RotationPropagator.class)
public class RotationPropagatorMixin {

    @Inject(
            method = "getRotationSpeedModifier(Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;)F",
            at = @At("RETURN"),
            cancellable = true,
            remap = false)
    private static void compactspeedctrl$scaleSpeed(KineticBlockEntity from, KineticBlockEntity to,
                                                    CallbackInfoReturnable<Float> cir) {
        float result = cir.getReturnValue();
        if (from instanceof CompactSpeedControllerBlockEntity controller && to != null) {
            cir.setReturnValue(result * controller.getRotationSpeedModifier(directionTo(from.getBlockPos(), to.getBlockPos())));
        } else if (to instanceof CompactSpeedControllerBlockEntity controller && from != null) {
            cir.setReturnValue(result * controller.getRotationSpeedModifier(directionTo(to.getBlockPos(), from.getBlockPos())));
        }
    }

    private static Direction directionTo(BlockPos a, BlockPos b) {
        return Direction.getNearest(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ());
    }
}
