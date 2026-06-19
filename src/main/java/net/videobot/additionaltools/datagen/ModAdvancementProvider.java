package net.videobot.additionaltools.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new NotesAdvancement()));
    }

    private static final class NotesAdvancement implements AdvancementProvider.AdvancementGenerator{

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            generateRoot(registries, saver, existingFileHelper);
            generateEcho(registries, saver, existingFileHelper);
        }

        private void generateRoot(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            Advancement.Builder builder = new Advancement.Builder();

            builder.display(
                    new ItemStack(ModItems.ANCIENT_NOTES_FROM_FORGE.get()),
                    Component.translatable("advancements.additionaltools.notes.title"),
                    Component.translatable("advancements.additionaltools.notes.description"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/advancements/backgrounds/stone.png"),
                    AdvancementType.GOAL,
                    true,
                    true,
                    false
            );

            builder.rewards(AdvancementRewards.Builder.experience(10));

            builder.addCriterion("has_notes", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ANCIENT_NOTES_FROM_FORGE));
            builder.requirements(AdvancementRequirements.allOf(List.of("has_notes")));
            builder.save(saver, AdditionalToolsMod.res("ancient_notes/root"), existingFileHelper);
        }
        private void generateEcho(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            Advancement.Builder builder = new Advancement.Builder();

            builder.parent(AdditionalToolsMod.res("ancient_notes/root"));

            builder.display(
                    new ItemStack(ModItems.CRYSTAL_ECHO.get()),
                    Component.translatable("advancements.additionaltools.echo.title"),
                    Component.translatable("advancements.additionaltools.echo.description"),
                    null,
                    AdvancementType.GOAL,
                    true,
                    true,
                    false
            );

            builder.rewards(AdvancementRewards.Builder.experience(10));

            builder.addCriterion("has_echo", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRYSTAL_ECHO));
            builder.requirements(AdvancementRequirements.allOf(List.of("has_echo")));
            builder.save(saver, AdditionalToolsMod.res("ancient_notes/echo"), existingFileHelper);

            Advancement.Builder.advancement().parent(AdditionalToolsMod.res("ancient_notes/echo")).display(
                    new ItemStack(ModBlocks.CRYSTAL_UPGRADER.asItem()),
                    Component.translatable("advancements.additionaltools.crystalizer.title"),
                    Component.translatable("advancements.additionaltools.crystalizer.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    false
            ).rewards(AdvancementRewards.Builder.experience(100))
                    .addCriterion("has_crystalizer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.CRYSTAL_UPGRADER.asItem()))
                    .requirements(AdvancementRequirements.allOf(List.of("has_crystalizer")))
                    .save(saver, AdditionalToolsMod.res("ancient_notes/crystalizer"), existingFileHelper);
        }
    }
}
