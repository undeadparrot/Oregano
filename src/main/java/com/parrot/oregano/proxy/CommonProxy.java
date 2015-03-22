package com.parrot.oregano.proxy;

import com.parrot.oregano.init.ModBlocks;
import com.parrot.oregano.init.ModItems;
import com.parrot.oregano.init.ModTileEntities;
import com.parrot.oregano.network.PacketHandler;

/**
 * Created by Shane on 3/12/2015.
 */
public abstract class CommonProxy implements IProxy {

    @Override
    public void init()
    {

        ModItems.init();
        ModBlocks.init();
        ModTileEntities.init();
        PacketHandler.init();

    }

}
