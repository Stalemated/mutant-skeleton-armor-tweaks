package com.stalemated.mutantskeletweaks.network;

import com.google.gson.Gson;
import com.stalemated.lib.network.NetworkHelper;
import com.stalemated.mutantskeletweaks.config.ConfigManager;
import com.stalemated.mutantskeletweaks.config.MSATConfig;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import io.netty.buffer.Unpooled;

public class MSATNetworkHandler {
    public static final Identifier SYNC_CONFIG_S2C = new Identifier("msat", "sync_config");
    public static final Identifier SYNC_CONFIG_C2S = new Identifier("msat", "sync_config_c2s");
    private static final Gson GSON = new Gson();

    public static void init() {
        NetworkHelper.INSTANCE.registerClientReceiver(SYNC_CONFIG_S2C, buf -> {
            String json = buf.readString();
            ConfigManager.serverConfig = GSON.fromJson(json, MSATConfig.class);
        });

        NetworkHelper.INSTANCE.registerServerReceiver(SYNC_CONFIG_C2S, (player, buf) -> {
            if (player.hasPermissionLevel(2)) {
                String json = buf.readString();
                MSATConfig config = GSON.fromJson(json, MSATConfig.class);
                MSATConfig localConfig = ConfigManager.getLocalConfig();

                localConfig.enableSkullMultishot = config.enableSkullMultishot;
                localConfig.enableChestplateDrawSpeed = config.enableChestplateDrawSpeed;
                localConfig.enableChestplateCrossbowTweak = config.enableChestplateCrossbowTweak;
                localConfig.enableLeggingsEffect = config.enableLeggingsEffect;
                localConfig.enableBootsEffect = config.enableBootsEffect;
                
                ConfigManager.saveLocal();
                
                for (ServerPlayerEntity p : player.server.getPlayerManager().getPlayerList()) {
                    sendConfigToPlayer(p);
                }
            }
        });
    }

    public static void sendConfigToPlayer(ServerPlayerEntity player) {
        String json = GSON.toJson(ConfigManager.getLocalConfig());
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeString(json);
        NetworkHelper.INSTANCE.sendToClient(player, SYNC_CONFIG_S2C, buf);
    }

    public static void sendConfigToServer(MSATConfig config) {
        String json = GSON.toJson(config);
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeString(json);
        NetworkHelper.INSTANCE.sendToServer(SYNC_CONFIG_C2S, buf);
    }

    public static void clearServerConfig() {
        ConfigManager.serverConfig = null;
    }
}
