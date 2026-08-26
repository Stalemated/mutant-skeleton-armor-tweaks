package com.stalemated.mutantskeletweaks;

import com.stalemated.lib.helper.PlatformHelper;
import com.stalemated.mutantskeletweaks.client.MSATTooltips;

public final class MSATClient {
    public static void init() {
        if (PlatformHelper.INSTANCE.isModLoaded("customtooltips")) {
            MSATTooltips.register();
        }
    }
}
