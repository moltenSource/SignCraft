package de.moltensource.client;

import de.moltensource.SignCraft;
import de.moltensource.common.ProxyCommon;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.function.Supplier;

public class ProxyClient extends ProxyCommon {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public Block registerBlock(String name, Supplier<Block> constructor) {
        Block block = super.registerBlock(name, constructor);

        Item item = Item.getItemFromBlock(block);
        if (item != null)
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(SignCraft.MODID + ":" + name, "inventory"));

        return block;
    }
}
