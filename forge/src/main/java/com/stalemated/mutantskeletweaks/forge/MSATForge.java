package com.stalemated.mutantskeletweaks.forge;

import com.stalemated.mutantskeletweaks.forge.client.MSATForgeClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.api.distmarker.Dist;

import com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import com.stalemated.mutantskeletweaks.config.ConfigManager;
import net.minecraft.server.network.ServerPlayerEntity;

@Mod(MutantSkeletonArmorTweaks.MOD_ID)
public final class MSATForge {
    public MSATForge() {
        MutantSkeletonArmorTweaks.init();
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerJoin);
        
        if (FMLEnvironment.dist == Dist.CLIENT) {
            MSATForgeClient.init();
        }
    }

    private void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayerEntity serverPlayer) {
            ConfigManager.MANAGER.sendConfigToPlayer(serverPlayer);
        }
    }
}
