package de.moltensource;

import de.moltensource.common.ProxyCommon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
    }
}
