package com.stalemated.mutantskeletweaks.config;

import com.stalemated.lib.config.annotation.Comment;
import com.stalemated.lib.config.annotation.Sync;
import com.stalemated.lib.config.network.SyncMode;

@Sync(SyncMode.OVERRIDE_CLIENT)
public class MSATConfig {
    @Comment("Enable the multishot ability when wearing the skull")
    public boolean enableSkullMultishot = true;

    @Comment("Enable the faster bow draw speed when wearing the chestplate")
    public boolean enableChestplateDrawSpeed = true;

    @Comment("Enable the crossbow tweaks when wearing the chestplate")
    public boolean enableChestplateCrossbowTweak = true;

    @Comment("Enable jump boost when wearing the leggings")
    public boolean enableLeggingsEffect = true;

    @Comment("Enable speed when wearing the boots")
    public boolean enableBootsEffect = true;
}