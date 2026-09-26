package com.stalemated.mutantskeletweaks.forge;

import com.stalemated.mutantskeletweaks.forge.client.MSATForgeClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.api.distmarker.Dist;

import com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks;

@Mod(MutantSkeletonArmorTweaks.MOD_ID)
public final class MSATForge {
    public MSATForge() {
        MutantSkeletonArmorTweaks.init();
        
        if (FMLEnvironment.dist == Dist.CLIENT) {
            MSATForgeClient.init();
        }
    }
}
