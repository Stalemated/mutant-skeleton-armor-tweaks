package com.stalemated.mutantskeletweaks.handler;

import com.stalemated.mutantskeletweaks.config.ConfigManager;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static com.stalemated.mutantskeletweaks.registry.ArmorRegistry.skullItem;

public class ArmorEffectsHandler {
    private static final TagKey<Item> RANGED_WEAPON_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "ranged_weapons"));

    public static boolean shouldApplyEffect(ArmorItem.Type type) {
        if (type == ArmorItem.Type.BOOTS) {
            return ConfigManager.getActiveConfig().enableBootsEffect;
        } else if (type == ArmorItem.Type.LEGGINGS) {
            return ConfigManager.getActiveConfig().enableLeggingsEffect;
        }
        return true;
    }

    public static Item getDrawSpeedItem(ItemStack instance) {
        Item checkedItem = instance.getItem();

        if (!ConfigManager.getActiveConfig().enableChestplateDrawSpeed) {
            return Items.AIR;
        }

        if (!ConfigManager.getActiveConfig().enableChestplateCrossbowTweak) {
            return checkedItem;
        }

        if (instance.isIn(RANGED_WEAPON_TAG) || checkedItem == Items.CROSSBOW) {
            return Items.BOW;
        }

        return checkedItem;
    }

    public static ItemStack getSkullStack(PlayerEntity player) {
        ItemStack realItemStack = player.getEquippedStack(EquipmentSlot.HEAD);

        if (realItemStack.isOf(skullItem) && ConfigManager.getActiveConfig().enableSkullMultishot) {
            return ItemStack.EMPTY;
        }

        return realItemStack;
    }
}
