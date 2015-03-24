package com.parrot.oregano.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.tileentity.TileEntitySign;
import org.lwjgl.opengl.GL11;

/**
 * Created by Shane on 3/22/2015.
 */
public class TileEntityRendererMcSignDouble extends TileEntitySignRenderer {

    @Override
    public void renderTileEntityAt(TileEntitySign entity, double x, double y, double z, float f)
    {

        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F,0F,0F);
        super.renderTileEntityAt(entity,x,y,z,f);
        GL11.glPopMatrix();

    }

}
