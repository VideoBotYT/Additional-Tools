package net.videobot.additionaltools.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.item.ModItems;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, AdditionalToolsMod.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creativetab.additionaltools.additional_blocks", "Additional Blocks");
        add("creativetab.additionaltools.additional_items", "Additional Items");
        add("creativetab.additionaltools.additional_tools", "Additional Tools");

        // blocks
        add(ModBlocks.ECHO_ORE.get(), "Echo Ore");

        // items
        add(ModItems.RAW_ECHO.get(), "Raw Echo");
        add(ModItems.CRYSTAL_ECHO.get(), "Crystal Echo");
        add(ModItems.ECHO_TEMPLATE.get(), "Echo Upgrade Template");

        //tools
        add(ModItems.ECHO_SWORD.get(), "Echo Sword");
    }
}
