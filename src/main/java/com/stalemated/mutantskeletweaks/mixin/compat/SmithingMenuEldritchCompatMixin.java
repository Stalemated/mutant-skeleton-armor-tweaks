package com.stalemated.mutantskeletweaks.mixin.compat;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import elocindev.eldritch_end.api.infusion.InfusionTemplate;
import fuzs.mutantmonsters.init.ModRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;

@Mixin(value = SmithingMenu.class, priority = 500)
public abstract class SmithingMenuEldritchCompatMixin {

    @Inject(method = "createResult", at = @At("HEAD"), cancellable = true)
    private void mutant_skeleton_armor_tweaks$createResultCompat(CallbackInfo ci) {
        SmithingMenu ths = (SmithingMenu) (Object) this;

        ItemStack base = ths.getSlot(0).getItem();      // Template
        ItemStack candidate = ths.getSlot(1).getItem(); // Equipment
        ItemStack addition = ths.getSlot(2).getItem();  // Material

        if (addition.isEmpty() || base.isEmpty() || candidate.isEmpty()) return;

        if (candidate.getItem() != ModRegistry.MUTANT_SKELETON_SKULL_ITEM.get()) return;

        if (addition.getItem() instanceof InfusableItemMaterial material) {
            if (material.getInfusionTemplate() != base.getItem()) return;
            if (!material.applyToArmor() || (!material.isInfusable())) return;

            boolean isAlreadyInfused = candidate.copy().getOrCreateTagElement("eldritch_infusions").getBoolean("isInfused");

            if (isAlreadyInfused && !material.canSwapInfusionTo().contains(candidate.copy().getOrCreateTagElement("eldritch_infusions").getString("materialIdentifier"))) return;

            ItemStack potentialResult = candidate.copy();

            CompoundTag nbt = potentialResult.getOrCreateTagElement("eldritch_infusions");

            // explicitly force HEAD slot for attributes
            EquipmentSlot slot = EquipmentSlot.HEAD;

            nbt.putBoolean("isInfused", true);
            nbt.putString("currentInfusion", BuiltInRegistries.ITEM.getKey(addition.getItem()).getPath());
            nbt.putString("materialIdentifier", BuiltInRegistries.ITEM.getKey(addition.getItem()).toString());
            
            Multimap<Attribute, AttributeModifier> originalModifiers = HashMultimap.create();
            originalModifiers.putAll(potentialResult.getAttributeModifiers(slot));
            
            Multimap<Attribute, AttributeModifier> mergedModifiers = HashMultimap.create(originalModifiers);
            
            for (var holder : material.getInfusionAttributes()) {
                AttributeModifier newModifier = new AttributeModifier(
                    material.getInfusionUUID(addition, slot),
                    "Infusion modifier",
                    holder.amount,
                    holder.operation
                );
    
                if (holder.attribute.equals(Attributes.ATTACK_DAMAGE) || holder.attribute.equals(Attributes.ATTACK_SPEED)) {
                    Collection<AttributeModifier> existingModifiers = mergedModifiers.get(holder.attribute);
                    AttributeModifier toReplace = null;
    
                    for (AttributeModifier existingModifier : existingModifiers) {
                        if (existingModifier.getId().equals(newModifier.getId())) {
                            toReplace = existingModifier;
                            break;
                        }
                    }
    
                    if (toReplace != null) {
                        mergedModifiers.remove(holder.attribute, toReplace);
                        mergedModifiers.put(holder.attribute, new AttributeModifier(
                            toReplace.getId(),
                            toReplace.getName(),
                            toReplace.getAmount() + newModifier.getAmount(),
                            toReplace.getOperation()
                        ));
                    } else {
                        mergedModifiers.put(holder.attribute, newModifier);
                    }
                } else {
                    mergedModifiers.put(holder.attribute, newModifier);
                }
            }
    
            for (var entry : mergedModifiers.entries()) {
                Attribute attribute = entry.getKey();
                AttributeModifier modifier = entry.getValue();
    
                potentialResult.addAttributeModifier(attribute, modifier, slot);
            }
    
            ths.getSlot(3).set(potentialResult);
    
            ci.cancel();
        }
    }
}
