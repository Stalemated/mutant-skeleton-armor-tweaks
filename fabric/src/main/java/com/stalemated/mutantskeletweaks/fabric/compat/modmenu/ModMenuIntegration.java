package com.stalemated.mutantskeletweaks.fabric.compat.modmenu;

import com.stalemated.mutantskeletweaks.gui.screen.MSATConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() { return MSATConfigScreen::create; }
}
