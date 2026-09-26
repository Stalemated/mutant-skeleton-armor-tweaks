package com.stalemated.mutantskeletweaks.config;

import com.stalemated.lib.config.SLibConfig;
import com.stalemated.lib.config.manager.SyncedConfigManager;

import static com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks.LOGGER;
import static com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks.MOD_ID;

public class ConfigManager {

    public static final SyncedConfigManager<MSATConfig> MANAGER = SLibConfig.syncedBuilder(MSATConfig.class)
            .modId(MOD_ID)
            .logger(LOGGER)
            .register();

    public static void init() {
    }

    public static MSATConfig getConfig() {
        return MANAGER.getActiveConfig();
    }
}
