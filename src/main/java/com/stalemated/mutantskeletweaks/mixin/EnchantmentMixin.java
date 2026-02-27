package com.stalemated.mutantskeletweaks.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import fuzs.mutantmonsters.world.item.ArmorBlockItem;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
    @Final
    @Shadow public EnchantmentCategory category;

    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    private void allowMutantSkullEnchantments(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {

        // Verify if item is a mutant skele skull by referencing the class
        if (stack.getItem() instanceof ArmorBlockItem) {

            // Checks for relevant categories
            if (this.category == EnchantmentCategory.ARMOR ||
                    this.category == EnchantmentCategory.ARMOR_HEAD ||
                    this.category == EnchantmentCategory.BREAKABLE ||
                    this.category == EnchantmentCategory.VANISHABLE) {
                cir.setReturnValue(true);
            }
        }
    }
}