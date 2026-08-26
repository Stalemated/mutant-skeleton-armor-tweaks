package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.helper.ArmorEnchantabilityHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Redirect(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(Lnet/minecraft/item/Item;)Z"))
    private static boolean enchantTableAcceptsMutantSkull(EnchantmentTarget target, Item item) {
        if (ArmorEnchantabilityHelper.enchantabilityConditions(target, item)) {
            return true;
        }
        return target.isAcceptableItem(item);
    }
}
