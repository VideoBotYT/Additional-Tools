package net.videobot.additionaltools.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        blasting(recipeOutput, List.of(ModItems.RAW_ECHO.get(), ModBlocks.ECHO_ORE), RecipeCategory.TOOLS, ModItems.CRYSTAL_ECHO.get(), 4f, 800, "echo");

        upgrade(recipeOutput, ModItems.ECHO_TEMPLATE.get(), ModItems.CRYSTAL_ECHO.get(), Items.DIAMOND_SWORD, ModItems.ECHO_SWORD.get(), RecipeCategory.COMBAT, "has_echo", ModItems.CRYSTAL_ECHO.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ECHO_TEMPLATE.get())
                .pattern("EEE")
                .pattern("ETE")
                .pattern("EEE")
                .define('E', ModItems.CRYSTAL_ECHO.get())
                .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_echo", has(ModItems.CRYSTAL_ECHO.get())).save(recipeOutput);
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
