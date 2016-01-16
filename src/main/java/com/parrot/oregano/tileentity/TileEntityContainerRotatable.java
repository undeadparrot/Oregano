package com.parrot.oregano.tileentity;

import com.parrot.oregano.client.render.special.TileEntityRendererRotatable;
import com.parrot.oregano.network.PacketHandler;
import com.parrot.oregano.network.message.MessageTileEntityBlob;
import com.parrot.oregano.network.message.MessageTileEntityContainerRotatable;
import com.parrot.oregano.util.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by smatu on 1/12/2016.
 */
public class TileEntityContainerRotatable extends TileEntityOregano implements IInventory {
    public ItemStack[] inventoryItems;
    public int syncTicks;
    public boolean dirty;

    public TileEntityContainerRotatable()
    {
        inventoryItems= new ItemStack[howManyInvSlots()];
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {

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
        return 0;
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
        return false;
    }

    public int howManyInvSlots() {
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.facing= ForgeDirection.getOrientation(nbt.getInteger("fd"));
        int numSlots=howManyInvSlots();
        this.inventoryItems=new ItemStack[numSlots];
        NBTTagList invTags= nbt.getTagList("inv", Constants.NBT.TAG_COMPOUND);
        for ( int i=0;i<numSlots;i++ )
        {
            this.inventoryItems[i]=ItemStack.loadItemStackFromNBT(invTags.getCompoundTagAt(i));
        }
        NBTTagCompound extra=nbt.getCompoundTag("mo");
        this.NbtAfterParty(extra);
        LogHelper.info("rContainerRotatable");
    }

    public void NbtAfterParty(NBTTagCompound nbt) {

    }
    public void NbtPreParty(NBTTagCompound nbt) {

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("fd",this.facing.ordinal());
        int numSlots=howManyInvSlots();
        NBTTagList invTags= new NBTTagList();
        for (int i = 0;i<numSlots ; i++)
            {
                NBTTagCompound tag = new NBTTagCompound();
                if(this.inventoryItems[i]!=null)
                {
                    tag = this.inventoryItems[i].writeToNBT(tag);
                }
                invTags.appendTag(tag);

            }
        nbt.setTag("inv",invTags);
        NBTTagCompound extra=new NBTTagCompound();
        this.NbtPreParty(extra);
        nbt.setTag("mo",extra);

        LogHelper.info("wContainerRotatable");
    }

    @Override
    public Packet getDescriptionPacket()
    {

        LogHelper.info("gdpContainerRotatable");
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityContainerRotatable(xCoord,yCoord,zCoord,this));

    }

    @Override
    public void updateEntity()
    {
        syncTicks++;
        if(!worldObj.isRemote && syncTicks>2 && dirty) {
            syncTicks=1;
            dirty=false;
            PacketHandler.INSTANCE.sendToAll(new MessageTileEntityContainerRotatable(xCoord,yCoord,zCoord,this));
            this.markDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        super.updateEntity();
    }

    @Override
    public void renderCentered(TileEntityRendererRotatable tesr, TileEntity entity) {

    }

    public void PacketAfterParty(ByteBuf afterPartyBufToBytes) {

    }

    public void PacketPreParty(ByteBuf buf) {
    }
}
