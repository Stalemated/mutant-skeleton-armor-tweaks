package com.stalemated.mutantskeletweaks.forge.client;

import com.stalemated.mutantskeletweaks.client.MSATClient;
import com.stalemated.mutantskeletweaks.gui.screen.MSATConfigScreen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import com.stalemated.mutantskeletweaks.network.MSATNetworkHandler;
import net.minecraftforge.fml.ModLoadingContext;

@SuppressWarnings("removal")
public final class MSATForgeClient {
    public static void init() {
        MSATClient.init();
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> MSATConfigScreen.create(parent)));
        MinecraftForge.EVENT_BUS.addListener(MSATForgeClient::onPlayerLogout);
    }

    private static void onPlayerLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        MSATNetworkHandler.clearServerConfig();
    }
}
