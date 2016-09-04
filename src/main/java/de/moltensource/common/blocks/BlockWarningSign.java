package de.moltensource.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockWarningSign extends Block {

    public static final AxisAlignedBB BLOCK_BOX_NORTH = new AxisAlignedBB(0, 0, 0, 1, 1, 0.0625);
    public static final AxisAlignedBB BLOCK_BOX_SOUTH = new AxisAlignedBB(1, 0, 1, 0, 1, 15 * 0.0625);
    public static final AxisAlignedBB BLOCK_BOX_EAST = new AxisAlignedBB(1, 0, 0, 15 * 0.0625, 1, 1);
    public static final AxisAlignedBB BLOCK_BOX_WEST = new AxisAlignedBB(0, 0, 0, 0.0625, 1, 1);

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockWarningSign() {
        super(Material.CIRCUITS);
        setHardness(0.5F);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing facing = state.getValue(FACING);

        switch (facing.getName()) {
            case "south":
                return BLOCK_BOX_NORTH;
            case "north":
                return BLOCK_BOX_SOUTH;
            case "west":
                return BLOCK_BOX_EAST;
            case "east":
                return BLOCK_BOX_WEST;
            case "down":
                return BLOCK_BOX_NORTH;
            case "up":
                return BLOCK_BOX_NORTH;
            default:
                return BLOCK_BOX_NORTH;
        }
    }


    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        EnumFacing facing = state.getValue(FACING);

        AxisAlignedBB box;
        
        switch (facing.getName()) {
            case "south":
                box = BLOCK_BOX_NORTH;
                break;
            case "north":
                box = BLOCK_BOX_SOUTH;
                break;
            case "west":
                box = BLOCK_BOX_EAST;
                break;
            case "east":
                box = BLOCK_BOX_WEST;
                break;
            case "down":
                box = BLOCK_BOX_NORTH;
                break;
            case "up":
                box = BLOCK_BOX_NORTH;
                break;
            default:
                box = BLOCK_BOX_NORTH;
        }
        super.addCollisionBoxToList(state, worldIn, pos, box, collidingBoxes, entityIn);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);

        if (facing.getAxis() == EnumFacing.Axis.Y) {
            facing = EnumFacing.NORTH;
        }

        return getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos,
                                     EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
                                     EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
}
