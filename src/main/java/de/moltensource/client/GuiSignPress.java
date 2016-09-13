package de.moltensource.client;

import de.moltensource.SignCraft;
import de.moltensource.client.container.ContainerSignCraft;
import de.moltensource.tile.TileEntitySignPress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiSignPress extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation(SignCraft.MODID.toLowerCase(), "textures/gui/blockSignPress.png");
    private TileEntitySignPress entity;

    public GuiSignPress(InventoryPlayer invPlayer, TileEntitySignPress entity) {
        super(new ContainerSignCraft(invPlayer, entity));

        xSize = 176;
        ySize = 166;

        this.entity = entity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;

        fontRendererObj.drawString(entity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
    }
}
