package com.parrot.oregano.tileentity;

import com.parrot.oregano.client.render.special.TileEntityRendererRotatable;
import com.parrot.oregano.init.ModModels;
import com.parrot.oregano.network.PacketHandler;
import com.parrot.oregano.network.message.MessageTileEntityBlob;
import com.parrot.oregano.network.message.MessageTileEntityCanvas;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.*;
import org.lwjgl.opengl.GL11;

/**
 * Created by Shane on 3/14/2015.
 */
public class TileEntityBlob extends TileEntityContainerRotatable {

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

    @Override
    public void renderCentered(TileEntityRendererRotatable tesr, TileEntity entity) {

        GL11.glPushMatrix();

        World world = entity.getWorldObj();
        TileEntityBlob tileEntity=((TileEntityBlob)entity);

            FontRenderer fontrenderer = tesr.func_147498_b();


        GL11.glPushMatrix();
        GL11.glTranslatef((float) x  , (float) y  , (float) z  );

        WavefrontObject model = (WavefrontObject)ModModels.chestBarrelMini;
        tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/planks_spruce.png"));


        tesr.bindTexturePublic(TextureMap.locationBlocksTexture);

        Tessellator tess = Tessellator.instance;
        tess.startDrawing(4);

        IIcon materialitemicon= Block.getBlockFromItem(GameRegistry.findItem("minecraft","glass")).getBlockTextureFromSide(1);

        if(tileEntity.inventoryItem!=null) {
            Block helditemblock=Block.getBlockFromItem(tileEntity.inventoryItem.getItem());
            if(helditemblock!=null&&helditemblock!= Blocks.air)
            {
                materialitemicon= helditemblock.getBlockTextureFromSide(0);
            }

        }

        for (GroupObject part : model.groupObjects)
        {
            if(part.name.contains("Box")||part.name.contains("Lid"))
            {

            for(Face f : part.faces){
                tess.setNormal(f.faceNormal.x,f.faceNormal.y,f.faceNormal.z);
                for (int i = 0; i < f.vertices.length; i++) {
                    Vertex v = f.vertices[i];
                    TextureCoordinate t =f.textureCoordinates[i];
                    tess.addVertexWithUV(v.x,v.y,v.z,materialitemicon.getInterpolatedU(t.u*16),materialitemicon.getInterpolatedV(t.v*16));//

                }
            }
            }
        }

        materialitemicon= Block.getBlockFromItem(GameRegistry.findItem("minecraft","iron_block")).getBlockTextureFromSide(1);
        for (GroupObject part : model.groupObjects)
        {
            if(part.name.contains("Box")||part.name.contains("Lid"))
            {
                continue;
            }
            for(Face f : part.faces){
                tess.setNormal(f.faceNormal.x,f.faceNormal.y,f.faceNormal.z);
                for (int i = 0; i < f.vertices.length; i++) {
                    Vertex v = f.vertices[i];
                    TextureCoordinate t =f.textureCoordinates[i];
                    tess.addVertexWithUV(v.x,v.y,v.z,materialitemicon.getInterpolatedU(t.u*16),materialitemicon.getInterpolatedV(t.v*16));//

                }
            }
        }

        tess.draw();

//
//        model.renderPart("Box");
//        model.renderPart("Lid");
//        tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/iron_block.png"));
//        //GL11.glBindTexture(GL11.GL_TEXTURE_2D,37);
//
//        model.renderPart("Clasp");
//        tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/wool_colored_purple.png"));
//        model.renderPart("Lining");

        GL11.glPopMatrix();



        GL11.glTranslatef((float) x + 0.0F, (float) y + 0.50F, (float) z + 0.0F);
        GL11.glTranslatef(-0.1F,-0.25F,-0.05F);
        GL11.glScalef(0.5F,0.5F,0.5F);



        ItemStack stack = tileEntity.inventoryItem;
        if(stack!=null) {

            //IIcon heldItemBlockTexture = Block.getBlockFromItem(tileEntity.inventoryItem.getItem()).getBlockTextureFromSide(0);
            //LogHelper.info(heldItemBlockTextureResourceLocation);
            //ResourceLocation heldItBlTextureResourceLocation = new ResourceLocation(heldItemBlockTextureResourceLocation);
            //tesr.bindTexturePublic( heldItBlTextureResourceLocation );

            EntityItem entItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);
            entItem.hoverStart = 0.0F;

            GL11.glPushMatrix();
            GL11.glRotatef(-120.50F, 0.15F, 0.15F, 0.25F);
            RenderItem.renderInFrame = true;
            RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            RenderItem.renderInFrame = false;
            GL11.glPopMatrix();



        }

        GL11.glPushMatrix();
        //GL11.glTranslatef((float) x + 0.5F, (float) y + 0.50F, (float) z + 0.5F);
        //GL11.glTranslatef((float) x, (float) y, (float) z);
        //String iconname=Block.getBlockFromName("Stone").getBlockTextureFromSide(3).getIconName();
        tesr.bindTexturePublic(new ResourceLocation("textures/entity/chest/trapped.png"));
        Tessellator tessellator = Tessellator.instance;
        //int lightValue = entity.getWorldObj().getBlock((int)x,(int)y,(int)z).getMixedBrightnessForBlock(entity.getWorldObj(), (int)x, (int)y, (int)z);
        //tessellator.setBrightness(lightValue);
        //tessellateCenteredCubeWithUV(tessellator, 00.50F, 0.50F, 00.50F, 0.45F, 0.45F, 0.15F);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        Float tx = tileEntity.x;
        GL11.glTranslatef(tx,0.0F,0.0F);
        GL11.glScalef(0.009f, -0.009f, 0.009f);
        GL11.glColor4f(1.250F, 0.5F, 1.0F, 0.5F);
//        fontrenderer.drawString("Hellow_Wurldses",
//                -fontrenderer.getStringWidth("Hellow_Wurld") / 2,
//                25,
//                0);
//        fontrenderer.drawString(tx+"",
//                -fontrenderer.getStringWidth(tx+"") / 2,
//                35,
//                0);
        if(stack!=null) {
            fontrenderer.drawString(tileEntity.inventoryItem.getDisplayName() + "",
                    -fontrenderer.getStringWidth(tx + "") / 2,
                    -45,
                    0);
            Item item=(stack.getItem());
            if(stack.getItem() instanceof ItemWritableBook)
            {
                ItemWritableBook bookitem   =   (ItemWritableBook)stack.getItem();
                NBTTagCompound nbttagcompound = stack.getTagCompound();
                String s = "("+nbttagcompound.getString("title")+") "+nbttagcompound.getTagList("pages",8);
                fontrenderer.drawString(s,
                        -fontrenderer.getStringWidth(s) / 2,
                        -22,
                        0);
            }

        }
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

}
