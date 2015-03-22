package com.parrot.oregano.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by Shane on 3/22/2015.
 */
public class MessageClientBlockInteractionByte implements IMessage{

    public byte action;
    public int x,y,z;

    public MessageClientBlockInteractionByte()
    {

    }

    public MessageClientBlockInteractionByte(byte _action, int _x, int _y, int _z)
    {
        action=_action;
        x=_x;y=_y;z=_z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        action=buf.readByte();

        x=buf.readInt();
        y=buf.readInt();
        z=buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeByte(action);

        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

    }
}
