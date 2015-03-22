package com.parrot.oregano.init;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.item.ItemDesertRose;
import com.parrot.oregano.item.ItemOregano;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Shane on 3/13/2015.
 */

@GameRegistry.ObjectHolder(Oregano.MODID)
public class ModItems {

    public static final ItemOregano desertRose = new ItemDesertRose();

    public static void init()
    {
        GameRegistry.registerItem(desertRose,"desertRose");

    }

}
