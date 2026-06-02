package net.videobot.additionaltools.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.videobot.additionaltools.AdditionalToolsMod;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;

public class SlotProvider extends CuriosDataProvider {
    public SlotProvider(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(AdditionalToolsMod.MODID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        createSlot("toolsback")
                .icon(ResourceLocation.fromNamespaceAndPath("curios", "slot/empty_back_slot"))
                .size(1)
                .dropRule(ICurio.DropRule.ALWAYS_DROP)
                .addValidator(ResourceLocation.fromNamespaceAndPath("curios", "tag"));

        createEntities("slots")
                .addPlayer()
                .addSlots("toolsback");
    }
}
