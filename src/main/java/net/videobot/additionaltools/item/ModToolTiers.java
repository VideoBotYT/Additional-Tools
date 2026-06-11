package net.videobot.additionaltools.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.videobot.additionaltools.util.ModTags;

public class ModToolTiers {
    public static final Tier ECHO = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_ECHO_TOOL,
            3000, 2f, 5f, 30, () -> Ingredient.of(ModItems.CRYSTAL_ECHO.get()));

    public static final Tier VOID = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_VOID_TOOL,
            3500, 2f, 6f, 30, () -> Ingredient.of(ModItems.VOID_INGOT.get()));
}
