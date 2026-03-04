package com.stalemated.mutantskeletweaks.mixin.compat;

import com.google.common.collect.Multimap;
import fuzs.mutantmonsters.world.item.ArmorBlockItem;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import static com.stalemated.mutantskeletweaks.MutantSkeletonArmorTweaks.LOGGER;

@Mixin(value = ItemCombinerMenu.class, priority = 1500)
public abstract class SmithingMenuEldritchCompatMixin {

    @Inject(method = "slotsChanged", at = @At("RETURN"))
    private void fixMutantSkeleSkullInfusion(Container inventory, CallbackInfo ci) {

        if (!FabricLoader.getInstance().isModLoaded("eldritch_end")) {
            return;
        }
        if ((Object) this instanceof SmithingMenu menu) {
            ItemStack item = menu.getSlot(1).getItem();
            ItemStack result = menu.getSlot(3).getItem();

            if (!result.isEmpty() && result.hasTag() && result.getItem() instanceof ArmorBlockItem) {

                EquipmentSlot correctSlot = Mob.getEquipmentSlotForItem(result);

                if (correctSlot != null && correctSlot != EquipmentSlot.MAINHAND) {
                    ListTag modifiers = result.getTag().getList("AttributeModifiers", Tag.TAG_COMPOUND);
                    boolean changed = false;

                    // Iterate through all item modifiers
                    for (int i = 0; i < modifiers.size(); i++) {
                        CompoundTag modifier = modifiers.getCompound(i);

                        // Correct wrongly assigned mainhand slot to head slot
                        if ("mainhand".equals(modifier.getString("Slot"))) {
                            modifier.putString("Slot", correctSlot.getName());
                            changed = true;
                        }
                    }

                    // Restore old attributes
                    if (changed) {
                        Multimap<Attribute, AttributeModifier> attributeModifiers = item.getItem().getAttributeModifiers(item, correctSlot); //

                        // Iterate through all default attributes
                        for (Map.Entry<Attribute, AttributeModifier> entry : attributeModifiers.entries()) {
                            boolean hasAttribute = false;
                            String attributeName = BuiltInRegistries.ATTRIBUTE.getKey(entry.getKey()).toString();

                            // Iterate through all item modifiers
                            for (int i = 0; i < modifiers.size(); i++) {
                                CompoundTag mod = modifiers.getCompound(i);
                                if (mod.getString("AttributeName").equals(attributeName) && mod.getString("Name").equals(entry.getValue().getName())) {
                                    hasAttribute = true;
                                    break;
                                }
                            }

                            if (!hasAttribute) {
                                LOGGER.info("Restoring attribute: {}", attributeName);
                                CompoundTag newModifiers = getCompoundTag(entry, attributeName, correctSlot);

                                modifiers.add(newModifiers);
                            }
                        }

                        result.getTag().put("AttributeModifiers", modifiers);
                    }
                }
            }
        }
    }

    @Unique
    private static @NotNull CompoundTag getCompoundTag(Map.Entry<Attribute, AttributeModifier> entry, String attrName, EquipmentSlot correctSlot) {
        CompoundTag newModifiers = new CompoundTag();
        newModifiers.putString("AttributeName", attrName);
        newModifiers.putString("Name", entry.getValue().getName());
        newModifiers.putDouble("Amount", entry.getValue().getAmount());
        newModifiers.putInt("Operation", entry.getValue().getOperation().toValue());
        newModifiers.putUUID("UUID", entry.getValue().getId());
        newModifiers.putString("Slot", correctSlot.getName());
        return newModifiers;
    }
}