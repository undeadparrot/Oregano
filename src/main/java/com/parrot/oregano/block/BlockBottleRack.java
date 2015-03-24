package com.parrot.oregano.block;

import com.parrot.oregano.init.ModTileEntitySpecialRenderers;
import com.parrot.oregano.tileentity.TileEntityBottleRack;
import com.parrot.oregano.util.LogHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Shane on 3/24/2015.
 */
public class BlockBottleRack extends BlockOregano implements ITileEntityProvider {

    public BlockBottleRack()
    {
        super();
        this.setBlockName("bottlerack");



    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityBottleRack();
    }

    @Override
    public int getRenderType()
    {
        return ModTileEntitySpecialRenderers.renderidBottleRack;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ)
    {

        LogHelper.info("materialspy "+player.getHeldItem().getIconIndex().getIconName()+"   ___ " + player.getHeldItem().getIconIndex());
        ((TileEntityBottleRack)world.getTileEntity(x,y,z)).texture=player.getHeldItem().getIconIndex().getIconName();

        return true;

    }


}
