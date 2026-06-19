package net.videobot.additionaltools.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.item.ModItems;

import java.util.List;

@EventBusSubscriber(modid = AdditionalToolsMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if (event.getType() == VillagerProfession.LIBRARIAN){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.ANCIENT_NOTES_FROM_FORGE.get()), 1, 10, 0.5f
            ));
        }
    }
}
