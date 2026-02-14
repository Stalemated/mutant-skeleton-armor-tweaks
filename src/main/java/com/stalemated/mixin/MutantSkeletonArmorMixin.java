package com.stalemated.mixin;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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
		if (instance.is(RANGED_WEAPON_TAG) || checkedItem == Items.CROSSBOW) {
			return Items.BOW;
		}
		return checkedItem;
	}
}