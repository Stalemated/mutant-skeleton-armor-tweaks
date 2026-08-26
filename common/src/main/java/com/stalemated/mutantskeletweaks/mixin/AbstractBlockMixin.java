package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.handler.SkullDropHandler;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/loot/context/LootContextParameterSet$Builder;)Ljava/util/List;", at = @At("RETURN"))
    private void restoreAllTagsToSkullDrop(BlockState state, LootContextParameterSet.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
        SkullDropHandler.processSkullDrop(builder, cir.getReturnValue());
    }
}
