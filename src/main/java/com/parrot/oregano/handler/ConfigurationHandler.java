package com.parrot.oregano.handler;

import com.parrot.oregano.Oregano;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Shane on 3/13/2015.
 */
public class ConfigurationHandler {

    public static Configuration configuration;

    public static boolean configValue=false;

    public static void init(File configFile)
    {
        if(configuration==null){
            configuration= new Configuration(configFile);
            loadConfiguration();
        }




    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Oregano.MODID))
        {
            //configs have changed, update
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        boolean configValue = configuration.get(Configuration.CATEGORY_GENERAL,"configValue",true,"This is an example").getBoolean(true);

        if(configuration.hasChanged()) {
            configuration.save();
        }
    }

}
