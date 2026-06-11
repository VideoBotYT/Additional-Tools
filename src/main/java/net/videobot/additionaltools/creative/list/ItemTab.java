package net.videobot.additionaltools.creative.list;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.item.ModItems;

import java.util.function.Supplier;

public class ItemTab {
    public static final DeferredRegister<CreativeModeTab> ITEMTAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AdditionalToolsMod.MODID);

    public static final Supplier<CreativeModeTab> ADDITIONAL_ITEMS = ITEMTAB.register("additional_items",
            () -> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.RAW_ECHO.get()))
                    .title(Component.translatable("creativetab.additionaltools.additional_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_ECHO.get());
                        output.accept(ModItems.CRYSTAL_ECHO.get());
                        output.accept(ModItems.ECHO_TEMPLATE.get());
                        output.accept(ModItems.VOID_DUST.get());
                        output.accept(ModItems.VOID_INGOT.get());
                    }).build());
}
