package com.stalemated.mutantskeletweaks.helper;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

import static com.stalemated.mutantskeletweaks.registry.ArmorRegistry.skullItem;

public class ArmorEnchantabilityHelper {
    public static boolean enchantabilityConditions(EnchantmentTarget target, Item item) {
        if (item != skullItem) return false;

        return target == EnchantmentTarget.ARMOR ||
                target == EnchantmentTarget.ARMOR_HEAD ||
                target == EnchantmentTarget.BREAKABLE ||
                target == EnchantmentTarget.VANISHABLE;
    }
}
