package net.videobot.additionaltools.creative;

import net.neoforged.bus.api.IEventBus;
import net.videobot.additionaltools.creative.list.BlockTab;
import net.videobot.additionaltools.creative.list.ItemTab;
import net.videobot.additionaltools.creative.list.ToolsTab;

public class CreativeTabs {
    public static void register(IEventBus bus) {
        BlockTab.BLOCKTAB.register(bus);
        ItemTab.ITEMTAB.register(bus);
        ToolsTab.TOOLSTAB.register(bus);
    }
}
