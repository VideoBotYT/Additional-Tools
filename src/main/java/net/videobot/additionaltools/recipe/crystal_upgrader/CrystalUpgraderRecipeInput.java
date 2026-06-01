package net.videobot.additionaltools.recipe.crystal_upgrader;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record CrystalUpgraderRecipeInput(ItemStack template, ItemStack tool, ItemStack ingredient) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch(index){
            case 0 -> this.template ;
            case 1 -> this.tool;
            case 2 -> this.ingredient;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
        };
    }

    @Override
    public int size() {
        return 3;
    }
}
