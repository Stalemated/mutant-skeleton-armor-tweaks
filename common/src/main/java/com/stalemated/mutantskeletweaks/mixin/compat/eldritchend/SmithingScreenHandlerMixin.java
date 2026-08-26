package com.stalemated.mutantskeletweaks.mixin.compat.eldritchend;

import com.stalemated.mutantskeletweaks.compat.eldritchend.EldritchEndCompatManager;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SmithingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SmithingScreenHandler.class, priority = 500)
public abstract class SmithingScreenHandlerMixin {

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void msat$createResultCompatUpdated(CallbackInfo ci) {
        SmithingScreenHandler ths = (SmithingScreenHandler) (Object) this;

        ItemStack base = ths.getSlot(0).getStack();
        ItemStack candidate = ths.getSlot(1).getStack();
        ItemStack addition = ths.getSlot(2).getStack();

        if (EldritchEndCompatManager.applyInfusion(base, candidate, addition, ths.getSlot(3))) {
            ci.cancel();
        }
    }
}
