package net.videobot.additionaltools.datagen.builders;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.recipe.crystal_upgrader.CrystalUpgraderRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

public class CrystalRecipeBuilder {
    private final Item template;
    private final Item tool;
    private final Item ingredient;
    private final Item result;
    private final int craftTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public CrystalRecipeBuilder(Item template, Item tool, Item ingredient, Item result, int time) {
        this.template = template;
        this.tool = tool;
        this.ingredient = ingredient;
        this.result = result;
        this.craftTime = time;
    }

    public static CrystalRecipeBuilder crystalize(Item template, Item tool, Item ingredient, Item result, int time) {
        return new CrystalRecipeBuilder(template, tool, ingredient, result, time);
    }

    public CrystalRecipeBuilder unlocks(String key, Criterion<?> criterion) {
        this.criteria.put(key, criterion);
        return this;
    }

    public void save(RecipeOutput recipeOutput, String recipeId) {
        this.save(recipeOutput, ResourceLocation.parse(AdditionalToolsMod.MODID + ":" + recipeId));
    }

    private void save(RecipeOutput recipeOutput, ResourceLocation recipeId) {
        this.ensureValid(recipeId);
        Advancement.Builder advancementbuilder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancementbuilder::addCriterion);
        CrystalUpgraderRecipe recipe = new CrystalUpgraderRecipe(Ingredient.of(this.template), Ingredient.of(this.tool), Ingredient.of(this.ingredient), new ItemStack(this.result), this.craftTime);
        recipeOutput.accept(recipeId, recipe, advancementbuilder.build(recipeId.withPrefix("recipes/crystalizer/")));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }
}
