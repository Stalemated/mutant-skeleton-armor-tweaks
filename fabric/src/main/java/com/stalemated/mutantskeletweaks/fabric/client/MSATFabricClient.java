package com.stalemated.mutantskeletweaks.fabric.client;

import com.stalemated.mutantskeletweaks.client.MSATClient;
import net.fabricmc.api.ClientModInitializer;

public final class MSATFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MSATClient.init();
    }
}
