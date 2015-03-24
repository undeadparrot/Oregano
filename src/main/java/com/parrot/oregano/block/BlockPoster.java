package com.parrot.oregano.block;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.gui.GuiHandlerOregano;
import com.parrot.oregano.network.PacketHandler;
import com.parrot.oregano.network.message.MessageGuiOpenToClient;
import com.parrot.oregano.network.message.MessageTileEntityCanvas;
import com.parrot.oregano.tileentity.TileEntityPoster;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.server.FMLServerHandler;
import ibxm.Player;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
        //Minecraft.getMinecraft().displayGuiScreen(new GuiPoster());

        LogHelper.info(">Player"+player.getUniqueID()+"_"+player.getEntityId()+"_"+player.getGameProfile().getName()+"_"+player.getGameProfile().getId());

        if(!world.isRemote)
        {
            PacketHandler.INSTANCE.sendTo(new MessageGuiOpenToClient(player, GuiHandlerOregano.Poster, x,y,z), (EntityPlayerMP)player);
            LogHelper.info(">Poster");
            //FMLNetworkHandler.openGui(player,Oregano.instance,GuiHandlerOregano.Poster,world,x,y,z);
            //player.openGui(Oregano.instance, GuiHandlerOregano.Poster,world,x,y,z);
        }

        return true;

    }

}
