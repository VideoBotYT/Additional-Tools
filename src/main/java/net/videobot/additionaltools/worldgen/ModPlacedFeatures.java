package net.videobot.additionaltools.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.videobot.additionaltools.AdditionalToolsMod;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ECHO_ORE_PLACE_KEY = registerKey("echo_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ECHO_ORE_PLACE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ECHO_ORE_KEY),
                rareOrePlacementModifiers(9, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static List<PlacementModifier> orePlacementModifiers(PlacementModifier count, PlacementModifier heightRange) {
        return List.of(count, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacementModifiers(int count, PlacementModifier heightRange) {
        return orePlacementModifiers(CountPlacement.of(count), heightRange);
    }

    public static List<PlacementModifier> rareOrePlacementModifiers(int chance, PlacementModifier heightRange) {
        return orePlacementModifiers(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}
