package net.videobot.additionaltools.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AdditionalToolsMod.MODID);

    public static final Supplier<BlockEntityType<CrystalUpgraderBlockEntity>> CRYSTAL_UPGRADER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("crystal_upgrader_block_entity", () -> BlockEntityType.Builder.of(
                    CrystalUpgraderBlockEntity::new, ModBlocks.CRYSTAL_UPGRADER.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
