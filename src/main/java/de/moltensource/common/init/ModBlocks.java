package de.moltensource.common.init;

import de.moltensource.common.ProxyCommon;
import de.moltensource.common.blocks.BlockSignPress;
import de.moltensource.common.blocks.BlockWarningSign;
import de.moltensource.tile.TileEntitySignPress;
import net.minecraft.block.Block;

public class ModBlocks {
    public static Block generalWarningSign;
    public static Block explosiveSubstancesSign;
    public static Block bioHazardSign;
    public static Block corrosiveSubstancesSign;
    public static Block counterRotatingRollerSign;
    public static Block dangerLoadingBatteriesSign;
    public static Block flammableMaterialsSign;
    public static Block floorObstructionsSign;
    public static Block forkLiftSign;
    public static Block gasBottlesSign;
    public static Block guardDogSign;
    public static Block handInjuryHazardSign;
    public static Block highVoltageSign;
    public static Block hotSurfacesSign;
    public static Block laserBeamSign;
    public static Block lowTemperaturesSign;
    public static Block magneticFieldSign;
    public static Block nonIonizingRadiationSign;
    public static Block opticalRadiationSign;
    public static Block overheadCraneSign;
    public static Block overheadObstaclesSign;
    public static Block oxidisingMaterialsSign;
    public static Block poisonousSubstancesSign;
    public static Block radioactiveSubstancesSign;
    public static Block remotelyStartedEquipmentSign;
    public static Block riskOfCrushingSign;
    public static Block riskOfFallingSign;
    public static Block sharpObjectsSign;
    public static Block slippingHazardSign;
    public static Block signPressBlock;

    public static void registerBlocks(ProxyCommon proxy) {
        generalWarningSign = proxy.registerBlock("generalWarningSign", BlockWarningSign::new);
        explosiveSubstancesSign = proxy.registerBlock("explosiveSubstancesSign", BlockWarningSign::new);
        bioHazardSign = proxy.registerBlock("bioHazardSign", BlockWarningSign::new);
        corrosiveSubstancesSign = proxy.registerBlock("corrosiveSubstancesSign", BlockWarningSign::new);
        counterRotatingRollerSign = proxy.registerBlock("counterRotatingRollerSign", BlockWarningSign::new);
        dangerLoadingBatteriesSign = proxy.registerBlock("dangerLoadingBatteriesSign", BlockWarningSign::new);
        flammableMaterialsSign = proxy.registerBlock("flammableMaterialsSign", BlockWarningSign::new);
        floorObstructionsSign = proxy.registerBlock("floorObstructionsSign", BlockWarningSign::new);
        forkLiftSign = proxy.registerBlock("forkLiftSign", BlockWarningSign::new);
        gasBottlesSign = proxy.registerBlock("gasBottlesSign", BlockWarningSign::new);
        guardDogSign = proxy.registerBlock("guardDogSign", BlockWarningSign::new);
        handInjuryHazardSign = proxy.registerBlock("handInjuryHazardSign", BlockWarningSign::new);
        highVoltageSign = proxy.registerBlock("highVoltageSign", BlockWarningSign::new);
        hotSurfacesSign = proxy.registerBlock("hotSurfacesSign", BlockWarningSign::new);
        laserBeamSign = proxy.registerBlock("laserBeamSign", BlockWarningSign::new);
        lowTemperaturesSign = proxy.registerBlock("lowTemperaturesSign", BlockWarningSign::new);
        magneticFieldSign = proxy.registerBlock("magneticFieldSign", BlockWarningSign::new);
        nonIonizingRadiationSign = proxy.registerBlock("nonIonizingRadiationSign", BlockWarningSign::new);
        opticalRadiationSign = proxy.registerBlock("opticalRadiationSign", BlockWarningSign::new);
        overheadCraneSign = proxy.registerBlock("overheadCraneSign", BlockWarningSign::new);
        overheadObstaclesSign = proxy.registerBlock("overheadObstaclesSign", BlockWarningSign::new);
        oxidisingMaterialsSign = proxy.registerBlock("oxidisingMaterialsSign", BlockWarningSign::new);
        poisonousSubstancesSign = proxy.registerBlock("poisonousSubstancesSign", BlockWarningSign::new);
        radioactiveSubstancesSign = proxy.registerBlock("radioactiveSubstancesSign", BlockWarningSign::new);
        remotelyStartedEquipmentSign = proxy.registerBlock("remotelyStartedEquipmentSign", BlockWarningSign::new);
        riskOfCrushingSign = proxy.registerBlock("riskOfCrushingSign", BlockWarningSign::new);
        riskOfFallingSign = proxy.registerBlock("riskOfFallingSign", BlockWarningSign::new);
        sharpObjectsSign = proxy.registerBlock("sharpObjectsSign", BlockWarningSign::new);
        slippingHazardSign = proxy.registerBlock("slippingHazardSign", BlockWarningSign::new);
        signPressBlock = proxy.registerBlock("signPressBlock", BlockSignPress::new);
    }

    public static void registerTileEntities(ProxyCommon proxy) {
        proxy.registerTileEntity(TileEntitySignPress.class, "BlockSignPress");
    }
}
