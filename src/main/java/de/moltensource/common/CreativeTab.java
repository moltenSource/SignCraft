package de.moltensource.common;

import de.moltensource.common.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab {

    public static final CreativeTabs SIGNCRAFT = new CreativeTabs("SignCraft") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.generalWarningSign);
        }
    };
}
