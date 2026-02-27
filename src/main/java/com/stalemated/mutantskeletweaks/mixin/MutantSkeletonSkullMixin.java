package com.stalemated.mutantskeletweaks.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import fuzs.mutantmonsters.world.item.ArmorBlockItem;

@Mixin(Item.class)
public class MutantSkeletonSkullMixin {

    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void forceSkullEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        // Verify if item is a mutant skele skull by referencing the class
        if (((Object) this) instanceof ArmorBlockItem) {
            cir.setReturnValue(true);
        }
    }

    // Add same enchanting power as the rest of the armor (9)
    @Inject(method = "getEnchantmentValue", at = @At("HEAD"), cancellable = true)
    private void setSkullEnchantmentValue(CallbackInfoReturnable<Integer> cir) {
        if (((Object) this) instanceof ArmorBlockItem) {
            cir.setReturnValue(9);
        }
    }
}