package com.parrot.oregano.block;

import com.parrot.oregano.gui.guiscreen.GuiPoster;
import com.parrot.oregano.tileentity.TileEntityPoster;
import com.parrot.oregano.util.LogHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Shane on 3/22/2015.
 */
public class BlockPoster extends BlockOregano implements ITileEntityProvider {

    public BlockPoster()
    {
        super();
        setBlockName("poster");
        setBlockTextureName("poster");
        setBlockBounds(0F, 0F, 0F, 0.2F, 1.0F, 2.0F);
    }

//    @Override
//    public boolean renderAsNormalBlock(){return false;}

    @Override
    public boolean isOpaqueCube() {return false;}

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityPoster();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ)
    {
        LogHelper.info(">Poster");
        Minecraft.getMinecraft().displayGuiScreen(new GuiPoster());

        return true;

    }

}
