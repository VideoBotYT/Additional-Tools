package net.videobot.additionaltools.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.videobot.additionaltools.AdditionalToolsMod;

public class ModTools {
    public static final DeferredRegister.Items TOOLS = DeferredRegister.createItems(AdditionalToolsMod.MODID);

    public static final DeferredItem<SwordItem> ECHO_SWORD = TOOLS.register("echo_sword",
            () -> new SwordItem(ModToolTiers.ECHO, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ECHO, 16, -1f))));

    public static final DeferredItem<PickaxeItem> VOID_PICKAXE = TOOLS.register("void_pickaxe",
            () -> new PickaxeItem(ModToolTiers.VOID, new Item.Properties().fireResistant()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.VOID, 8, -1f))));
}
