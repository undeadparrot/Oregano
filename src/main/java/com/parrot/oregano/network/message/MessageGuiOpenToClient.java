package com.parrot.oregano.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

/**
 * Created by Shane on 3/23/2015.
 */
public class MessageGuiOpenToClient implements IMessage {

    public String playerName;
    public int guiId;
    public int x,y,z;

    public MessageGuiOpenToClient()
    {
        playerName="";
    }

    public MessageGuiOpenToClient(EntityPlayer _player,int _guiId,int _x, int _y, int _z)
    {
        playerName=_player.getGameProfile().getName();
        guiId=_guiId;

        x=_x;
        y=_y;
        z=_z;


    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerName= ByteBufUtils.readUTF8String(buf);
        guiId=buf.readInt();
        x=buf.readInt();
        y=buf.readInt();
        z=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, playerName);
        buf.writeInt(guiId);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }
}
