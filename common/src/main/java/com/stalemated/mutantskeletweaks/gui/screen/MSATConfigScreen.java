package com.stalemated.mutantskeletweaks.gui.screen;

import com.stalemated.lib.config.permissions.ClientConfigPermissions;
import com.stalemated.mutantskeletweaks.config.ConfigManager;
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
                .save(ConfigManager.MANAGER::saveFromClient)
                .build()
                .generateScreen(parent);
    }

    private static OptionGroup createOptionsGroup() {
        boolean canEdit = ClientConfigPermissions.OP_OR_SP.get();

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
                        () -> ConfigManager.MANAGER.getActiveConfig().enableSkullMultishot,
                        val -> ConfigManager.MANAGER.updateField((cfg, v) -> cfg.enableSkullMultishot = v, val, ClientConfigPermissions.OP_OR_SP)
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
                        () -> ConfigManager.MANAGER.getActiveConfig().enableChestplateDrawSpeed,
                        val -> ConfigManager.MANAGER.updateField((cfg, v) -> cfg.enableChestplateDrawSpeed = v, val, ClientConfigPermissions.OP_OR_SP)
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
                        () -> ConfigManager.MANAGER.getActiveConfig().enableChestplateCrossbowTweak,
                        val -> ConfigManager.MANAGER.updateField((cfg, v) -> cfg.enableChestplateCrossbowTweak = v, val, ClientConfigPermissions.OP_OR_SP)
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
                        () -> ConfigManager.MANAGER.getActiveConfig().enableLeggingsEffect,
                        val -> ConfigManager.MANAGER.updateField((cfg, v) -> cfg.enableLeggingsEffect = v, val, ClientConfigPermissions.OP_OR_SP)
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
                        () -> ConfigManager.MANAGER.getActiveConfig().enableBootsEffect,
                        val -> ConfigManager.MANAGER.updateField((cfg, v) -> cfg.enableBootsEffect = v, val, ClientConfigPermissions.OP_OR_SP)
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
