package com.stalemated.mutantskeletweaks;

import com.stalemated.mutantskeletweaks.config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MutantSkeletonArmorTweaks {
    public static final String MOD_ID = "mutant_skeleton_armor_tweaks";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        ConfigManager.register();

        LOGGER.info("Mutant Skeleton Armor Tweaks loaded successfully!");
    }
}
