package com.stalemated;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MutantSkeletonArmorTweaks implements ModInitializer {
	public static final String MOD_ID = "mutant-skeleton-armor-tweaks";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MutantSkeletonArmorConfig.load();
		LOGGER.info("Mutant Skeleton Armor Tweaks loaded successfully!");
	}
}