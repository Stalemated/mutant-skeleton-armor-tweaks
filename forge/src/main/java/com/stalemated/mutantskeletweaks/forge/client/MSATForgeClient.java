package com.stalemated.mutantskeletweaks.forge.client;

import com.stalemated.mutantskeletweaks.MSATClient;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import com.stalemated.mutantskeletweaks.network.MSATNetworkHandler;

public final class MSATForgeClient {
    public static void init() {
        MSATClient.init();
        MinecraftForge.EVENT_BUS.addListener(MSATForgeClient::onPlayerLogout);
    }

    private static void onPlayerLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        MSATNetworkHandler.clearServerConfig();
    }
}
