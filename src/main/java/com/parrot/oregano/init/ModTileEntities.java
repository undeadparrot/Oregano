package com.parrot.oregano.init;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.tileentity.*;
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
        GameRegistry.registerTileEntity(TileEntityBottleRack.class,"bottlerack");
        GameRegistry.registerTileEntity(TileEntityContainerRotatable.class,"containerrotatable");

    }
}
