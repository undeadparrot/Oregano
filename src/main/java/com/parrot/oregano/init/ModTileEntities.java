package com.parrot.oregano.init;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.tileentity.TileEntityBlob;
import com.parrot.oregano.tileentity.TileEntityCanvas;
import com.parrot.oregano.tileentity.TileEntityOregano;
import com.parrot.oregano.tileentity.TileEntityPoster;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Shane on 3/14/2015.
 */
@GameRegistry.ObjectHolder(Oregano.MODID)
public class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityBlob.class,"blob");
        GameRegistry.registerTileEntity(TileEntityCanvas.class,"canvas");
        GameRegistry.registerTileEntity(TileEntityPoster.class,"poster");
    }
}
