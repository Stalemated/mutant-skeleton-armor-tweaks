package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.handler.ArmorEffectsHandler;
import fuzs.mutantmonsters.world.item.SkeletonArmorItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SkeletonArmorItem.class)
public class SkeletonArmorItemMixin {

    @Redirect(method = "inventoryTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    private boolean toggleArmorPotionEffects(PlayerEntity player, StatusEffectInstance effectInstance) {
        ArmorItem armorItem = (ArmorItem) (Object) this;
        ArmorItem.Type type = armorItem.getType();

        if (!ArmorEffectsHandler.shouldApplyEffect(type)) return false;

        return player.addStatusEffect(effectInstance);
    }
}
