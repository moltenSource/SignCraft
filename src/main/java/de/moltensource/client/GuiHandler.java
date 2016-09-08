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
    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(SignCraft.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));

        switch (ID) {
            case 0:
                if (entity != null && entity instanceof TileEntitySignPress) {
                    return new ContainerSignCraft(player.inventory, (TileEntitySignPress) entity);
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));

        switch (ID) {
            case 0:
                if (entity != null && entity instanceof TileEntitySignPress) {
                    return new GuiSignPress(player.inventory, (TileEntitySignPress) entity);
                } else {
                    return null;
                }
            default:
                return null;
        }
    }
}
