package net.videobot.additionaltools.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.recipe.crystal_upgrader.CrystalUpgraderRecipe;
import org.jetbrains.annotations.Nullable;

public class CrystalRecipeCat implements IRecipeCategory<CrystalUpgraderRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "crystal_upgrader");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID,
            "textures/gui/crystal_upgrader/crystal_upgrader_gui.png");

    public static final RecipeType<CrystalUpgraderRecipe> CRYSTAL_UPGRADER_RECIPE_TYPE =
            new RecipeType<>(UID, CrystalUpgraderRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CrystalRecipeCat(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 80);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRYSTAL_UPGRADER));
    }

    @Override
    public RecipeType<CrystalUpgraderRecipe> getRecipeType() {
        return CRYSTAL_UPGRADER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.CRYSTAL_UPGRADER.get().getName();
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrystalUpgraderRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44, 21).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 21).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 116, 21).addIngredients(recipe.getIngredients().get(2));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 61).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return background;
    }
}
