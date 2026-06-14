package net.videobot.additionaltools.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AdditionalToolsMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ECHO_ORE);
        blockWithItem(ModBlocks.VOID_ORE);
        blockWithItem(ModBlocks.VOID_BLOCK);
        blockWithItem(ModBlocks.ECHO_BLOCK);
        blockWithItem(ModBlocks.RAW_ECHO_BLOCK);
        blockItem(ModBlocks.CRYSTAL_UPGRADER);

        stairsBlock(ModBlocks.ECHO_STAIRS.get(), blockTexture(ModBlocks.ECHO_BLOCK.get()));
        slabBlock(ModBlocks.ECHO_SLAB.get(), blockTexture(ModBlocks.ECHO_BLOCK.get()), blockTexture(ModBlocks.ECHO_BLOCK.get()));
        wallBlock(ModBlocks.ECHO_WALL.get(), blockTexture(ModBlocks.ECHO_BLOCK.get()));

        buttonBlock(ModBlocks.ECHO_BUTTON.get(), blockTexture(ModBlocks.ECHO_BLOCK.get()));

        stairsBlock(ModBlocks.VOID_STAIRS.get(), blockTexture(ModBlocks.VOID_BLOCK.get()));
        slabBlock(ModBlocks.VOID_SLAB.get(), blockTexture(ModBlocks.VOID_BLOCK.get()), blockTexture(ModBlocks.VOID_BLOCK.get()));
        wallBlock(ModBlocks.VOID_WALL.get(), blockTexture(ModBlocks.VOID_BLOCK.get()));

        buttonBlock(ModBlocks.VOID_BUTTON.get(), blockTexture(ModBlocks.VOID_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.ECHO_DOOR.get(), modLoc("block/echo_door_bottom"), modLoc("block/echo_door_top"), "cutout");
        doorBlockWithRenderType(ModBlocks.VOID_DOOR.get(), modLoc("block/void_door_bottom"), modLoc("block/void_door_top"), "cutout");

        blockItem(ModBlocks.ECHO_SLAB);
        blockItem(ModBlocks.ECHO_STAIRS);
        blockItem(ModBlocks.VOID_SLAB);
        blockItem(ModBlocks.VOID_STAIRS);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("additionaltools:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("additionaltools:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
