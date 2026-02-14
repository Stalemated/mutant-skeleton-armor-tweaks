package com.stalemated.mixin;

import com.stalemated.MutantSkeletonArmorConfig;
import fuzs.mutantmonsters.init.ModRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "fuzs.mutantmonsters.handler.PlayerEventsHandler")
public class MutantSkeletonArmorMixin {
	@Unique
	private static final TagKey<Item> RANGED_WEAPON_TAG = TagKey.create(Registries.ITEM, new ResourceLocation("c", "ranged_weapons"));

	@Redirect(
			method = "onItemUseTick",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;",
					ordinal = 1 // useItem.getItem() check
			)
	)
	private static Item allowRangedWeaponsInCheck(ItemStack instance) {
		Item checkedItem = instance.getItem();

		// Draw speed feature disabled
		if (!MutantSkeletonArmorConfig.enableChestplateDrawSpeed) {
			return Items.AIR;
		}
		// Crossbow feature disabled check
		if (!MutantSkeletonArmorConfig.enableChestplateCrossbowTweak) {
			return checkedItem;
		}

		if (instance.is(RANGED_WEAPON_TAG) || checkedItem == Items.CROSSBOW) {
			return Items.BOW;
		}
		return checkedItem;
	}


	@Redirect(
			method = "onArrowLoose",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/player/Player;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"
			)
	)
	private static ItemStack disableSkullMultishot(Player player, EquipmentSlot slot) {
		ItemStack realItemStack = player.getItemBySlot(slot.HEAD);

		if (player.getItemBySlot(slot.HEAD).getItem() == ModRegistry.MUTANT_SKELETON_SKULL_ITEM.get() && !MutantSkeletonArmorConfig.enableSkullMultishot) {
			return ItemStack.EMPTY;
		}

		return realItemStack;
	}
}