package com.stalemated.mutantskeletweaks.handler;

import fuzs.mutantmonsters.world.level.block.entity.SkullWithItemTagBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;

import java.util.List;

import static com.stalemated.mutantskeletweaks.registry.ArmorRegistry.skullItem;

public class SkullDropHandler {
    
    public static void processSkullDrop(LootContextParameterSet.Builder builder, List<ItemStack> drops) {
        BlockEntity be = builder.getOptional(LootContextParameters.BLOCK_ENTITY);

        if (be instanceof SkullWithItemTagBlockEntity skullBe) {
            NbtCompound blockEntityNbt = skullBe.createNbt();

            if (blockEntityNbt.contains("ItemTag", 10)) {
                putNbtOnDrop(blockEntityNbt, drops);
            }
        }
    }

    private static void putNbtOnDrop(NbtCompound compoundTag, List<ItemStack> drops) {
        NbtCompound itemTag = compoundTag.getCompound("ItemTag");

        for (ItemStack drop : drops) {
            if (drop.isOf(skullItem)) {
                drop.getOrCreateNbt().copyFrom(itemTag);
            }
        }
    }
}
