package com.stalemated.mutantskeletweaks.config;

import com.google.gson.*;
import com.stalemated.lib.config.BaseConfigManager;
import com.stalemated.lib.config.ConfigProvider;
import com.stalemated.mutantskeletweaks.network.MSATNetworkHandler;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.minecraft.util.Identifier;

import java.nio.file.Path;

import static com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks.LOGGER;

public class ConfigManager {

    private static final Path CONFIG_PATH = BaseConfigManager.buildPath("mutant_skeleton_armor_tweaks.json5");
    public static MSATConfig serverConfig = null;

    public static final ConfigClassHandler<MSATConfig> HANDLER = ConfigClassHandler.createBuilder(MSATConfig.class)
            .id(new Identifier("msat", "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(CONFIG_PATH)
                    .setJson5(true)
                    .build())
            .build();

    private static final BaseConfigManager<MSATConfig> CONFIG = new BaseConfigManager<MSATConfig>(
            new ConfigProvider<MSATConfig>() {
                @Override
                public boolean load() {
                    return HANDLER.load();
                }

                @Override
                public void save() {
                    HANDLER.save();
                }

                @Override
                public MSATConfig instance() {
                    return HANDLER.instance();
                }
            },
            CONFIG_PATH,
            LOGGER
    ) {};

    public static boolean configLoadFailed = false;

    public static void register() {
        CONFIG.register();
        configLoadFailed = CONFIG.configLoadFailed;
    }

    public static MSATConfig getLocalConfig() {
        return CONFIG.getConfig();
    }

    /**
     * Gets the currently active config (server config if connected to a server, local config otherwise)
     */
    public static MSATConfig getActiveConfig() {
        if (serverConfig != null) {
            return serverConfig;
        }
        return getLocalConfig();
    }

    public static void saveLocal() {
        CONFIG.save();
    }

    public static void save() {
        if (serverConfig != null) {
            MSATNetworkHandler.sendConfigToServer(getLocalConfig());
        }
        CONFIG.save();
    }
}