package com.stalemated.mutantskeletweaks.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import fuzs.mutantmonsters.world.item.ArmorBlockItem;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Redirect(
            method = "getAvailableEnchantmentResults",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/EnchantmentCategory;canEnchant(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private static boolean enchantTableAcceptsMutantSkeleSkull(EnchantmentCategory category, Item item) {

        // Verify if item is a mutant skele skull by referencing the class
        if (item instanceof ArmorBlockItem) {

            // Checks for relevant categories
            if (category == EnchantmentCategory.ARMOR ||
                    category == EnchantmentCategory.ARMOR_HEAD ||
                    category == EnchantmentCategory.BREAKABLE ||
                    category == EnchantmentCategory.VANISHABLE) {

                return true;
            }
        }

        return category.canEnchant(item);
    }
}