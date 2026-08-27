package com.stalemated.mutantskeletweaks.helper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static com.stalemated.mutantskeletweaks.registry.ArmorRegistry.skullItem;

public class ArmorEnchantabilityHelper {

    public static boolean enchantabilityConditions(EnchantmentTarget target, Item item) {
        if (item != skullItem) return false;

        return target == EnchantmentTarget.ARMOR ||
                target == EnchantmentTarget.ARMOR_HEAD ||
                target == EnchantmentTarget.BREAKABLE ||
                target == EnchantmentTarget.VANISHABLE;
    }

    public static void allowMutantSkullEnchantments(EnchantmentTarget target, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (enchantabilityConditions(target, stack.getItem())) {
            cir.setReturnValue(true);
        }
    }

    public static void addMutantSkullEnchantments(int power, ItemStack stack, boolean treasureAllowed, List<EnchantmentLevelEntry> entries) {
        if (entries == null) return;

        for (Enchantment enchantment : Registries.ENCHANTMENT) {
            if (shouldAddEnchantment(enchantment, stack, treasureAllowed, entries)) {
                addEnchantmentLevels(entries, enchantment, power);
            }
        }
    }

    private static boolean shouldAddEnchantment(Enchantment enchantment, ItemStack stack, boolean treasureAllowed, List<EnchantmentLevelEntry> entries) {
        if (enchantment.isTreasure() && !treasureAllowed) return false;
        if (!enchantment.isAvailableForRandomSelection()) return false;
        if (!enchantabilityConditions(enchantment.target, stack.getItem())) return false;

        for (EnchantmentLevelEntry entry : entries) {
            if (entry.enchantment == enchantment) {
                return false;
            }
        }

        return true;
    }

    private static void addEnchantmentLevels(List<EnchantmentLevelEntry> entries, Enchantment enchantment, int power) {
        for (int i = enchantment.getMaxLevel(); i >= enchantment.getMinLevel(); --i) {
            if (power >= enchantment.getMinPower(i) && power <= enchantment.getMaxPower(i)) {
                entries.add(new EnchantmentLevelEntry(enchantment, i));
                break;
            }
        }
    }
}
