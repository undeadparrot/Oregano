package com.parrot.oregano.network.messagehandler;

import com.parrot.oregano.network.message.MessageTileEntityBlob;
import com.parrot.oregano.tileentity.TileEntityBlob;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Shane on 3/16/2015.
 */
public class MessageHandlerTileEntityBlob implements IMessageHandler<MessageTileEntityBlob,IMessage> {
    @Override
    public IMessage onMessage(MessageTileEntityBlob message, MessageContext ctx) {

        TileEntityBlob tileEntity= (TileEntityBlob) FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x,message.y,message.z);
        if(tileEntity!=null) {
            tileEntity.inventoryItem = message.itemStack;
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(".");;
        }
        return null;
    }


//    @Override
//     //https://github.com/Anoyomouse/SqueakCraft/blob/master/src/main/java/com/anoyomouse/squeakcraft/network/message/MessageTileEntityStockPile.java#L17
//    public IMessage onMessage(MessageTileEntityStockPile message, MessageContext ctx)
//    {
//        TileEntity tileEntity = super.getTileEntityMessage(message, ctx);
//        if (tileEntity instanceof TileEntityStockPile)
//        {
//            for(int i = 0; i < 4; i++)
//            {
//                ((TileEntityStockPile) tileEntity).setInventorySlotContents(i, message.inventorySlots[i]);
//            }
//        }
//        return null;
//    }

}
