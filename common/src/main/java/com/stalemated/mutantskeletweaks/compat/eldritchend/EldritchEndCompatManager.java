package com.stalemated.mutantskeletweaks.compat.eldritchend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.Slot;

import java.util.Collection;

import static com.stalemated.mutantskeletweaks.registry.ArmorRegistry.skullItem;

public class EldritchEndCompatManager {
    public static boolean applyInfusion(ItemStack base, ItemStack candidate, ItemStack addition, Slot outputSlot) {
        if (addition.isEmpty() || base.isEmpty() || candidate.isEmpty()) return false;

        if (!candidate.isOf(skullItem)) return false;

        if (addition.getItem() instanceof InfusableItemMaterial material) {
            if (material.getInfusionTemplate() != base.getItem()) return false;
            if (!material.applyToArmor() || !material.isInfusable()) return false;

            NbtCompound existingInfusion = candidate.copy().getOrCreateSubNbt("eldritch_infusions");
            boolean isAlreadyInfused = existingInfusion.getBoolean("isInfused");

            if (isAlreadyInfused && !material.canSwapInfusionTo().contains(existingInfusion.getString("materialIdentifier"))) return false;

            ItemStack potentialResult = candidate.copy();
            NbtCompound nbt = potentialResult.getOrCreateSubNbt("eldritch_infusions");

            nbt.putBoolean("isInfused", true);
            nbt.putString("currentInfusion", Registries.ITEM.getId(addition.getItem()).getPath());
            nbt.putString("materialIdentifier", Registries.ITEM.getId(addition.getItem()).toString());

            /* Future implementation (Eldritch End natively handles attribute scaling and dynamic changes via tag)
            *  Probably will need to redo the entire API over there to abstract everything
            *  away and just have items be able to get registered as infusable
            */
            // nbt.putString("infusionSlot", EquipmentSlot.HEAD.getName());

            oldInfusionImpl(potentialResult, material, addition);

            outputSlot.setStack(potentialResult);
            return true;
        }

        return false;
    }

    public static void oldInfusionImpl(ItemStack potentialResult, InfusableItemMaterial material, ItemStack addition) {
        EquipmentSlot slot = EquipmentSlot.HEAD;

        Multimap<EntityAttribute, EntityAttributeModifier> originalModifiers = HashMultimap.create();
        originalModifiers.putAll(potentialResult.getAttributeModifiers(slot));

        Multimap<EntityAttribute, EntityAttributeModifier> mergedModifiers = HashMultimap.create(originalModifiers);

        for (var holder : material.getInfusionAttributes()) {
            EntityAttributeModifier newModifier = new EntityAttributeModifier(
                    material.getInfusionUUID(addition, slot),
                    "Infusion modifier",
                    holder.amount,
                    holder.operation
            );

            if (holder.attribute.equals(EntityAttributes.GENERIC_ATTACK_DAMAGE) || holder.attribute.equals(EntityAttributes.GENERIC_ATTACK_SPEED)) {
                Collection<EntityAttributeModifier> existingModifiers = mergedModifiers.get(holder.attribute);
                EntityAttributeModifier toReplace = null;

                for (EntityAttributeModifier existingModifier : existingModifiers) {
                    if (existingModifier.getId().equals(newModifier.getId())) {
                        toReplace = existingModifier;
                        break;
                    }
                }

                if (toReplace != null) {
                    mergedModifiers.remove(holder.attribute, toReplace);
                    mergedModifiers.put(holder.attribute, new EntityAttributeModifier(
                            toReplace.getId(),
                            toReplace.getName(),
                            toReplace.getValue() + newModifier.getValue(),
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
            EntityAttribute attribute = entry.getKey();
            EntityAttributeModifier modifier = entry.getValue();

            potentialResult.addAttributeModifier(attribute, modifier, slot);
        }
    }
}
