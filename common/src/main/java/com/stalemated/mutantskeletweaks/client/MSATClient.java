package com.stalemated.mutantskeletweaks.client;

import com.stalemated.lib.helper.PlatformHelper;

public final class MSATClient {
    public static void init() {
        if (PlatformHelper.INSTANCE.isModLoaded("customtooltips")) {
            MSATTooltips.register();
        }
    }
}
