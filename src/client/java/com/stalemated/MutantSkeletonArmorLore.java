package com.stalemated;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;

public class MutantSkeletonArmorLore implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {

            String itemId = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();

            switch (itemId) {

                case "mutantmonsters:mutant_skeleton_skull":
                    lines.add(Component.empty());
                    lines.add(Component.literal("Unlocks a special Multishot for Bows").withStyle(ChatFormatting.GREEN));
                    break;

                case "mutantmonsters:mutant_skeleton_chestplate":
                    lines.add(Component.empty());
                    lines.add(Component.literal("Increases draw speed for Ranged Weapons").withStyle(ChatFormatting.GREEN));
                    break;

                case "mutantmonsters:mutant_skeleton_leggings":
                    lines.add(Component.empty());
                    lines.add(Component.literal("Increases movement speed").withStyle(ChatFormatting.GREEN));
                    break;

                case "mutantmonsters:mutant_skeleton_boots":
                    lines.add(Component.empty());
                    lines.add(Component.literal("Increases jump height").withStyle(ChatFormatting.GREEN));
                    break;
            }
        });
    }
}