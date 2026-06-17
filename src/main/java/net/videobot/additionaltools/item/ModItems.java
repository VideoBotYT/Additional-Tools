package net.videobot.additionaltools.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.videobot.additionaltools.AdditionalToolsMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AdditionalToolsMod.MODID);

    public static final DeferredItem<Item> RAW_ECHO = ITEMS.register("raw_echo",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CRYSTAL_ECHO = ITEMS.register("crystal_echo",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ECHO_TEMPLATE = ITEMS.register("echo_template",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> VOID_DUST = ITEMS.register("void_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> VOID_INGOT = ITEMS.register("void_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> VOID_UPGRADE_TEMPLATE = ITEMS.register("void_upgrade_template",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ANCIENT_NOTES_FROM_FORGE = ITEMS.register("ancient_notes_from_forge",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
        ModTools.TOOLS.register(eventBus);
    }
}
