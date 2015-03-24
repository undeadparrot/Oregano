package com.parrot.oregano;

/**
 * Created by Shane on 3/12/2015.
 */

import com.parrot.oregano.handler.ConfigurationHandler;
import com.parrot.oregano.init.ModBlocks;
import com.parrot.oregano.init.ModItems;
import com.parrot.oregano.init.ModTileEntities;
import com.parrot.oregano.proxy.IProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Oregano.MODID, name="Oregano Mod Garden", version=Oregano.VERSION, guiFactory = Oregano.GUI_FACTORY_CLASS)
public class Oregano {
    public static final String MODID="oregano";
    public static final String VERSION="1.7.10-10.13.2.1291";
    public static final String PROXY_SERVER="com.parrot.oregano.proxy.ServerProxy";
    public static final String PROXY_CLIENT="com.parrot.oregano.proxy.ClientProxy";
    public static final String GUI_FACTORY_CLASS="com.parrot.oregano.gui.config.GuiFactory";

    @Mod.Instance(Oregano.MODID)
    public static Oregano instance;

    @SidedProxy(clientSide =Oregano.PROXY_CLIENT ,serverSide =Oregano.PROXY_SERVER, modId = Oregano.MODID)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        proxy.init();
    }

    @Mod.EventHandler
    public void Init (FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event)
    {

    }


}
