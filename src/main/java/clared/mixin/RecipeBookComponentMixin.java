package clared.mixin;

import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookComponent.class)
public class RecipeBookComponentMixin {

    @Inject(method = "updateScreenPosition", at = @At("RETURN"), cancellable = true)
    private void removeShift(int width, int imageWidth, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((width - imageWidth) / 2);
    }

    @Inject(method = "getXOrigin", at = @At("RETURN"), cancellable = true)
    private void adjustBookX(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(cir.getReturnValue() - 77);
    }

    @ModifyArg(
            method = "updateTabs",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookTabButton;setPosition(II)V"),
            index = 0)
    private int adjustTabX(int x) {
        return x - 77;
    }
}
