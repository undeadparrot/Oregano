package com.parrot.oregano.handler;

import com.parrot.oregano.tileentity.TileEntityOregano;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

/**
 * Created by Shane on 3/22/2015.
 */
public class DrawBlockHighlightEventHandler {

    @SubscribeEvent
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event)
    {
        if(event.target.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) return;
        TileEntity tileEntity = event.player.worldObj.getTileEntity(event.target.blockX, event.target.blockY, event.target.blockZ);
        if(tileEntity != null && tileEntity instanceof TileEntityOregano)
        {
            ((TileEntityOregano)((TileEntityOregano) tileEntity)).renderBlockHighlight(event);
        }

    }

}
