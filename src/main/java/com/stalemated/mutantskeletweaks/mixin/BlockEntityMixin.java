package com.stalemated.mutantskeletweaks.mixin;

import com.stalemated.mutantskeletweaks.IMutantSkullData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntity.class)
public class BlockEntityMixin implements IMutantSkullData {

    @Unique
    private CompoundTag mutantSkullTags;

    @Override
    public CompoundTag mutant_skeleton_armor_tweaks$getMutantSkullTags() {
        return this.mutantSkullTags;
    }

    @Override
    public void mutant_skeleton_armor_tweaks$setMutantSkullTags(CompoundTag tag) {
        this.mutantSkullTags = tag;
        ((BlockEntity) (Object) this).setChanged();
    }

    @Inject(method = "load", at = @At("TAIL"))
    private void onLoadMutantSkullTags(@NotNull CompoundTag nbt, CallbackInfo ci) {
        if (nbt.contains("MutantSkullSavedTags")) {
            this.mutantSkullTags = nbt.getCompound("MutantSkullSavedTags");
        }
    }

    @Inject(method = "saveAdditional", at = @At("TAIL"))
    private void onSaveMutantSkullTags(CompoundTag nbt, CallbackInfo ci) {
        if (this.mutantSkullTags != null) {
            nbt.put("MutantSkullSavedTags", this.mutantSkullTags);
        }
    }
}