package com.stalemated;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;

public class MutantSkeletonChestplateLore implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {

            String itemId = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();

            if (itemId.equals("mutantmonsters:mutant_skeleton_chestplate")) {
                lines.add(Component.empty());
                lines.add(Component.literal("Increases draw speed for Ranged Weapons")
                        .withStyle(ChatFormatting.GREEN));
            }
        });
    }
}