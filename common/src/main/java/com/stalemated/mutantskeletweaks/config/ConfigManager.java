package com.stalemated.mutantskeletweaks.config;

import com.stalemated.lib.config.ConfigProvider;
import com.stalemated.lib.config.permissions.ServerConfigPermissions;
import com.stalemated.lib.config.SyncedConfigManager;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.minecraft.util.Identifier;

import java.nio.file.Path;

import static com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks.LOGGER;

public class ConfigManager {

    private static final Path CONFIG_PATH = SyncedConfigManager.buildPath("mutant_skeleton_armor_tweaks.json5");

    public static final ConfigClassHandler<MSATConfig> HANDLER = ConfigClassHandler.createBuilder(MSATConfig.class)
            .id(new Identifier("msat", "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(CONFIG_PATH)
                    .setJson5(true)
                    .build())
            .build();

    public static final SyncedConfigManager<MSATConfig> MANAGER = new SyncedConfigManager<>(
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
            LOGGER,
            new Identifier("msat", "sync_config"),
            MSATConfig.class,
            ServerConfigPermissions.OP_ONLY,
            (source, dest) -> {
                dest.enableSkullMultishot = source.enableSkullMultishot;
                dest.enableChestplateDrawSpeed = source.enableChestplateDrawSpeed;
                dest.enableChestplateCrossbowTweak = source.enableChestplateCrossbowTweak;
                dest.enableLeggingsEffect = source.enableLeggingsEffect;
                dest.enableBootsEffect = source.enableBootsEffect;
            }
    );

    public static boolean configLoadFailed = false;

    public static void register() {
        MANAGER.register();
        configLoadFailed = MANAGER.configLoadFailed;
    }
}
