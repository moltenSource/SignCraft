package de.moltensource.common.init;

import de.moltensource.common.ProxyCommon;
import de.moltensource.common.blocks.BlockExplosiveSubstancesSign;
import de.moltensource.common.blocks.BlockGeneralWarningSign;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block generalWarningSign;
    public static Block explosiveSubstancesSign;

    public static void registerBlocks(ProxyCommon proxy) {
        generalWarningSign = proxy.registerBlock("generalWarningSign", BlockGeneralWarningSign::new);
        explosiveSubstancesSign = proxy.registerBlock("explosiveSubstancesSign", BlockExplosiveSubstancesSign::new);
    }

}
