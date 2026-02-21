package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.MutantSkeletonArmorConfig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "fuzs.mutantmonsters.world.item.SkeletonArmorItem")
public class MutantSkeletonArmorItemMixin {
    @Redirect(
            method = "inventoryTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"
            )
    )
    private boolean toggleArmorPotionEffects(Player player, MobEffectInstance effectInstance) {
        ArmorItem armorItem = (ArmorItem) (Object) this;
        ArmorItem.Type type = armorItem.getType();

        // Boots toggle check
        if (type == ArmorItem.Type.BOOTS) {
            if (!MutantSkeletonArmorConfig.enableBootsEffect) {
                return false;
            }
        }

        // Leggings toggle check
        if (type == ArmorItem.Type.LEGGINGS) {
            if (!MutantSkeletonArmorConfig.enableLeggingsEffect) {
                return false;
            }
        }

        return player.addEffect(effectInstance);
    }
}