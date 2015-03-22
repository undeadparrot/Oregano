package com.parrot.oregano.tileentity;

import com.parrot.oregano.network.PacketHandler;
import com.parrot.oregano.network.message.MessageTileEntityCanvas;
import com.parrot.oregano.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shane on 3/15/2015.
 */
public class TileEntityCanvas extends TileEntityOregano {

    public int width = 24;
    public int height= 24;
    public int[] data;

    public Vec3 testPosition;
    public float tilt=0.0F;
    public Map<String,Vec3> debugVecMap=new HashMap<String,Vec3>();

    public int syncTicks;
    public boolean dirty=true;


    //public static Vec3 planeP1=Vec3.createVectorHelper(1.0F,1.0F)

    public TileEntityCanvas()
    {
        data=new int[width*height];
        for(int i=0;i<width*height;i++)
        {
            data[i]= Color.lightGray.getRGB();
        }
        LogHelper.info(">canvas" );

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        width=nbt.getInteger("width");
        height=nbt.getInteger("height");
        data=nbt.getIntArray("imageData");
        //LogHelper.info(">Rcanvas" );
        //LogHelper.info((worldObj!=null?worldObj.isRemote:"null")+">R"+width+"_"+height);

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("width", width);
        nbt.setInteger("height", height);
        nbt.setIntArray("imageData", data);
        //LogHelper.info((worldObj != null ? worldObj.isRemote : "null") + ">W" + width + "_" + height);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        //Minecraft.getMinecraft().thePlayer.sendChatMessage(Minecraft.getMinecraft().theWorld.isRemote+">GetDescr");
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityCanvas(width,height,data,xCoord,yCoord,zCoord));
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        //Minecraft.getMinecraft().thePlayer.sendChatMessage(Minecraft.getMinecraft().theWorld.isRemote + ">Update");
        syncTicks++;
        if(!worldObj.isRemote && syncTicks>30 && dirty) {
            syncTicks=1;
            dirty=false;
            PacketHandler.INSTANCE.sendToAll(new MessageTileEntityCanvas(width, height, data,xCoord,yCoord,zCoord));
            this.markDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }
    @Override
    public AxisAlignedBB[] getSubBoundingBoxes() {
        AxisAlignedBB[] list = new AxisAlignedBB[2];
        list[0]=AxisAlignedBB.getBoundingBox(0.0F,0.0F,0.0F,0.2F,0.25F,0.5F);
        list[1]=AxisAlignedBB.getBoundingBox(0.4F,0.0F,0.15F,0.25F,0.25F,0.25F);
        return list ;
    }

}
