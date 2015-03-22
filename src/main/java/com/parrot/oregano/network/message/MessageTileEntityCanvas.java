package com.parrot.oregano.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import org.lwjgl.BufferUtils;

/**
 * Created by Shane on 3/16/2015.
 */
public class MessageTileEntityCanvas implements IMessage  {

    public int x,y,z;
    public int width,height;
    public int[] data;

    public MessageTileEntityCanvas() {

    }

    public MessageTileEntityCanvas(int w, int h, int[] d,int _x,int _y, int _z){
        data=d.clone();
        width=w;
        height=h;
        x=_x;y=_y;z=_z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        width=buf.readInt();
        height=buf.readInt();
        data=new int[width*height];
        for(int i=0;i<width*height;i++)
        {
            data[i]=buf.readInt();
        }
        x=buf.readInt();
        y=buf.readInt();
        z=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(width);
        buf.writeInt(height);
        for(int i=0;i<width*height;i++)
        {
            buf.writeInt(data[i]);
        }
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }
}
