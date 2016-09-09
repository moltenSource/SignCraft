package de.moltensource.client;

import de.moltensource.SignCraft;
import de.moltensource.client.container.ContainerSignCraft;
import de.moltensource.tile.TileEntitySignPress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {
    private static final int GUIID = 404;

    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(SignCraft.instance, this);
    }

    public static int getGuiID() {
        return GUIID;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID != getGuiID()) {
            System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
        }

        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));

        if (entity instanceof TileEntitySignPress) {
            TileEntitySignPress tileEntitySignPress = (TileEntitySignPress) entity;
            return new ContainerSignCraft(player.inventory, tileEntitySignPress);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID != getGuiID()) {
            System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
        }

        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
        if (entity instanceof TileEntitySignPress) {
            TileEntitySignPress tileEntitySignPress = (TileEntitySignPress) entity;
            return new GuiSignPress(player.inventory, tileEntitySignPress);
        }
        return null;
    }
}