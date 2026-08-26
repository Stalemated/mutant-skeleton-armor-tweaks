package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.handler.ArmorEffectsHandler;
import fuzs.mutantmonsters.handler.PlayerEventsHandler;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEventsHandler.class)
public class PlayerEventsHandlerMixin {

    @Redirect(method = "onItemUseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", ordinal = 1))
    private static Item toggleChestplateTweaks(ItemStack instance) {
        return ArmorEffectsHandler.getDrawSpeedItem(instance);
    }

    @Redirect(method = "onArrowLoose", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
    private static ItemStack toggleSkullMultishot(PlayerEntity player, EquipmentSlot slot) {
        return ArmorEffectsHandler.getSkullStack(player);
    }
}
