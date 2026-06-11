package net.videobot.additionaltools.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.videobot.additionaltools.AdditionalToolsMod;
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

        handheldItem(ModTools.ECHO_SWORD.get());
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "item/" + item.getId().getPath()));
    }
}
