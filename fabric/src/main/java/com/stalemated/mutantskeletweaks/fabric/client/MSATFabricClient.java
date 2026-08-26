package com.stalemated.mutantskeletweaks.fabric.client;

import com.stalemated.mutantskeletweaks.MSATClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import com.stalemated.mutantskeletweaks.network.MSATNetworkHandler;

public final class MSATFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MSATClient.init();

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            MSATNetworkHandler.clearServerConfig();
        });
    }
}
