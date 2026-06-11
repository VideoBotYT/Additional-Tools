package net.videobot.additionaltools.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.videobot.additionaltools.AdditionalToolsMod;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_ECHO_TOOL = createTag("needs_echo_tool");
        public static final TagKey<Block> INCORRECT_FOR_ECHO_TOOL = createTag("incorrect_for_echo_tool");

        public static final TagKey<Block> NEEDS_VOID_TOOL = createTag("needs_void_tool");
        public static final TagKey<Block> INCORRECT_FOR_VOID_TOOL = createTag("incorrect_for_void_tool");

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, name));
        }
    }

    public static class Items{
        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, name));
        }
    }
}
