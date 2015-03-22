package com.parrot.oregano.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;

/**
 * Created by Shane on 3/16/2015.
 */
public class MessageTileEntityBlob implements IMessage  {

    public int x,y,z;
    public ItemStack itemStack;

    public MessageTileEntityBlob() {

    }

    public MessageTileEntityBlob(ItemStack _itemStack, int _x, int _y, int _z){
        itemStack=_itemStack;
        x=_x;y=_y;z=_z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        itemStack= ByteBufUtils.readItemStack(buf);
        x=buf.readInt();
        y=buf.readInt();
        z=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, itemStack);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }
}
