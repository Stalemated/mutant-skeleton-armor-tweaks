package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.IMutantSkullData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import fuzs.mutantmonsters.world.item.ArmorBlockItem;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(
            method = "updateCustomBlockEntityTag(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;)Z",
            at = @At("RETURN")
    )
    private void transferAllTagsToSkull(BlockPos pos, Level level, Player player, ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof ArmorBlockItem) {

            if (stack.hasTag()) {
                BlockEntity be = level.getBlockEntity(pos);

                // Injecting the NBT data
                if (be instanceof IMutantSkullData data) {
                    assert stack.getTag() != null;
                    data.mutant_skeleton_armor_tweaks$setMutantSkullTags(stack.getTag().copy());
                }
            }
        }
    }
}