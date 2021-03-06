package com.parrot.oregano.init;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.block.*;
import com.parrot.oregano.block.mc.BlockMcSignDouble;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Shane on 3/14/2015.
 */

@GameRegistry.ObjectHolder(Oregano.MODID)
public class ModBlocks {

    public static final BlockOregano plaster = new BlockPlaster();
    public static final BlockOregano blob = new BlockBlob();
    public static final BlockOregano easel = new BlockEasel();
    public static final BlockPoster poster = new BlockPoster();
    public static final BlockMcSignDouble mcsigndouble = new BlockMcSignDouble();
    public static final BlockBottleRack bottlerack = new BlockBottleRack();
    public static final BlockContainerRotatable containerrotatable = new BlockContainerRotatable();
    public static final BlockOregano lectern = new BlockLectern();

    public static void init()
    {
        GameRegistry.registerBlock(plaster,"plaster");
        GameRegistry.registerBlock(blob,"blob");
        GameRegistry.registerBlock(easel,"easel");
        GameRegistry.registerBlock(poster,"poster");
        GameRegistry.registerBlock(mcsigndouble,"mcsigndouble");
        GameRegistry.registerBlock(bottlerack,"bottlerack");
        GameRegistry.registerBlock(containerrotatable,"containerrotatable");
        GameRegistry.registerBlock(lectern,"lectern");
    }

}
