package net.videobot.additionaltools.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.ModBlocks;
import net.videobot.additionaltools.item.ModItems;
import net.videobot.additionaltools.item.ModTools;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, AdditionalToolsMod.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creativetab.additionaltools.additional_blocks", "Additional Blocks");
        add("creativetab.additionaltools.additional_items", "Additional Items");
        add("creativetab.additionaltools.additional_tools", "Additional Tools");
        add("blockentity.additionaltools.crystal_upgrader", "Crystal Upgrader");
        add("jei.crystal_recipe_tip.craft_time", "%s s");
        add("jade.tooltip.crystal_upgrader", "Progress: %d s / %d s");
        add("config.jade.plugin_additionaltools.crystal_upgrader", "Crystal Upgrader");

        // blocks
        add(ModBlocks.ECHO_ORE.get(), "Echo Ore");
        add(ModBlocks.CRYSTAL_UPGRADER.get(), "Crystal Upgrader");
        add(ModBlocks.VOID_ORE.get(), "Void Ore");
        add(ModBlocks.VOID_BLOCK.get(), "Void Block");
        add(ModBlocks.ECHO_DOOR.get(), "Echo Door");
        add(ModBlocks.ECHO_BUTTON.get(), "Echo Button");
        add(ModBlocks.ECHO_SLAB.get(), "Echo Slab");
        add(ModBlocks.ECHO_WALL.get(), "Echo Wall");
        add(ModBlocks.ECHO_STAIRS.get(), "Echo Stairs");
        add(ModBlocks.ECHO_BLOCK.get(), "Echo Block");
        add(ModBlocks.RAW_ECHO_BLOCK.get(), "Raw Echo Block");
        add(ModBlocks.VOID_DOOR.get(), "Void Door");
        add(ModBlocks.VOID_BUTTON.get(), "Void Button");
        add(ModBlocks.VOID_SLAB.get(), "Void Slab");
        add(ModBlocks.VOID_WALL.get(), "Void Wall");
        add(ModBlocks.VOID_STAIRS.get(), "Void Stairs");

        // items
        add(ModItems.RAW_ECHO.get(), "Raw Echo");
        add(ModItems.CRYSTAL_ECHO.get(), "Crystal Echo");
        add(ModItems.ECHO_TEMPLATE.get(), "Echo Upgrade Template");
        add(ModItems.VOID_DUST.get(), "Void Dust");
        add(ModItems.VOID_INGOT.get(), "Void Ingot");
        add(ModItems.VOID_UPGRADE_TEMPLATE.get(), "Void Upgrade Template");

        //tools
        add(ModTools.ECHO_SWORD.get(), "Echo Sword");
        add(ModTools.VOID_PICKAXE.get(), "Void Pickaxe");
    }
}
