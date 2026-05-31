package net.videobot.additionaltools.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.videobot.additionaltools.AdditionalToolsMod;

public class CrystalUpgraderScreen extends AbstractContainerScreen<CrystalUpgraderMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "textures/gui/crystal_upgrader/crystal_upgrader_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AdditionalToolsMod.MODID, "textures/gui/crystal_upgrader/crystal_upgrader_arrow.png");

    public CrystalUpgraderScreen(CrystalUpgraderMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, 174);

        renderProgress(guiGraphics, x+52, y+38);
    }

    private void renderProgress(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting())
            guiGraphics.blit(ARROW_TEXTURE, x, y, 0, 0, 73, menu.getScaledArrowProgress(), 73, 20);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
