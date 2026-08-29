package com.stalemated.mutantskeletweaks.gui.screen;

import com.stalemated.mutantskeletweaks.config.ConfigManager;
import com.stalemated.mutantskeletweaks.helper.PermissionsHelper;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class MSATConfigScreen {
    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("msat.config_screen.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("msat.config_screen.category.general"))
                        .group(createOptionsGroup())
                        .build())
                .save(ConfigManager::save)
                .build()
                .generateScreen(parent);
    }

    private static OptionGroup createOptionsGroup() {

        boolean canEdit = PermissionsHelper.canEditConfig();

        var skullMultishot = Option.<Boolean>createBuilder()
                .name(Text.translatable("msat.config_screen.enable_skull_multishot"))
                .description(OptionDescription.of(
                        Text.translatable("msat.config_screen.enable_skull_multishot.description"),
                        canEdit ?
                                Text.of("") :
                                Text.translatable("msat.config_screen.op_required")
                ))
                .binding(
                        true,
                        () -> ConfigManager.getActiveConfig().enableSkullMultishot,
                        val -> { 
                            if(canEdit) {
                                ConfigManager.getLocalConfig().enableSkullMultishot = val;
                                if (ConfigManager.serverConfig != null) ConfigManager.serverConfig.enableSkullMultishot = val;
                            }
                        }
                )
                .controller(TickBoxControllerBuilder::create)
                .available(canEdit)
                .build();

        var chestDrawSpeed = Option.<Boolean>createBuilder()
                .name(Text.translatable("msat.config_screen.enable_chestplate_draw_speed"))
                .description(OptionDescription.of(
                        Text.translatable("msat.config_screen.enable_chestplate_draw_speed.description"),
                        canEdit ?
                                Text.of("") :
                                Text.translatable("msat.config_screen.op_required")
                ))
                .binding(
                        true,
                        () -> ConfigManager.getActiveConfig().enableChestplateDrawSpeed,
                        val -> { 
                            if(canEdit) {
                                ConfigManager.getLocalConfig().enableChestplateDrawSpeed = val; 
                                if (ConfigManager.serverConfig != null) ConfigManager.serverConfig.enableChestplateDrawSpeed = val;
                            }
                        }
                )
                .controller(TickBoxControllerBuilder::create)
                .available(canEdit)
                .build();

        var chestCrossbowCompat = Option.<Boolean>createBuilder()
                .name(Text.translatable("msat.config_screen.enable_chestplate_crossbow_compat"))
                .description(OptionDescription.of(
                        Text.translatable("msat.config_screen.enable_chestplate_crossbow_compat.description"),
                        canEdit ?
                                Text.of("") :
                                Text.translatable("msat.config_screen.op_required")
                ))
                .binding(
                        true,
                        () -> ConfigManager.getActiveConfig().enableChestplateCrossbowTweak,
                        val -> { 
                            if(canEdit) {
                                ConfigManager.getLocalConfig().enableChestplateCrossbowTweak = val; 
                                if (ConfigManager.serverConfig != null) ConfigManager.serverConfig.enableChestplateCrossbowTweak = val;
                            }
                        }
                )
                .controller(TickBoxControllerBuilder::create)
                .available(canEdit)
                .build();

        var legsJumpBoost = Option.<Boolean>createBuilder()
                .name(Text.translatable("msat.config_screen.enable_leggings_effect"))
                .description(OptionDescription.of(
                        Text.translatable("msat.config_screen.enable_leggings_effect.description"),
                        canEdit ?
                                Text.of("") :
                                Text.translatable("msat.config_screen.op_required")
                ))
                .binding(
                        true,
                        () -> ConfigManager.getActiveConfig().enableLeggingsEffect,
                        val -> { 
                            if(canEdit) {
                                ConfigManager.getLocalConfig().enableLeggingsEffect = val; 
                                if (ConfigManager.serverConfig != null) ConfigManager.serverConfig.enableLeggingsEffect = val;
                            }
                        }
                )
                .controller(TickBoxControllerBuilder::create)
                .available(canEdit)
                .build();

        var bootsSpeed = Option.<Boolean>createBuilder()
                .name(Text.translatable("msat.config_screen.enable_boots_effect"))
                .description(OptionDescription.of(
                        Text.translatable("msat.config_screen.enable_boots_effect.description"),
                        canEdit ?
                                Text.of("") :
                                Text.translatable("msat.config_screen.op_required")
                ))
                .binding(
                        true,
                        () -> ConfigManager.getActiveConfig().enableBootsEffect,
                        val -> { 
                            if(canEdit) {
                                ConfigManager.getLocalConfig().enableBootsEffect = val; 
                                if (ConfigManager.serverConfig != null) ConfigManager.serverConfig.enableBootsEffect = val;
                            }
                        }
                )
                .controller(TickBoxControllerBuilder::create)
                .available(canEdit)
                .build();

        return OptionGroup.createBuilder()
                .name(Text.translatable("msat.config_screen.group.armor_effects"))
                .description(OptionDescription.of(
                        Text.translatable("msat.config_screen.group.armor_effects.description"),
                        canEdit ?
                                Text.of("") :
                                Text.translatable("msat.config_screen.op_required")
                ))
                .option(skullMultishot)
                .option(chestDrawSpeed)
                .option(chestCrossbowCompat)
                .option(legsJumpBoost)
                .option(bootsSpeed)
                .build();
    }
}
