package com.stalemated.mutantskeletweaks.forge.client;

import com.stalemated.mutantskeletweaks.client.MSATClient;
import com.stalemated.mutantskeletweaks.gui.screen.MSATConfigScreen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

@SuppressWarnings("removal")
public final class MSATForgeClient {
    public static void init() {
        MSATClient.init();
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> MSATConfigScreen.create(parent)));
    }
}
