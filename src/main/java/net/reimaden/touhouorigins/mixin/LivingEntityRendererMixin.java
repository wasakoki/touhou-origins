package net.reimaden.touhouorigins.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.reimaden.touhouorigins.power.FlipModelPower;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(method = "shouldFlipUpsideDown", at = @At("HEAD"), cancellable = true)
    private static void flipPlayerModel(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        List<FlipModelPower> powers = PowerHolderComponent.getPowers(entity, FlipModelPower.class);

        if (!powers.isEmpty()) {
            cir.setReturnValue(true);
        }

        String string = Formatting.strip(entity.getName().getString());
        if (!powers.isEmpty() && ("Dinnerbone".equals(string) || "Grumm".equals(string))) {
            cir.setReturnValue(false);
        }
    }
}
