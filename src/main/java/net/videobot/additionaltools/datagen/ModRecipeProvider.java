package net.videobot.additionaltools.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.datagen.builders.CrystalRecipeBuilder;
import net.videobot.additionaltools.item.ModItems;
import net.videobot.additionaltools.item.ModTools;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        blasting(recipeOutput, List.of(ModItems.RAW_ECHO.get(), ModBlocks.ECHO_ORE), RecipeCategory.TOOLS, ModItems.CRYSTAL_ECHO.get(), 4f, 800, "echo");
        blasting(recipeOutput, List.of(ModBlocks.RAW_ECHO_BLOCK.get()), RecipeCategory.TOOLS, ModBlocks.ECHO_BLOCK.get(), 50f, 2400, "echo");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ECHO_TEMPLATE.get())
                .pattern("EEE")
                .pattern("ETE")
                .pattern("EEE")
                .define('E', ModItems.CRYSTAL_ECHO.get())
                .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_echo", has(ModItems.CRYSTAL_ECHO.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRYSTAL_UPGRADER.get())
                .pattern("EAE")
                .pattern("ASA")
                .pattern("EAE")
                .define('E', ModItems.CRYSTAL_ECHO.get())
                .define('A', Items.AMETHYST_SHARD)
                .define('S', Blocks.SMITHING_TABLE)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOID_UPGRADE_TEMPLATE.get())
                .pattern("VAV")
                .pattern("ATA")
                .pattern("VAV")
                .define('V', ModItems.VOID_INGOT.get())
                .define('A', Items.AMETHYST_SHARD)
                .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VOID_INGOT.get())
                .requires(ModItems.VOID_DUST.get(), 4)
                .requires(Items.AMETHYST_SHARD, 2)
                .unlockedBy("has_void", has(ModItems.VOID_DUST.get())).save(recipeOutput);

        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.VOID_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_BLOCK.get().asItem());
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.CRYSTAL_ECHO, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHO_BLOCK.get().asItem());
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.RAW_ECHO, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ECHO_BLOCK.get().asItem());

        CrystalRecipeBuilder.crystalize(ModItems.ECHO_TEMPLATE.get(), Items.NETHERITE_SWORD, ModItems.CRYSTAL_ECHO.get(), ModTools.ECHO_SWORD.get(), 200)
                .unlocks("has_netherite_sword", has(Items.NETHERITE_SWORD)).save(recipeOutput, "echo_sword_crystalize");

        CrystalRecipeBuilder.crystalize(ModItems.VOID_UPGRADE_TEMPLATE.get(), Items.NETHERITE_PICKAXE, ModItems.VOID_INGOT.get(), ModTools.VOID_PICKAXE.get(), 800)
                .unlocks("has_netherite_pickaxe", has(Items.NETHERITE_PICKAXE)).save(recipeOutput, "void_pickaxe_crystalize");

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHO_BUTTON.asItem(), ModBlocks.ECHO_BLOCK.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHO_DOOR.asItem(), ModBlocks.ECHO_BLOCK.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHO_STAIRS.asItem(), ModBlocks.ECHO_BLOCK.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHO_WALL.asItem(), ModBlocks.ECHO_BLOCK.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHO_SLAB.asItem(), ModBlocks.ECHO_BLOCK.asItem(), 2);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_BUTTON.asItem(), ModBlocks.VOID_BLOCK.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_DOOR.asItem(), ModBlocks.VOID_BLOCK.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_STAIRS.asItem(), ModBlocks.VOID_BLOCK.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_WALL.asItem(), ModBlocks.VOID_BLOCK.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_SLAB.asItem(), ModBlocks.VOID_BLOCK.asItem(), 2);
    }

    protected static void smelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                   float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void blasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void cooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                    List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, AdditionalToolsMod.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void upgrade(RecipeOutput recipeOutput, Item template, Item upgradeItem, Item ingredientItem, Item resultItem,
                                  RecipeCategory category, String unlock, Item unlockItem){
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(ingredientItem), Ingredient.of(upgradeItem), category, resultItem)
                .unlocks(unlock, has(unlockItem))
                .save(recipeOutput, AdditionalToolsMod.MODID + ":" + getItemName(resultItem) + "_smithing");
    }
}
