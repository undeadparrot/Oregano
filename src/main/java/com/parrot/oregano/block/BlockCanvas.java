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
        boolean remote=world.isRemote;
        TileEntityCanvas tileEntity= ((TileEntityCanvas) world.getTileEntity(x,y,z));
        MovingObjectPosition mop= Minecraft.getMinecraft().objectMouseOver;
     //   Minecraft.getMinecraft().thePlayer.sendChatMessage(sideX+"_"+sideY+"_"+sideZ);
        float eyeHeight=player.getEyeHeight();
        Vec3 lookVec=player.getLookVec();
        Vec3 headPoint=getUniversalHeadVec(player);
        Vec3 xyz=Vec3.createVectorHelper(x, y, z);
        Vec3 pos=xyz;
        //lookVec.dotProduct()

        float depth=+0.2F+BlockCanvas.thickness;
        //+0.05//+0.05//-0.05
        float angleconst=tileEntity.tilt;
        float wid=0.6F;
        float wd=wid/2.0F;
        float height=0.6F;
        Vec3 planeP1=Vec3.createVectorHelper(-wd, 0.0F, depth+angleconst );
        Vec3 planeP2=Vec3.createVectorHelper(wd, 0.0F, depth+angleconst );
        Vec3 planeP3=Vec3.createVectorHelper(-wd, height, depth-angleconst );



        Vec3 xyzoffset=Vec3.createVectorHelper(0.5F,0.0F,0.5F);
        planeP1=planeP1.addVector(x,y,z).addVector(xyzoffset.xCoord,xyzoffset.yCoord,xyzoffset.zCoord);
        planeP2=planeP2.addVector(x,y,z).addVector(xyzoffset.xCoord,xyzoffset.yCoord,xyzoffset.zCoord);
        planeP3=planeP3.addVector(x,y,z).addVector(xyzoffset.xCoord,xyzoffset.yCoord,xyzoffset.zCoord);

        Vec3 centreofPlane=Vec3.createVectorHelper(
                planeP1.xCoord+((planeP2.xCoord-planeP1.xCoord)/2.0F),
                planeP1.yCoord+((planeP3.yCoord-planeP1.yCoord)/2.0F),
                planeP1.zCoord+((planeP3.zCoord-planeP1.zCoord)/2.0F)
        );


        tileEntity.debugVecMap.put("pP1",xyz.subtract(planeP1));
        tileEntity.debugVecMap.put("pP2",xyz.subtract(planeP2));
        tileEntity.debugVecMap.put("pP3",xyz.subtract(planeP3));
        tileEntity.debugVecMap.put("pO",xyz.subtract(centreofPlane));

        Vec3 planeN =(planeP2.subtract(planeP1).crossProduct(planeP3.subtract(planeP1))   );
        //Vec3 planeN =(planeP3.subtract(planeP1).crossProduct(planeP2.subtract(planeP1))   );//inverted

        Vec3 normalArrow=centreofPlane.addVector(planeN.xCoord*0.5F,planeN.yCoord*0.5F,planeN.zCoord*0.5F);
        tileEntity.debugVecMap.put("pN", xyz.subtract(normalArrow));
        tileEntity.debugVecMap.put("pN_dot1",xyz.subtract(normalArrow.addVector(planeN.xCoord*-0.25F,planeN.yCoord*-0.25F,planeN.zCoord*-0.25F)));
        tileEntity.debugVecMap.put("pN_dot2",xyz.subtract(normalArrow.addVector(planeN.xCoord*0.25F,planeN.yCoord*0.25F,planeN.zCoord*0.25F)));

        Vec3 lineP1=headPoint;
        Vec3 lineP2 = headPoint.addVector(lookVec.xCoord * 3.0F, lookVec.yCoord *3.0F,lookVec.zCoord*3.0F);
        tileEntity.debugVecMap.put("lP2",xyz.subtract(lineP2));
        tileEntity.debugVecMap.put("lP2_dot1",xyz.subtract(lineP1.addVector(lookVec.xCoord*0.25F,lookVec.yCoord*0.25F,lookVec.zCoord*0.25F)));
        tileEntity.debugVecMap.put("lP2_dot2",xyz.subtract(lineP1.addVector(lookVec.xCoord*0.50F,lookVec.yCoord*0.50F,lookVec.zCoord*0.50F)));
        tileEntity.debugVecMap.put("lP2_dot3",xyz.subtract(lineP1.addVector(lookVec.xCoord*0.75F,lookVec.yCoord*0.75F,lookVec.zCoord*0.75F)));

        float u = (float) ((planeN.dotProduct(planeP3.subtract(lineP1)))
                        /
                        (planeN.dotProduct(lineP2.subtract(lineP1))));
        float u2 = (float)(
                (planeN.dotProduct(planeP1.subtract(lineP1)))
                /
                (planeN.dotProduct(lookVec))
                );
        float d= (float) planeN.dotProduct(planeP1);
        Vec3 ab=lineP2.subtract(lineP1);
        float t= (float) ((d - planeN.dotProduct(lineP1) )/ planeN.dotProduct(ab));
        t=Math.abs(t);
//        if(t>=0.0F && t<=1.0f)
//        {
//            t+=0.25F;
//            LogHelper.info("It intersects!");
//            Vec3 poi=Vec3.createVectorHelper(
//                    lineP1.xCoord+(t*lookVec.xCoord),
//                    lineP1.yCoord+(t*lookVec.yCoord),
//                    lineP1.zCoord+(t*lookVec.zCoord)
//            );
//            //tileEntity.debugVecMap.put("+", xyz.subtract(poi));
//        }
        Vec3 poi=null;
        Vec3 poiLocal=null,poiNormalized=null;
        Vec3 planeSize=Vec3.createVectorHelper(planeP2.xCoord-planeP1.xCoord,planeP3.yCoord-planeP1.yCoord,planeP3.zCoord-planeP1.zCoord);
        u2=Math.abs(u2);
        if(u2>=0.0F && u2<=1.0f)
        {
            //LogHelper.info("It intersects!");
            poi = Vec3.createVectorHelper(
                    lineP1.xCoord + (u2 * lookVec.normalize().xCoord),
                    lineP1.yCoord + (u2 * lookVec.normalize().yCoord),
                    lineP1.zCoord + (u2 * lookVec.normalize().zCoord)
            );
        //tileEntity.debugVecMap.put("x",xyz.subtract(poi));

        poiLocal=Vec3.createVectorHelper(poi.xCoord-planeP1.xCoord,poi.yCoord-planeP1.yCoord,poi.zCoord-planeP1.zCoord);
        poiNormalized=Vec3.createVectorHelper(poiLocal.xCoord/planeSize.xCoord,poiLocal.yCoord/planeSize.yCoord,poiLocal.zCoord/planeSize.zCoord);

    }
    //LogHelper.info("intersectionA=" +poi);
    //LogHelper.info("intersectionB=" + );
    poi=intersectLinePlane(lineP1, lookVec, 2.0F, planeP1, planeP3, planeP2);

        if(poi!=null){tileEntity.debugVecMap.put("x",xyz.subtract(poi));}

        AxisAlignedBB aabb=AxisAlignedBB.getBoundingBox(x,y,z,x+0.5F,y+0.5F,z+0.5F);
        LogHelper.info("AABBINTERCEPT:"+aabb.calculateIntercept(lineP1,lineP2));

        //tileEntity.tilt+=0.001F;

        //Vec3 poi = lineP1.addVector(lineP1P2.xCoord * u, lineP1P2.yCoord * u, lineP1P2.zCoord * u);
        //Vec3 poi2=poi.subtract(pos);
        //LogHelper.info("lookvec"+lookVec+"   player.getPosition().y"+player.getPosition(0.0F).yCoord+" player.posY "+player.posY+" "+" ___ "+" ");
        //LogHelper.info("lineP1"+lineP1+"   planeP1"+planeP1+" ___lookVec "+player.getLookVec()+" ___ "+" ");
        //LogHelper.info("planesize"+planeSize+"   poiLocal"+poiLocal+" ___ ");
        //LogHelper.info("u="+u+"   u2="+u2+" ___ ");
        //LogHelper.info("d"+d+"   t"+t+" ___ "+1+" ___ "+1);
        tileEntity.testPosition =Vec3.createVectorHelper(  sideX,  sideY,  sideZ);
        //tileEntity.testPosition=poi2;
        tileEntity.testPosition=xyz.subtract(lineP1);
        tileEntity.debugVecMap.put("H",xyz.subtract(lineP1));
        //LogHelper.info("headpoint"+headPoint+"   testposition"+tileEntity.testPosition+" ___ ");

        if(poi!=null) {
            poiLocal=Vec3.createVectorHelper(poi.xCoord-planeP1.xCoord,poi.yCoord-planeP1.yCoord,poi.zCoord-planeP1.zCoord);
            poiNormalized=Vec3.createVectorHelper(poiLocal.xCoord/planeSize.xCoord,poiLocal.yCoord/planeSize.yCoord,poiLocal.zCoord/planeSize.zCoord);
            int w = tileEntity.width;
            int h = tileEntity.height;
            int pixx = (int) (poiNormalized.xCoord * w);
            int pixy = (h - 1) - (int) (poiNormalized.yCoord * h);
            if (poiNormalized.xCoord >= 1.0F || poiNormalized.xCoord <= 0.0F || poiNormalized.yCoord <= 0.0F || poiNormalized.yCoord >= 1.0F) {
                //LogHelper.info("not inside the canvas");
                return true;
            }
            int pix = pixx + (pixy * tileEntity.width);
            if (tileEntity.data.length >= pix) {
                tileEntity.data[pix] = (tileEntity.data[pix] == Color.lightGray.getRGB()) ? Color.yellow.getRGB() : Color.lightGray.getRGB();
                tileEntity.dirty = true;
                world.markTileEntityChunkModified(x, y, z, tileEntity);
                //setBlockBounds(0.0F, 0.0F, 0.0F, 1.5F, 0.50F, 1.0F);

                //Minecraft.getMinecraft().thePlayer.sendChatMessage(remote+">");
            } else {
                //Minecraft.getMinecraft().thePlayer.sendChatMessage((remote+">")+tileEntity.data.length+"<"+pix);
            }
            //((TileEntityCanvas)world.getTileEntity(x,y,z)).x+=((sideX-0.5F)/2.0F);
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(pixx+"/"+w+"_"+pixy+"/"+h);
        }
        updateOrientation(tileEntity);
        ForgeDirection dir=BlockHelper.GetFDFromEntity(player, false);
        return true;
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
