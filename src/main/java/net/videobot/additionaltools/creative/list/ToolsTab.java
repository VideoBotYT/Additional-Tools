package net.videobot.additionaltools.creative.list;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.item.ModItems;

import java.util.function.Supplier;

public class ToolsTab {
    public static final DeferredRegister<CreativeModeTab> TOOLSTAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AdditionalToolsMod.MODID);

    public static final Supplier<CreativeModeTab> ADDITIONAL_TOOLS = TOOLSTAB.register("additional_tools",
            () -> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.ECHO_SWORD.get()))
                    .title(Component.translatable("creativetab.additionaltools.additional_tools"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ECHO_SWORD.get());
                    }).build());
}
