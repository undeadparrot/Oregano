package com.parrot.oregano.tileentity;

import com.parrot.oregano.network.PacketHandler;
import com.parrot.oregano.network.message.MessageTileEntityBlob;
import com.parrot.oregano.network.message.MessageTileEntityCanvas;
import com.parrot.oregano.util.LogHelper;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.nbt.NBTUtil;

/**
 * Created by Shane on 3/14/2015.
 */
public class TileEntityBlob extends TileEntityOregano implements IInventory{

    public int syncTicks;
    public boolean dirty=true;

    public float x=0,y=0,z=0;
    public static int numSlots=1;

    public ItemStack inventoryItem;

    public TileEntityBlob()
    {

        LogHelper.info(">blob" );
    }

    @Override
    public void updateEntity()
    {
        syncTicks++;
        if(!worldObj.isRemote && syncTicks>10 && dirty) {
            syncTicks=1;
            dirty=false;
            PacketHandler.INSTANCE.sendToAll(new MessageTileEntityBlob(inventoryItem,xCoord,yCoord,zCoord));
            this.markDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        LogHelper.info(">RblobS" );
        super.readFromNBT(nbt);
        inventoryItem.readFromNBT(nbt);
        //LogHelper.info((worldObj != null ? worldObj.isRemote : "null") + ">R" );
        LogHelper.info(">Rblob" );

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (inventoryItem != null) {
            inventoryItem.writeToNBT(nbt);
        }
        //LogHelper.info(  ">W"  );
        //LogHelper.info((worldObj != null ? worldObj.isRemote : "null") + ">W"  );

    }

    @Override
    public int getSizeInventory() {
        return numSlots;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        if(0==slotIndex)
        {
            return inventoryItem;
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int stackIndex, int decrementSize) {
        if(stackIndex==0)
        {
            ItemStack tempItemStack=inventoryItem;
            inventoryItem=null;
            dirty=true;
            return tempItemStack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        LogHelper.info(slotIndex+"<-"+itemStack);
        if(0==slotIndex)
        {
            inventoryItem=itemStack;
            dirty=true;
        }
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }
}
