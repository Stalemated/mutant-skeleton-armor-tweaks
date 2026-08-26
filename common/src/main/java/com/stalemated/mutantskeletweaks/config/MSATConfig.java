package com.stalemated.mutantskeletweaks.config;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

public class MSATConfig {
    @SerialEntry(comment = "Enable the multishot ability when wearing the skull")
    public boolean enableSkullMultishot = true;

    @SerialEntry(comment = "Enable the faster bow draw speed when wearing the chestplate")
    public boolean enableChestplateDrawSpeed = true;

    @SerialEntry(comment = "Enable the crossbow tweaks when wearing the chestplate")
    public boolean enableChestplateCrossbowTweak = true;

    @SerialEntry(comment = "Enable jump boost when wearing the leggings")
    public boolean enableLeggingsEffect = true;

    @SerialEntry(comment = "Enable speed when wearing the boots")
    public boolean enableBootsEffect = true;
}