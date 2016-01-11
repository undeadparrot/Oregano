package com.parrot.oregano.tileentity;

import com.parrot.oregano.client.render.special.TileEntityRendererRotatable;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * Created by Shane on 3/14/2015.
 */
public abstract class TileEntityOregano extends TileEntity {


    public ForgeDirection facing=ForgeDirection.NORTH;

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        //LogHelper.info((worldObj!=null?worldObj.isRemote:"null") + ">RR" );
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        //LogHelper.info((worldObj!=null?worldObj.isRemote:"null") + ">WW");
    }

    public void renderBlockHighlight(DrawBlockHighlightEvent event)
    {

        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 0.5F, 0.5F);
        GL11.glLineWidth(2);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        float f1 = 0.0047F;

        for(AxisAlignedBB aabb: this.getSubBoundingBoxes()) {

            double partialTickTime = event.partialTicks;

            double d0 = event.player.lastTickPosX + (event.player.posX - event.player.lastTickPosX) * (double) partialTickTime;
            double d1 = event.player.lastTickPosY + (event.player.posY - event.player.lastTickPosY) * (double) partialTickTime;
            double d2 = event.player.lastTickPosZ + (event.player.posZ - event.player.lastTickPosZ) * (double) partialTickTime;
            aabb = aabb.offset(event.target.blockX, event.target.blockY, event.target.blockZ);
            aabb = aabb.expand(f1, f1, f1).getOffsetBoundingBox(-d0, -d1, -d2);
            event.context.drawOutlinedBoundingBox(aabb, -1);
        }



        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        //event.setCanceled(true);
    }

    public AxisAlignedBB[] getSubBoundingBoxes() {
        return new AxisAlignedBB[0];
    }

    public void onClientBlockInteractionByte(byte action) {

    }

    public  void renderCentered(TileEntityRendererRotatable tesr,TileEntity entity)
    {
        //;
    }

}
