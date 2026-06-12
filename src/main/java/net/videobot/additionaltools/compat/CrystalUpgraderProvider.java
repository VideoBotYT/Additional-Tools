package net.videobot.additionaltools.compat;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.videobot.additionaltools.AdditionalToolsMod;
import net.videobot.additionaltools.block.entity.CrystalUpgraderBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

public enum CrystalUpgraderProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        IElementHelper helper = IElementHelper.get();
        iTooltip.add(Component.translatable("jade.tooltip.crystal_upgrader",
                blockAccessor.getServerData().getInt("CraftingTime")/20,
                blockAccessor.getServerData().getInt("MaxProgress")/20));
    }

    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID,"crystal_upgrader");
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        CrystalUpgraderBlockEntity blockEntity = ((CrystalUpgraderBlockEntity) blockAccessor.getBlockEntity());
        compoundTag.putInt("CraftingTime", blockEntity.progress);
        compoundTag.putInt("MaxProgress", blockEntity.max_progress);
    }
}
