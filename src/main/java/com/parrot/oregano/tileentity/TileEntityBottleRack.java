package com.parrot.oregano.tileentity;

import com.parrot.oregano.util.LogHelper;
import scala.tools.nsc.doc.model.Public;

/**
 * Created by Shane on 3/22/2015.
 */
public class TileEntityBottleRack extends TileEntityOregano {

    public String texture="minecraft:textures/blocks/brick.png";

    public TileEntityBottleRack()
    {

        LogHelper.info("*bottlerack");

    }

    @Override
    public void updateEntity()
    {

    }

}
