package net.videobot.additionaltools.creative.list;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;

import java.util.function.Supplier;

public class BlockTab {
    public static final DeferredRegister<CreativeModeTab> BLOCKTAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AdditionalToolsMod.MODID);

    public static final Supplier<CreativeModeTab> ADDITIONAL_BLOCKS = BLOCKTAB.register("additional_blocks",
            () -> CreativeModeTab.builder().icon(()->new ItemStack(ModBlocks.ECHO_ORE))
                    .title(Component.translatable("creativetab.additionaltools.additional_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ECHO_ORE.get());
                        output.accept(ModBlocks.CRYSTAL_UPGRADER.get());
                        output.accept(ModBlocks.VOID_ORE.get());
                    }).build());
}
