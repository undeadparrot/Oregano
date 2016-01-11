package com.parrot.oregano.proxy;

import com.parrot.oregano.handler.DrawBlockHighlightEventHandler;
import com.parrot.oregano.init.ModRenderers;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Shane on 3/12/2015.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void init()
    {
        super.init();
        ModRenderers.init();
        MinecraftForge.EVENT_BUS.register(new DrawBlockHighlightEventHandler());

    }

}
