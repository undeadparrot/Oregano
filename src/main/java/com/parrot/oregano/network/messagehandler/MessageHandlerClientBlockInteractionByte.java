package com.parrot.oregano.network.messagehandler;

import com.parrot.oregano.network.message.MessageBlockInteractionByteToServer;
import com.parrot.oregano.tileentity.TileEntityOregano;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Shane on 3/22/2015.
 */
public class MessageHandlerClientBlockInteractionByte implements IMessageHandler<MessageBlockInteractionByteToServer,IMessage> {

    @Override
    public IMessage onMessage(MessageBlockInteractionByteToServer message, MessageContext ctx) {

        TileEntityOregano tileEntity= (TileEntityOregano) FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x,message.y,message.z);
        if(tileEntity!=null) {
            tileEntity.onClientBlockInteractionByte(message.action);
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(".");;
        }
        return null;
    }
}
