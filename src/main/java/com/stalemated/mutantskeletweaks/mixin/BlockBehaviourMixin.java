package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.IMutantSkullData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import fuzs.mutantmonsters.world.item.ArmorBlockItem;
import java.util.List;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @Inject(
            method = "getDrops",
            at = @At("RETURN")
    )
    private void restoreAllTagsToSkullDrop(BlockState state, LootParams.Builder params, CallbackInfoReturnable<List<ItemStack>> cir) {
        BlockEntity be = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);

        if (be instanceof IMutantSkullData data && data.mutant_skeleton_armor_tweaks$getMutantSkullTags() != null) {
            List<ItemStack> drops = cir.getReturnValue();

            // Create the drop for the skull
            for (ItemStack drop : drops) {
                if (drop.getItem() instanceof ArmorBlockItem) {
                    drop.getOrCreateTag().merge(data.mutant_skeleton_armor_tweaks$getMutantSkullTags());
                }
            }
        }
    }
}