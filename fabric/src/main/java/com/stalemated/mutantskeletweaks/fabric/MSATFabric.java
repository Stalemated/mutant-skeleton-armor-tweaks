package com.stalemated.mutantskeletweaks.fabric;

import net.fabricmc.api.ModInitializer;

import com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks;

public final class MSATFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MutantSkeletonArmorTweaks.init();
    }
}
