package net.videobot.additionaltools.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.recipe.crystal_upgrader.CrystalUpgraderRecipe;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, AdditionalToolsMod.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, AdditionalToolsMod.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CrystalUpgraderRecipe>> CRYSTAL_UPGRADER_RECIPE_SERIALIZER =
            SERIALIZERS.register("crystal_upgrader", CrystalUpgraderRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<CrystalUpgraderRecipe>> CRYSTAL_UPGRADER_RECIPE_TYPE =
            TYPES.register("crystal_upgrader", () -> new RecipeType<CrystalUpgraderRecipe>() {
                @Override
                public String toString() {
                    return "crystal_upgrader";
                }
            });

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
