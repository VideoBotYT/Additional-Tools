package net.videobot.additionaltools.recipe.crystal_upgrader;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.videobot.additionaltools.recipe.ModRecipes;

public record CrystalUpgraderRecipe(Ingredient template, Ingredient tool, Ingredient ingredient, ItemStack output, int craftingTime) implements Recipe<CrystalUpgraderRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.template);
        list.add(this.tool);
        list.add(this.ingredient);
        return list;
    }

    @Override
    public boolean matches(CrystalUpgraderRecipeInput input, Level level) {
        if (level.isClientSide())
            return false;

        return template.test(input.getItem(0)) && tool.test(input.getItem(1)) && ingredient.test(input.getItem(2));
    }

    @Override
    public ItemStack assemble(CrystalUpgraderRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRYSTAL_UPGRADER_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CRYSTAL_UPGRADER_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<CrystalUpgraderRecipe> {
        public static final MapCodec<CrystalUpgraderRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("template").forGetter(CrystalUpgraderRecipe::template),
                Ingredient.CODEC_NONEMPTY.fieldOf("tool").forGetter(CrystalUpgraderRecipe::tool),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CrystalUpgraderRecipe::ingredient),
                ItemStack.CODEC.fieldOf("result").forGetter(CrystalUpgraderRecipe::output),
                Codec.INT.fieldOf("craftingTime").orElse(80).forGetter(CrystalUpgraderRecipe::craftingTime)
        ).apply(inst, CrystalUpgraderRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CrystalUpgraderRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, CrystalUpgraderRecipe::template,
                        Ingredient.CONTENTS_STREAM_CODEC, CrystalUpgraderRecipe::tool,
                        Ingredient.CONTENTS_STREAM_CODEC, CrystalUpgraderRecipe::ingredient,
                        ItemStack.STREAM_CODEC, CrystalUpgraderRecipe::output,
                        ByteBufCodecs.INT, CrystalUpgraderRecipe::craftingTime,
                        CrystalUpgraderRecipe::new);

        @Override
        public MapCodec<CrystalUpgraderRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CrystalUpgraderRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
