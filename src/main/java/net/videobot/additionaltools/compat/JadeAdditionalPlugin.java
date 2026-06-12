package net.videobot.additionaltools.compat;

import net.videobot.additionaltools.block.custom.CrystalUpgraderBlock;
import net.videobot.additionaltools.block.entity.CrystalUpgraderBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadeAdditionalPlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(CrystalUpgraderProvider.INSTANCE, CrystalUpgraderBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(CrystalUpgraderProvider.INSTANCE, CrystalUpgraderBlock.class);
    }
}
