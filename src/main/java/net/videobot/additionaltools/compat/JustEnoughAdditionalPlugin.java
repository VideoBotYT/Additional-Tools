package net.videobot.additionaltools.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.recipe.ModRecipes;
import net.videobot.additionaltools.recipe.crystal_upgrader.CrystalUpgraderRecipe;
import net.videobot.additionaltools.screen.ModMenuTypes;
import net.videobot.additionaltools.screen.custom.CrystalUpgraderMenu;
import net.videobot.additionaltools.screen.custom.CrystalUpgraderScreen;

import java.util.List;

@JeiPlugin
public class JustEnoughAdditionalPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrystalRecipeCat(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<CrystalUpgraderRecipe> crystalUpgraderRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.CRYSTAL_UPGRADER_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(CrystalRecipeCat.CRYSTAL_UPGRADER_RECIPE_TYPE, crystalUpgraderRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrystalUpgraderScreen.class, 52, 38, 72, 19,
                CrystalRecipeCat.CRYSTAL_UPGRADER_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRYSTAL_UPGRADER),
                CrystalRecipeCat.CRYSTAL_UPGRADER_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CrystalUpgraderMenu.class, ModMenuTypes.CRYSTAL_UPGRADER_MENU.get(),
                CrystalRecipeCat.CRYSTAL_UPGRADER_RECIPE_TYPE, 0, 3, 4, 36);
    }
}
