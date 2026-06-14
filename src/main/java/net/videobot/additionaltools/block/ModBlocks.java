package net.videobot.additionaltools.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.custom.CrystalUpgraderBlock;
import net.videobot.additionaltools.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AdditionalToolsMod.MODID);

    public static final DeferredBlock<Block> ECHO_ORE = registerBlock("echo_ore",
            () -> new DropExperienceBlock(UniformInt.of(4, 8),
                    BlockBehaviour.Properties.of().strength(3f, 8f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> VOID_ORE = registerBlock("void_ore",
            () -> new DropExperienceBlock(UniformInt.of(10, 20),
                    BlockBehaviour.Properties.of().strength(6f, 2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> CRYSTAL_UPGRADER = registerBlock("crystal_upgrader",
            () -> new CrystalUpgraderBlock(BlockBehaviour.Properties.of().strength(4f, 6f).requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(CrystalUpgraderBlock.ACTIVE) ? 15 : 0)));

    public static final DeferredBlock<Block> VOID_BLOCK = registerBlock("void_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(8f, 4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> ECHO_BLOCK = registerBlock("echo_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f, 6f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> RAW_ECHO_BLOCK = registerBlock("raw_echo_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f, 2f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));

    public static final DeferredBlock<StairBlock> ECHO_STAIRS = registerBlock("echo_stairs",
            () -> new StairBlock(ModBlocks.ECHO_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> ECHO_SLAB = registerBlock("echo_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> ECHO_WALL = registerBlock("echo_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<DoorBlock> ECHO_DOOR = registerBlock("echo_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredBlock<ButtonBlock> ECHO_BUTTON = registerBlock("echo_button",
            () -> new ButtonBlock(BlockSetType.IRON, 40, BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().noCollission()));

    public static final DeferredBlock<StairBlock> VOID_STAIRS = registerBlock("void_stairs",
            () -> new StairBlock(ModBlocks.VOID_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> VOID_SLAB = registerBlock("void_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> VOID_WALL = registerBlock("void_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(20f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<DoorBlock> VOID_DOOR = registerBlock("void_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredBlock<ButtonBlock> VOID_BUTTON = registerBlock("void_button",
            () -> new ButtonBlock(BlockSetType.IRON, 10, BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().noCollission()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
