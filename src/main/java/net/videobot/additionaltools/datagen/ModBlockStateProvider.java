package net.videobot.additionaltools.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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

        ResourceLocation crystal_upgrader_side = ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "block/crystal_upgrader/crystal_upgrader_side");
        ResourceLocation crystal_upgrader_top = ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "block/crystal_upgrader/crystal_upgrader_top");
        ResourceLocation crystal_upgrader_front = ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "block/crystal_upgrader/crystal_upgrader_front");

        horizontalBlock(ModBlocks.CRYSTAL_UPGRADER.get(), crystal_upgrader_side, crystal_upgrader_front, crystal_upgrader_top);
        blockItem(ModBlocks.CRYSTAL_UPGRADER);
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
