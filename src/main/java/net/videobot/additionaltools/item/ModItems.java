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

    public static final DeferredItem<SwordItem> ECHO_SWORD = ITEMS.register("echo_sword",
            () -> new SwordItem(ModToolTiers.ECHO, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ECHO, 16, -1f))));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
