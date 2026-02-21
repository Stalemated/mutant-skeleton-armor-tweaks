package com.stalemated.mutantskeletweaks;

import com.stalemated.mutantskeletweaks.config.ConfigManager;

public class MutantSkeletonArmorConfig {
    public static boolean enableSkullMultishot;
    public static boolean enableChestplateDrawSpeed;
    public static boolean enableChestplateCrossbowTweak;
    public static boolean enableLeggingsEffect;
    public static boolean enableBootsEffect;

    public static void load() {
        ConfigManager config = new ConfigManager("mutant_skeleton_armor_tweaks.json");

        enableSkullMultishot = config.getOrDefault("enable_skull_multishot", true);
        enableChestplateDrawSpeed = config.getOrDefault("enable_chestplate_draw_speed_buff", true);
        enableChestplateCrossbowTweak = config.getOrDefault("enable_chestplate_crossbow_tweak", true);
        enableLeggingsEffect = config.getOrDefault("enable_leggings_effect", true);
        enableBootsEffect = config.getOrDefault("enable_boots_effect", true);
    }
}