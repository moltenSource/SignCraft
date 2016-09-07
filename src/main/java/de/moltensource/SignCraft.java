package de.moltensource;

import de.moltensource.common.ProxyCommon;
import de.moltensource.common.blocks.BlockWarningSign;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SignCraft.MODID, name = SignCraft.MODID, version = SignCraft.VERSION)
public class SignCraft {

    public static final String MODID = "SignCraft";
    public static final String VERSION = "@VERSION@";

    @Mod.Instance(SignCraft.MODID)
    public static SignCraft instance;

    @SidedProxy(clientSide = "de.moltensource.client.ProxyClient", serverSide = "de.moltensource.server.ProxyServer")
    public static ProxyCommon proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        GameRegistry.addRecipe(new ItemStack(Blocks.COAL_BLOCK, 1),
                "SPS",
                "S S",
                "SSS",
                'S', Blocks.STONE, 'P', Blocks.PISTON);
    }
}
