package de.moltensource.client.container;

import de.moltensource.tile.TileEntitySignPress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerSignCraft extends Container {
    private TileEntitySignPress entity;

    public ContainerSignCraft(InventoryPlayer invPlayer, TileEntitySignPress entity) {
        this.entity = entity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return entity.isUseableByPlayer(player);
    }
}
