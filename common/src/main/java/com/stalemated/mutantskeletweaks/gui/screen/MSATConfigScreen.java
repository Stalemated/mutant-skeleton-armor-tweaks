package com.stalemated.mutantskeletweaks.gui.screen;

import com.stalemated.lib.config.permissions.ClientConfigPermissions;
import com.stalemated.mutantskeletweaks.config.ConfigManager;
import com.stalemated.mutantskeletweaks.config.MSATConfig;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.function.Function;

public class MSATConfigScreen {
    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("msat.config_screen.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("msat.config_screen.category.general"))
                        .group(createOptionsGroup())
                        .build())
                .build()
                .generateScreen(parent);
    }

    private static OptionGroup createOptionsGroup() {
        boolean canEdit = ClientConfigPermissions.OP_OR_SP.get();

        return OptionGroup.createBuilder()
                .name(Text.translatable("msat.config_screen.group.armor_effects"))
                .description(OptionDescription.of(
                        Text.translatable("msat.config_screen.group.armor_effects.description"),
                        canEdit ? Text.empty() : Text.translatable("msat.config_screen.op_required")
                ))
                .option(createBooleanOption("enableSkullMultishot", "enable_skull_multishot", canEdit, cfg -> cfg.enableSkullMultishot, true))
                .option(createBooleanOption("enableChestplateDrawSpeed", "enable_chestplate_draw_speed", canEdit, cfg -> cfg.enableChestplateDrawSpeed, true))
                .option(createBooleanOption("enableChestplateCrossbowTweak", "enable_chestplate_crossbow_compat", canEdit, cfg -> cfg.enableChestplateCrossbowTweak, true))
                .option(createBooleanOption("enableLeggingsEffect", "enable_leggings_effect", canEdit, cfg -> cfg.enableLeggingsEffect, true))
                .option(createBooleanOption("enableBootsEffect", "enable_boots_effect", canEdit, cfg -> cfg.enableBootsEffect, true))
                .build();
    }

    private static Option<Boolean> createBooleanOption(String configKey, String langKeySuffix, boolean canEdit, Function<MSATConfig, Boolean> getter, boolean defaultValue) {
        String langKey = "msat.config_screen." + langKeySuffix;
        return Option.<Boolean>createBuilder()
                .name(Text.translatable(langKey))
                .description(OptionDescription.of(
                        Text.translatable(langKey + ".description"),
                        canEdit ? Text.empty() : Text.translatable("msat.config_screen.op_required")
                ))
                .binding(
                        defaultValue,
                        () -> getter.apply(ConfigManager.getConfig()),
                        val -> ConfigManager.MANAGER.updateOption(configKey, val)
                )
                .controller(TickBoxControllerBuilder::create)
                .available(canEdit)
                .build();
    }
}
