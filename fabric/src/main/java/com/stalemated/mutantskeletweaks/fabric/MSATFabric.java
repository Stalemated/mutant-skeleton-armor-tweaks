package com.stalemated.mutantskeletweaks.fabric;

import net.fabricmc.api.ModInitializer;

import com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import com.stalemated.mutantskeletweaks.network.MSATNetworkHandler;

public final class MSATFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MutantSkeletonArmorTweaks.init();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            MSATNetworkHandler.sendConfigToPlayer(handler.player);
        });
    }
}
