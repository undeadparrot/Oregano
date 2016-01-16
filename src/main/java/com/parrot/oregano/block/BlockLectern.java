package com.parrot.oregano.block;

import com.parrot.oregano.network.PacketHandler;
import com.parrot.oregano.network.message.MessageTileEntityBlob;
import com.parrot.oregano.tileentity.TileEntityBottleRack;
import com.parrot.oregano.tileentity.TileEntityContainerRotatable;
import com.parrot.oregano.tileentity.TileEntityLectern;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by smatu on 1/13/2016.
 */
public class BlockLectern extends BlockContainerRotatable  {

    public BlockLectern() {
        super();
        this.setBlockName("lectern");
    }
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityLectern();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        super.onBlockPlacedBy(world, x, y, z, entity, itemStack);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block,  int meta) {

        TileEntityContainerRotatable tileEntity = ((TileEntityContainerRotatable) world.getTileEntity(x, y, z));


        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ) {
        boolean remote=world.isRemote;
        if(!remote) {
            MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
            TileEntityContainerRotatable tileEntity = ((TileEntityContainerRotatable) world.getTileEntity(x, y, z));
            if (player.getHeldItem() != null && !player.isSneaking() && null == tileEntity.inventoryItems[0]) {
                tileEntity.inventoryItems[0] = player.getHeldItem().copy();
                tileEntity.inventoryItems[0].stackSize = 1;
                player.getHeldItem().stackSize--;
                tileEntity.dirty = true;
            } else if (player.isSneaking() &&null != tileEntity.inventoryItems[0]) {
                EntityItem entItem = new EntityItem(world, x, y, z, tileEntity.inventoryItems[0].copy());
                //entItem.
                if (tileEntity.inventoryItems[0].hasTagCompound()) {
                    entItem.getEntityItem().setTagCompound((NBTTagCompound) tileEntity.inventoryItems[0].getTagCompound().copy());
                }
                entItem.motionY = 0F;
                entItem.motionX = 0F;
                entItem.motionZ = 0F;

                world.spawnEntityInWorld(entItem);

                tileEntity.inventoryItems[0] = null;
            }
            else{
                if(!player.isSneaking())
                {
                    ((TileEntityLectern)tileEntity).pageNumber++;
                }
                else
                {
                    ((TileEntityLectern)tileEntity).pageNumber++;

                }
            }
            tileEntity.dirty = true;
            world.markTileEntityChunkModified(x, y, z, tileEntity);
        }
        return true;
    }
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
    }
}
