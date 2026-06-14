package net.videobot.additionaltools.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.item.ModItems;
import net.videobot.additionaltools.item.ModTools;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AdditionalToolsMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_ECHO.get());
        basicItem(ModItems.CRYSTAL_ECHO.get());
        basicItem(ModItems.ECHO_TEMPLATE.get());
        basicItem(ModItems.VOID_DUST.get());
        basicItem(ModItems.VOID_INGOT.get());
        basicItem(ModItems.VOID_UPGRADE_TEMPLATE.get());

        handheldItem(ModTools.ECHO_SWORD.get());
        handheldItem(ModTools.VOID_PICKAXE.get());

        buttonItem(ModBlocks.ECHO_BUTTON, ModBlocks.ECHO_BLOCK);
        wallItem(ModBlocks.ECHO_WALL, ModBlocks.ECHO_BLOCK);

        basicItem(ModBlocks.ECHO_DOOR.asItem());

        buttonItem(ModBlocks.VOID_BUTTON, ModBlocks.VOID_BLOCK);
        wallItem(ModBlocks.VOID_WALL, ModBlocks.VOID_BLOCK);

        basicItem(ModBlocks.VOID_DOOR.asItem());
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "item/" + item.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", AdditionalToolsMod.res("block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", AdditionalToolsMod.res("block/" + baseBlock.getId().getPath()));
    }
}
