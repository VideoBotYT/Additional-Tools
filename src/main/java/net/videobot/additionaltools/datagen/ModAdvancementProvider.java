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
import net.videobot.additionaltools.item.ModTools;

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
            generateVoid(registries, saver, existingFileHelper);
            generateTools(registries, saver, existingFileHelper);
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
        private void generateVoid(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            Advancement.Builder builder = new Advancement.Builder();

            builder.parent(AdditionalToolsMod.res("ancient_notes/root"));

            builder.display(
                    new ItemStack(ModItems.VOID_DUST.get()),
                    Component.translatable("advancements.additionaltools.voidstarter.title"),
                    Component.translatable("advancements.additionaltools.voidstarter.description"),
                    null,
                    AdvancementType.GOAL,
                    true,
                    true,
                    false
            );
            builder.rewards(AdvancementRewards.Builder.experience(50));
            builder.addCriterion("has_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.VOID_DUST));
            builder.requirements(AdvancementRequirements.allOf(List.of("has_dust")));
            builder.save(saver, AdditionalToolsMod.res("ancient_notes/voidstarter"), existingFileHelper);

            Advancement.Builder.advancement().parent(AdditionalToolsMod.res("ancient_notes/voidstarter"))
                    .display(
                            new ItemStack(ModItems.VOID_INGOT.get()),
                            Component.translatable("advancements.additionaltools.void.title"),
                            Component.translatable("advancements.additionaltools.void.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    ).rewards(AdvancementRewards.Builder.experience(69))
                    .addCriterion("has_void", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.VOID_INGOT))
                    .requirements(AdvancementRequirements.allOf(List.of("has_void")))
                    .save(saver, AdditionalToolsMod.res("ancient_notes/void"), existingFileHelper);
        }
        private void generateTools(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            Advancement.Builder.advancement().parent(AdditionalToolsMod.res("ancient_notes/crystalizer"))
                    .display(
                            new ItemStack(ModTools.ECHO_SWORD.get()),
                            Component.translatable("advancements.additionaltools.echosword.title"),
                            Component.translatable("advancements.additionaltools.echosword.description"),
                            null,
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    ).rewards(AdvancementRewards.Builder.experience(4))
                    .addCriterion("has_echo_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModTools.ECHO_SWORD))
                    .requirements(AdvancementRequirements.allOf(List.of("has_echo_sword")))
                    .save(saver, AdditionalToolsMod.res("ancient_notes/echo_sword"), existingFileHelper);Advancement.Builder.advancement().parent(AdditionalToolsMod.res("ancient_notes/crystalizer"))
                    .display(
                            new ItemStack(ModTools.VOID_PICKAXE.get()),
                            Component.translatable("advancements.additionaltools.voidpickaxe.title"),
                            Component.translatable("advancements.additionaltools.voidpickaxe.description"),
                            null,
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    ).rewards(AdvancementRewards.Builder.experience(4))
                    .addCriterion("has_void_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModTools.VOID_PICKAXE))
                    .requirements(AdvancementRequirements.allOf(List.of("has_void_pickaxe")))
                    .save(saver, AdditionalToolsMod.res("ancient_notes/void_pickaxe"), existingFileHelper);
        }
    }
}
