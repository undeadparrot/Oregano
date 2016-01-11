package com.parrot.oregano.proxy;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.gui.GuiHandlerOregano;
import com.parrot.oregano.init.ModBlocks;
import com.parrot.oregano.init.ModEntities;
import com.parrot.oregano.init.ModItems;
import com.parrot.oregano.init.ModTileEntities;
import com.parrot.oregano.network.PacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

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
        ModEntities.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(Oregano.instance,new GuiHandlerOregano());

    }

}
