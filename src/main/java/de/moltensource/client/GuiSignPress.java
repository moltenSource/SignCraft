package de.moltensource.client;

import de.moltensource.SignCraft;
import de.moltensource.client.container.ContainerSignCraft;
import de.moltensource.tile.TileEntitySignPress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSignPress extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation(SignCraft.MODID.toLowerCase(), "textures/gui/blockSignPress.png");

    public GuiSignPress(InventoryPlayer invPlayer, TileEntitySignPress entity) {
        super(new ContainerSignCraft(invPlayer, entity));

        xSize = 176;
        ySize = 165;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        // TODO check if "Minecraft.getMinecraft().func_110434_K().func_110577_a(texture);" equals:
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
