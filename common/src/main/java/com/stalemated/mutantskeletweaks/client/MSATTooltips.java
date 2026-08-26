package com.stalemated.mutantskeletweaks.client;

import com.stalemated.customtooltips.api.CustomTooltipApi;
import com.stalemated.customtooltips.api.enums.TooltipStyle;
import com.stalemated.mutantskeletweaks.config.ConfigManager;
import com.stalemated.mutantskeletweaks.config.MSATConfig;

import java.util.ArrayList;
import java.util.List;

import static com.stalemated.mutantskeletweaks.registry.ArmorRegistry.*;

public class MSATTooltips {

    public static void register() {
        // Skull
        CustomTooltipApi.builder(skullId)
                .displayCondition(s -> ConfigManager.getActiveConfig().enableSkullMultishot)
                .addLine(CustomTooltipApi.translate("msat.tooltip.skull_multishot"))
                .style(TooltipStyle.SOLID)
                .colors("#FFD700")
                .requireKeybind(true)
                .register();

        // Chestplate
        CustomTooltipApi.builder(chestId)
                .displayCondition(s -> ConfigManager.getActiveConfig().enableChestplateDrawSpeed)
                .dynamicText(s -> {
                    MSATConfig config = ConfigManager.getActiveConfig();
                    List<String> lines = new ArrayList<>();

                    lines.add(CustomTooltipApi.translate("msat.tooltip.chestplate_draw_speed.enabled"));

                    if (config.enableChestplateCrossbowTweak) {
                        lines.add(CustomTooltipApi.translate("msat.tooltip.chestplate_crossbow.enabled"));
                    } else {
                        lines.add(CustomTooltipApi.translate("msat.tooltip.chestplate_crossbow.disabled"));
                    }

                    return lines;
                })
                .style(TooltipStyle.SOLID)
                .requireKeybind(true)
                .register();

        // Leggings
        CustomTooltipApi.builder(legsId)
                .displayCondition(s -> ConfigManager.getActiveConfig().enableLeggingsEffect)
                .addLine(CustomTooltipApi.translate("effect.minecraft.speed") + CustomTooltipApi.translate("msat.tooltip.speed"))
                .style(TooltipStyle.SOLID)
                .colors("#5541f8")
                .register();

        // Boots
        CustomTooltipApi.builder(bootsId)
                .displayCondition(s -> ConfigManager.getActiveConfig().enableBootsEffect)
                .addLine(CustomTooltipApi.translate("effect.minecraft.jump_boost") + CustomTooltipApi.translate("msat.tooltip.jump_boost"))
                .style(TooltipStyle.SOLID)
                .colors("#5541F8")
                .register();
    }
}
