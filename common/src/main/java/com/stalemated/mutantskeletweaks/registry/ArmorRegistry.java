package com.stalemated.mutantskeletweaks.registry;

import fuzs.mutantmonsters.init.ModRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class ArmorRegistry {
    public static Item skullItem = ModRegistry.MUTANT_SKELETON_SKULL_ITEM.get();
    public static Item chestItem = ModRegistry.MUTANT_SKELETON_CHESTPLATE_ITEM.get();
    public static Item legsItem = ModRegistry.MUTANT_SKELETON_LEGGINGS_ITEM.get();
    public static Item bootsItem = ModRegistry.MUTANT_SKELETON_BOOTS_ITEM.get();

    public static String skullId = String.valueOf(Registries.ITEM.getId(skullItem));
    public static String chestId = String.valueOf(Registries.ITEM.getId(chestItem));
    public static String legsId = String.valueOf(Registries.ITEM.getId(legsItem));
    public static String bootsId = String.valueOf(Registries.ITEM.getId(bootsItem));
}
