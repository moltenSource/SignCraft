package de.moltensource.common;

import de.moltensource.SignCraft;
import de.moltensource.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.function.Supplier;

public class ProxyCommon {

    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.registerBlocks(this);
    }

    public void init(FMLInitializationEvent event) {
        ModBlocks.registerTileEntities(this);
    }

    public Block registerBlock(String name, Supplier<Block> constructor) {
        Block block = constructor.get()
                .setUnlocalizedName(SignCraft.MODID + "." + name)
                .setRegistryName(name)
                .setCreativeTab(CreativeTab.SIGNCRAFT);

        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));

        return block;
    }

    public void registerTileEntity(Class<TileEntity> c, String name) {
        GameRegistry.registerTileEntity(c, name);
    }

}
