package com.parrot.oregano.block;

import com.parrot.oregano.init.ModTileEntitySpecialRenderers;
import com.parrot.oregano.tileentity.TileEntityCanvas;
import com.parrot.oregano.util.BlockHelper;
import com.parrot.oregano.util.LogHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.awt.*;
import java.util.*;

import static com.parrot.oregano.util.IntersectionHelper.intersectLinePlane;

/**
 * Created by Shane on 3/14/2015.
 */
public class BlockCanvas extends BlockOregano implements ITileEntityProvider{

    public static final float thickness=0.125F;

    public BlockCanvas()
    {
        super();
        setBlockName("canvas");
        setBlockTextureName("sand");
        setBlockBounds(0.20F,0.0F,0.0F,thickness,2.0F,2.0F);
    }

    @Override
    public int getRenderType()
    {
        return ModTileEntitySpecialRenderers.renderidCanvas;
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
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCanvas();
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {



    }


    @Override
    public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB aabb, java.util.List list, Entity entity)
    {
        this.setBlockBounds(0.20F, 0.0F, 0.0F, 0.1F, 2.0F, 1.0F);
        super.addCollisionBoxesToList(world, i, j, k, aabb, list, entity);
        this.setBlockBounds(0.90F,0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        super.addCollisionBoxesToList(world, i, j, k, aabb, list, entity);
        this.setBlockBounds(0.00F,0.0F,0.0F,1.0F,1.0F,1.0F);
    }

        public static Vec3 getUniversalHeadVec(EntityPlayer player)
    {

        if(player.worldObj.isRemote)
        {
            return Vec3.createVectorHelper(player.posX,player.posY+player.getEyeHeight()-player.getDefaultEyeHeight(),player.posZ);
        }
        else
        {
            return Vec3.createVectorHelper(player.posX,player.posY+player.getEyeHeight()-((player instanceof EntityPlayerMP && player.isSneaking())?0.08F:0.0F),player.posZ);
        }
    }

    public static Vec3 vec3add(Vec3 a, Vec3 b)
    {
        return Vec3.createVectorHelper(a.xCoord+b.xCoord,a.yCoord+b.yCoord,a.zCoord+b.zCoord);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ)
    {
       return false;
    }

    public void updateOrientation(TileEntityCanvas tileEntity)
    {
        if(tileEntity.data[02]%2==1)
        {
            //setBlockBounds(0.0F,0.0F,0.0F,1.0F,1.0F,thickness);
        }
        else
        {
            //setBlockBounds(0.0F,0.0F,0.0F,1.0F,1.0F,thickness);
        }
    }

}
