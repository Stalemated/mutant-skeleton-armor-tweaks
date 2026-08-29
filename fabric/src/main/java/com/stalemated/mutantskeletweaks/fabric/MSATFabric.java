package com.stalemated.mutantskeletweaks.fabric;

import net.fabricmc.api.ModInitializer;

import com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks;
import com.stalemated.mutantskeletweaks.config.ConfigManager;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public final class MSATFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MutantSkeletonArmorTweaks.init();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
                ConfigManager.MANAGER.sendConfigToPlayer(handler.player));
    }
}
