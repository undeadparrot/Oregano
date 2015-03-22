package com.parrot.oregano.block;

import com.parrot.oregano.init.ModTileEntities;
import com.parrot.oregano.init.ModTileEntitySpecialRenderers;
import com.parrot.oregano.tileentity.TileEntityBlob;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by Shane on 3/14/2015.
 */
public class BlockBlob extends BlockOregano implements ITileEntityProvider{

    public BlockBlob()
    {
        super();
        setBlockName("blob");
        setBlockTextureName("sand");
    }

    @Override
    public int getRenderType()
    {
        return ModTileEntitySpecialRenderers.renderidBlob;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityBlob();
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        //
    //    //    //
        //
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ)
    {
        boolean remote=world.isRemote;
        if(!remote) {
            MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
            TileEntityBlob tileEntity = ((TileEntityBlob) world.getTileEntity(x, y, z));
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(sideX+"_"+sideY+"_"+sideZ);
            tileEntity.x += ((sideX - 0.5F) / 2.0F);
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(((sideX-0.5F)/2.0F)+"!");
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(tileEntity.x+"!");
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(remote+">"+player.getHeldItem()+"!"+player.getItemInUse()+"?"+player.isSneaking()+"("+tileEntity.inventoryItem+")");
            if (player.getHeldItem() != null && !player.isSneaking() && null == tileEntity.inventoryItem) {
                //Minecraft.getMinecraft().thePlayer.sendChatMessage(remote+">"+player.getHeldItem()+"into inwentory()");
                tileEntity.inventoryItem = player.getHeldItem().copy();
                tileEntity.inventoryItem.stackSize=1;
                player.getHeldItem().stackSize--;
                tileEntity.dirty=true;
            } else if (player.isSneaking() && tileEntity.inventoryItem != null) {
                EntityItem entItem = new EntityItem(world, x, y, z, tileEntity.inventoryItem.copy());
                //entItem.
                if (tileEntity.inventoryItem.hasTagCompound())
                {
                    entItem.getEntityItem().setTagCompound((NBTTagCompound)tileEntity.inventoryItem.getTagCompound().copy());
                }
                entItem.motionY=0F;
                entItem.motionX=0F;
                entItem.motionZ=0F;

                world.spawnEntityInWorld(entItem);

                tileEntity.inventoryItem = null;
                tileEntity.dirty=true;
            }
            world.markTileEntityChunkModified(x, y, z, tileEntity);


        }
        return true;
    }


}
