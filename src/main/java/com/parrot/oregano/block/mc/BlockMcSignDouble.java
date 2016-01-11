package com.parrot.oregano.block.mc;

import com.parrot.oregano.init.ModRenderers;
import net.minecraft.block.BlockSign;
import net.minecraft.tileentity.TileEntitySign;

/**
 * Created by Shane on 3/22/2015.
 */
public class BlockMcSignDouble extends BlockSign{

    public BlockMcSignDouble() {
        super(TileEntitySign.class, false);
        this.setBlockName("mcsigndouble");
    }

    //@Override
    //public int getRenderType(){return ModRenderers.renderidMcSignDouble;}


}
