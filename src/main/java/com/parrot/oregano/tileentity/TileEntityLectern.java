package com.parrot.oregano.tileentity;

import com.parrot.oregano.client.render.special.TileEntityRendererRotatable;
import com.parrot.oregano.init.ModModels;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.*;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;

import static com.parrot.oregano.util.RenderHelper.RenderModelPartWithIcon;
import static com.parrot.oregano.util.RenderHelper.RenderModelWithIcon;

/**
 * Created by smatu on 1/13/2016.
 */
public class TileEntityLectern extends TileEntityContainerRotatable {
    public int pageNumber=0;
    public TileEntityLectern() {
        if(Loader.isModLoaded("chisel"))
        {
            LogHelper.info("Chisel present");

        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }

    public int howManyInvSlots() {
        return 1;
    }

    @Override
    public void NbtAfterParty(NBTTagCompound nbt) {
        pageNumber=nbt.getInteger("page");
        super.NbtAfterParty(nbt);
    }

    @Override
    public void NbtPreParty(NBTTagCompound nbt) {
            nbt.setInteger("page",pageNumber);
        super.NbtPreParty(nbt);
    }

    @Override
    public void renderCentered(TileEntityRendererRotatable tesr, TileEntity entity) {
        WavefrontObject model = (WavefrontObject) ModModels.lecternDouble;

        tesr.bindTexturePublic(TextureMap.locationBlocksTexture);

        IIcon materialitemicon= Blocks.planks.getBlockTextureFromSide(0);
        RenderModelPartWithIcon(model, materialitemicon,"Base");

        tesr.bindTexturePublic(new ResourceLocation("oregano", "textures/gui/papertex.png"));
        model.renderPart( "BookPaper");

        DynamicTexture dynamicTexture=new DynamicTexture(256, 256);//bufferedImage);;
        BufferedImage bmp=new java.awt.image.BufferedImage(256,256, BufferedImage.TYPE_INT_RGB);
        Graphics gfx = bmp.getGraphics();
        gfx.setColor(Color.BLACK);
        Font f=new Font("Gamaliel",Font.PLAIN,15);
        gfx.setFont(f);
        gfx.drawString("Rose, she is red",18,24);
        gfx.drawString("The violets, they be blue",18,48);
        gfx.drawString("This would be a rhyme",18,72);
        gfx.drawString("If this line rhymed with blue",18,96);
        gfx.drawString("I once sang a sonnet",18,120);
        gfx.drawString("But the sonnet played me",18,148);
        gfx.drawString("I should not have done't",18,172);
        gfx.drawString("But I couldn't leave't be",18,196);

        WritableRaster raster = bmp.getRaster();
        int[] imgdata=((DataBufferInt)raster.getDataBuffer()).getData();
        TextureUtil.uploadTexture(dynamicTexture.getGlTextureId(), imgdata, 256, 256);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,dynamicTexture.getGlTextureId());

        model.renderPart("BookPaper");

        dynamicTexture.deleteGlTexture();

        TileEntityLectern lectern = (TileEntityLectern)entity;
        ItemStack stack= lectern.inventoryItems[0];

        if(stack!=null && stack.getItem() instanceof ItemWritableBook) {

            NBTTagCompound nbttagcompound = stack.getTagCompound();
            String s = ((NBTTagList)nbttagcompound.getTagList("pages", 8)).getStringTagAt(0)+"";

            float pageAngle=3.0F;

            GL11.glPushMatrix();
            GL11.glTranslatef(0.200F,0.8F,1.65F);
            GL11.glPushMatrix();
            GL11.glRotatef(-pageAngle,0.0F,1.0F,-1.0F);
            GL11.glRotatef(-45.0F,1.0F,0.0F,0.0F);

            drawPageText(tesr,  s);
            GL11.glPopMatrix();
            GL11.glPopMatrix();


            GL11.glPushMatrix();
            GL11.glTranslatef(01.20F,0.8F,1.7F);
            GL11.glPushMatrix();
            GL11.glRotatef(pageAngle,0.0F,1.0F,-1.0F);
            GL11.glRotatef(-45.0F,1.0F,0.0F,0.0F);

            drawPageText(tesr,  s);
            GL11.glPopMatrix();
            GL11.glPopMatrix();


        }




    }

    void drawPageText(TileEntityRendererRotatable tesr , String s) {
        FontRenderer fontrenderer = tesr.func_147498_b();
        GL11.glPushMatrix();
        GL11.glScalef(0.006f, -0.008f, 0.006f);
        GL11.glColor4f(1.0F, 01.0F, 1.0F, 01.0F);
//
//        fontrenderer.drawString("("+fontrenderer.getStringWidth(s)+","+
//                        fontrenderer.splitStringWidth(s,
//                                101)+")",
//                0,
//                -99,
//                0);
        fontrenderer.drawSplitString(s,0,
                -92,
                116,
                0);

        GL11.glPopMatrix();
    }

    @Override
    public void PacketAfterParty(ByteBuf buf) {
        pageNumber=buf.readInt();
        super.PacketAfterParty(buf);
    }

    @Override
    public void PacketPreParty(ByteBuf buf) {
        buf.writeInt(pageNumber);
        super.PacketPreParty(buf);
    }

}
