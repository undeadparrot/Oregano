package com.parrot.oregano.network;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.network.message.*;
import com.parrot.oregano.network.messagehandler.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by Shane on 3/15/2015.
 */

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE= NetworkRegistry.INSTANCE.newSimpleChannel(Oregano.MODID.toLowerCase());

    public static void init()
    {
        INSTANCE.registerMessage(MessageHandlerTileEntityCanvas.class, MessageTileEntityCanvas.class,0, Side.CLIENT);
        INSTANCE.registerMessage(MessageHandlerTileEntityBlob.class, MessageTileEntityBlob.class,1, Side.CLIENT);
        INSTANCE.registerMessage(MessageHandlerGuiOpenToClient.class, MessageGuiOpenToClient.class,2, Side.CLIENT);
        INSTANCE.registerMessage(MessageHandlerPlaySoundToClient.class, MessagePlaySoundToClient.class,3, Side.CLIENT);
        INSTANCE.registerMessage(MessageHandlerTileEntityContainerRotatable.class, MessageTileEntityContainerRotatable.class,4,Side.CLIENT);
    }

}
