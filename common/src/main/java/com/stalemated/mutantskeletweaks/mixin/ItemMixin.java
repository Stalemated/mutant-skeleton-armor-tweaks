package com.stalemated.mutantskeletweaks.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.stalemated.mutantskeletweaks.registry.ArmorRegistry.skullItem;

@Mixin(Item.class)
public class ItemMixin {
    @Unique
    private static final int SKULL_ENCHANTABILITY = 9;

    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void forceSkullEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (((Object) this) == skullItem) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getEnchantability", at = @At("HEAD"), cancellable = true)
    private void setSkullEnchantmentValue(CallbackInfoReturnable<Integer> cir) {
        if (((Object) this) == skullItem) {
            cir.setReturnValue(SKULL_ENCHANTABILITY);
        }
    }
}
