package com.parrot.oregano.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

/**
 * Created by Shane on 3/22/2015.
 */
public class MessageClientBlockInteractionText implements IMessage{

    String text;
    int x,y,z;

    public MessageClientBlockInteractionText()
    {

    }

    public MessageClientBlockInteractionText(String _text, int _x, int _y, int _z)
    {
        text=_text;
        x=_x;y=_y;z=_z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        text= ByteBufUtils.readUTF8String(buf);

        x=buf.readInt();
        y=buf.readInt();
        z=buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {

        ByteBufUtils.writeUTF8String(buf,text);

        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

    }
}
