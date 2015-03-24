package com.parrot.oregano.network.messagehandler;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.network.message.MessageGuiOpenToClient;
import com.parrot.oregano.network.message.MessageTileEntityBlob;
import com.parrot.oregano.tileentity.TileEntityBlob;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Shane on 3/16/2015.
 */
public class MessageHandlerGuiOpenToClient implements IMessageHandler<MessageGuiOpenToClient,IMessage> {
    @Override
    public IMessage onMessage(MessageGuiOpenToClient message, MessageContext ctx) {

        EntityPlayer player = FMLClientHandler.instance().getWorldClient().getPlayerEntityByName(message.playerName);
        if(player!=null) {
            player.openGui(Oregano.instance,message.guiId,FMLClientHandler.instance().getWorldClient(),message.x,message.y,message.z);
            LogHelper.info(message);
        }
        return null;
    }

}
