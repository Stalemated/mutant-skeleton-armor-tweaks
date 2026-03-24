package com.stalemated.mutantskeletweaks.mixin.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import fuzs.mutantmonsters.init.ModRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;

@Mixin(value = SmithingMenu.class, priority = 500)
public abstract class SmithingMenuEldritchCompatMixin {

    @Inject(method = "createResult", at = @At("HEAD"), cancellable = true)
    private void mutant_skeleton_armor_tweaks$createResultCompat(CallbackInfo ci) {
        SmithingMenu ths = (SmithingMenu) (Object) this;

        ItemStack base      = ths.getSlot(0).getItem(); // Template
        ItemStack candidate = ths.getSlot(1).getItem(); // Equipment
        ItemStack addition  = ths.getSlot(2).getItem(); // Material

        if (addition.isEmpty() || base.isEmpty() || candidate.isEmpty()) return;

        // Only intercept for the mutant skeleton skull
        if (candidate.getItem() != ModRegistry.MUTANT_SKELETON_SKULL_ITEM.get()) return;

        if (!(addition.getItem() instanceof InfusableItemMaterial material)) return;
        if (material.getInfusionTemplate() != base.getItem()) return;
        if (!material.applyToArmor() || !material.isInfusable()) return;

        CompoundTag existingInfusion = candidate.copy().getOrCreateTagElement("eldritch_infusions");
        boolean isAlreadyInfused = existingInfusion.getBoolean("isInfused");

        if (isAlreadyInfused && !material.canSwapInfusionTo().contains(existingInfusion.getString("materialIdentifier"))) return;

        ItemStack potentialResult = candidate.copy();

        CompoundTag nbt = potentialResult.getOrCreateTagElement("eldritch_infusions");

        nbt.putBoolean("isInfused", true);
        nbt.putString("currentInfusion", BuiltInRegistries.ITEM.getKey(addition.getItem()).getPath());
        nbt.putString("materialIdentifier", BuiltInRegistries.ITEM.getKey(addition.getItem()).toString());

        nbt.putString("infusionSlot", EquipmentSlot.HEAD.getName());

        ths.getSlot(3).set(potentialResult);

        ci.cancel();
    }
}
