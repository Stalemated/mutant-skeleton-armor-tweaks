package com.stalemated.mutantskeletweaks.fabric.client;

import com.stalemated.mutantskeletweaks.client.MSATClient;
import com.stalemated.mutantskeletweaks.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public final class MSATFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MSATClient.init();

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->
                ConfigManager.MANAGER.clearServerConfig());
    }
}
