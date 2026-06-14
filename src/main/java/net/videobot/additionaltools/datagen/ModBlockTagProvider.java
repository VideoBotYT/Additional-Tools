package net.videobot.additionaltools.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AdditionalToolsMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAW_ECHO_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ECHO_ORE.get())
                .add(ModBlocks.VOID_ORE.get())
                .add(ModBlocks.CRYSTAL_UPGRADER.get())
                .add(ModBlocks.VOID_BLOCK.get())
                .add(ModBlocks.ECHO_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ECHO_ORE.get())
                .add(ModBlocks.VOID_ORE.get())
                .add(ModBlocks.CRYSTAL_UPGRADER.get())
                .add(ModBlocks.VOID_BLOCK.get())
                .add(ModBlocks.ECHO_BLOCK.get());

        tag(ModTags.Blocks.NEEDS_ECHO_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_ECHO_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_ECHO_TOOL);

        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOID_TOOL);

        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOID_TOOL);

        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
            .addTag(ModTags.Blocks.NEEDS_VOID_TOOL);

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOID_TOOL);

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOID_TOOL);

        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOID_TOOL);

        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOID_TOOL);

        tag(ModTags.Blocks.NEEDS_VOID_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_VOID_TOOL)
                .remove(ModTags.Blocks.INCORRECT_FOR_ECHO_TOOL)
                .remove(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.WALLS)
                .add(ModBlocks.ECHO_WALL.get())
                .add(ModBlocks.VOID_WALL.value());
    }
}
