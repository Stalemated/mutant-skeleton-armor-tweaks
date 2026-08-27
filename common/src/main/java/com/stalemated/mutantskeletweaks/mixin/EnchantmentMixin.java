package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.helper.ArmorEnchantabilityHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
    @Final
    @Shadow
    public EnchantmentTarget target;

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void allowMutantSkullEnchantments(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (ArmorEnchantabilityHelper.enchantabilityConditions(target, stack.getItem())) {
            cir.setReturnValue(true);
        }
    }
}
