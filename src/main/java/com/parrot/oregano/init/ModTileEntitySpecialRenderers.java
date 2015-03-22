package com.parrot.oregano.init;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.client.render.tileentity.TileEntityRendererBlob;
import com.parrot.oregano.client.render.tileentity.TileEntityRendererCanvas;
import com.parrot.oregano.tileentity.TileEntityBlob;
import com.parrot.oregano.tileentity.TileEntityCanvas;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by Shane on 3/14/2015.
 */
@GameRegistry.ObjectHolder(Oregano.MODID)
public class ModTileEntitySpecialRenderers {

    public static int renderidBlob;
    public static int renderidCanvas;

    public static void init()
    {
        renderidBlob=RenderingRegistry.getNextAvailableRenderId();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlob.class,new TileEntityRendererBlob());
        renderidCanvas=RenderingRegistry.getNextAvailableRenderId();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCanvas.class,new TileEntityRendererCanvas());


    }

}
