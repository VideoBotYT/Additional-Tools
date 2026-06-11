package net.videobot.additionaltools.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.videobot.additionaltools.AdditionalToolsMod;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_ECHO_ORE = registerKey("add_echo_ore");
    public static final ResourceKey<BiomeModifier> ADD_VOID_ORE = registerKey("add_void_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_ECHO_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_ANCIENT_CITY),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ECHO_ORE_PLACE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_VOID_ORE, new  BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.VOID_ORE_PLACE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, name));
    }
}
