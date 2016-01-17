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
import net.minecraft.item.ItemEditableBook;
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
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.util.*;

import static com.parrot.oregano.util.RenderHelper.RenderModelPartWithIcon;
import static com.parrot.oregano.util.RenderHelper.RenderModelWithIcon;



/**
 * Created by smatu on 1/13/2016.
 */
public class TileEntityLectern extends TileEntityContainerRotatable {
    public int pageNumber=0;
    public TileEntityLectern() {
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

        TileEntityLectern lectern = (TileEntityLectern)entity;
        ItemStack stack= lectern.inventoryItems[0];

        if(stack!=null && (stack.getItem() instanceof ItemWritableBook || stack.getItem() instanceof ItemEditableBook)) {

            materialitemicon= Blocks.hardened_clay.getBlockTextureFromSide(0);
            RenderModelPartWithIcon(model, materialitemicon,"BookCover");

            tesr.bindTexturePublic(new ResourceLocation("oregano", "textures/gui/papertex.png"));
            model.renderPart( "BookPaper");

            NBTTagCompound nbttagcompound = stack.getTagCompound();
            NBTTagList pages = (NBTTagList)nbttagcompound.getTagList("pages", 8);
            DynamicTexture dynamicTexture=new DynamicTexture(480, 320);//bufferedImage);;
            BufferedImage bmp=new java.awt.image.BufferedImage(480,320, BufferedImage.TYPE_INT_RGB);
            Graphics gfx = bmp.getGraphics();
            gfx.setColor(Color.BLACK);
            Font f=new Font("Gamaliel",Font.PLAIN,19);
            Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
            attributes.put(TextAttribute.TRACKING, 0.05);
            f = f.deriveFont(attributes);
            gfx.setFont(f);
            gfx.setClip(0,0,240,320);

            int pageNumber=lectern.pageNumber;

            FontRenderer fontrenderer = tesr.func_147498_b();
            int textheight= 28;
            int lineheight=21;

            String s = (pages).getStringTagAt(pageNumber)+"";

            while (s != null && s.endsWith("\n"))
            {
                s = s.substring(0, s.length() - 1);
            }

            java.util.List list = fontrenderer.listFormattedStringToWidth(s, 116);

            for (Iterator iterator = list.iterator(); iterator.hasNext(); textheight += lineheight)
            {
                String s1 = (String)iterator.next();
                gfx.drawString(s1,18,textheight);

            }

            gfx.drawString((pageNumber+1)+"",(240/2)-(gfx.getFontMetrics().stringWidth(pageNumber+"")/2),305);
            gfx.setClip(0,0,480,320);

            pageNumber++;
            if (pages.tagCount()>=pageNumber+1) {
                s = (pages).getStringTagAt(pageNumber)+"";

                while (s != null && s.endsWith("\n"))
                {
                    s = s.substring(0, s.length() - 1);
                }

                list = fontrenderer.listFormattedStringToWidth(s, 116);
                textheight=24;

                for (Iterator iterator = list.iterator(); iterator.hasNext(); textheight += lineheight)
                {
                    String s1 = (String)iterator.next();
                    gfx.drawString(s1,240+18,textheight);

                }

                gfx.drawString((pageNumber+1)+"",360-(gfx.getFontMetrics().stringWidth(pageNumber+"")/2),305);
            }

            WritableRaster raster = bmp.getRaster();
            int[] imgdata=((DataBufferInt)raster.getDataBuffer()).getData();
            TextureUtil.uploadTexture(dynamicTexture.getGlTextureId(), imgdata, 480, 320);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,dynamicTexture.getGlTextureId());

            model.renderPart("BookText");

            dynamicTexture.deleteGlTexture();

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
