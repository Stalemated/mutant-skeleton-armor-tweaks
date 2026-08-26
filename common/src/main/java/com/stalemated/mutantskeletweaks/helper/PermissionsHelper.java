package com.stalemated.mutantskeletweaks.helper;

import net.minecraft.client.MinecraftClient;

public class PermissionsHelper {
    public static boolean canEditConfig() {
        MinecraftClient client = MinecraftClient.getInstance();
        boolean inMultiplayer = client.world != null && !client.isInSingleplayer();
        boolean isOp = client.player != null && client.player.hasPermissionLevel(2);
        return !inMultiplayer || isOp;
    }
}
