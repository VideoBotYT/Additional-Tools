package net.videobot.additionaltools.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.videobot.additionaltools.AdditionalToolsMod;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, AdditionalToolsMod.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

    }
}
