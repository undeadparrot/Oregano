package com.parrot.oregano.client.render.tileentity;

import com.parrot.oregano.block.BlockCanvas;
import com.parrot.oregano.client.render.DynamicTextureRegister;
import com.parrot.oregano.tileentity.TileEntityCanvas;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by Shane on 3/14/2015.
 */
public class TileEntityRendererCanvas extends TileEntitySpecialRenderer {



    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {



        TileEntityCanvas tileEntity=(TileEntityCanvas)entity;

        TextureManager texManager= FMLClientHandler.instance().getClient().getTextureManager();


        GL11.glPushMatrix();



        DynamicTextureRegister.getInstance().updateDynamicTexture("canwors" ,tileEntity.width, tileEntity.height,tileEntity.data);


//            DynamicTexture dynamicTexture = new DynamicTexture(4, 4);
//            int numpix = dynamicTexture.getTextureData().length;
//            for(int i=0;i<numpix;i++) {
//                dynamicTexture.getTextureData()[i] = i*8;
//            }
//            dynamicTexture.updateDynamicTexture();

//


            //dynamicTexture = new DynamicTexture(64,64);//bufferedImage);
            //dynamicTextureLocation=texManager.getDynamicTextureLocation("canwors",dynamicTexture);
        //texManager.new DynamicTexture(64,64);

            texManager.bindTexture(
                    DynamicTextureRegister.getInstance().getResourceLocation("canwors" ,tileEntity.width,tileEntity.height));

            GL11.glTranslatef((float) x , (float) y , (float) z);
            GL11.glTranslatef( 0.5F , 0.0F , 0.5F);

            GL11.glPushMatrix();
//
                if(tileEntity.data[01]>tileEntity.data[02])
                {
                    //GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                }
                else
                {
                    //GL11.glRotatef(0F, 0.0F, 01.0F, 0.0F);
                }
                Tessellator tessellator = Tessellator.instance;
                int lightValue = entity.getWorldObj().getBlock((int)x,(int)y,(int)z).getMixedBrightnessForBlock(entity.getWorldObj(), (int)x, (int)y, (int)z);
                tessellator.setBrightness(lightValue);

                float depth=-0.50F+BlockCanvas.thickness;
                float depth2=0.02F+depth;

                float tilt=tileEntity.tilt;

                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(-0.5F,   0.0F,   depth+tilt,    0.0F, 1.0F);
                tessellator.addVertexWithUV(0.5F,   0.0F,   depth+tilt,    1.0F, 1.0F);
                tessellator.addVertexWithUV(0.5F,   1.0F,   depth-tilt,    1.0F, 0.0F);
                tessellator.addVertexWithUV(-0.5F,   1.0F,   depth-tilt, 0.0F, 0.0F);
                tessellator.draw();


                //Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/planks.png"));
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(-0.55F,   0.0F,   depth2,    0.0F, 1.0F);
                tessellator.addVertexWithUV(-0.5F,   0.0F,   depth2,    1.0F, 1.0F);
                tessellator.addVertexWithUV(-0.5F,   1.0F,   depth2,    1.0F, 0.0F);
                tessellator.addVertexWithUV(-0.55F,   1.0F,   depth2, 0.0F, 0.0F);
                tessellator.draw();

                //tessellateCenteredCubeWithUV(tessellator,1.0F,1.0F, BlockCanvas.thickness,0.0F,0.0F,1.0F);

            GL11.glPopMatrix();

            texManager.bindTexture(new ResourceLocation("textures/gui/container/anvil.png"));

        GL11.glPopMatrix();

        if(tileEntity.testPosition!=null)
        {
            FontRenderer fontrenderer = this.func_147498_b();
            GL11.glPushMatrix();
                GL11.glTranslatef((float)x,(float)y,(float)z+0.15F);
                GL11.glScalef(0.009f, -0.009f, 0.009f);
                fontrenderer.drawString("oOo"+Math.round(tileEntity.testPosition.xCoord*10)+" "+Math.round(tileEntity.testPosition.yCoord*10)+" "+Math.round(tileEntity.testPosition.zCoord*10),
                    -fontrenderer.getStringWidth("oOo") / 2,
                    10,
                    0);
                fontrenderer.drawString("_"+x,
                    -fontrenderer.getStringWidth("oOo") / 2,
                    20,
                    0);
                 fontrenderer.drawString("_"+((float)tileEntity.testPosition.xCoord+x),
                    -fontrenderer.getStringWidth("oOo") / 2,
                    30,
                    0);
            //
            GL11.glPopMatrix();

//            GL11.glPushMatrix();
//                GL11.glTranslatef((float)tileEntity.testPosition.xCoord+(float)x,(float)tileEntity.testPosition.yCoord+(float)y,(float)tileEntity.testPosition.zCoord+(float)z+0.05F);
//                //GL11.glTranslatef((float)x,(float)y,(float)z+0.25F);
//                GL11.glScalef(0.009f, -0.009f, 0.009f);
//                fontrenderer.drawString("^O^",
//                    -fontrenderer.getStringWidth("oOo") / 2,
//                    0,
//                    0);
//
//            GL11.glPopMatrix();

            //tessellateDebugPoint(tileEntity.testPosition,"H",fontrenderer,x,y,z);

            for(Map.Entry<String,Vec3>entry:tileEntity.debugVecMap.entrySet())
            {
                tessellateDebugPoint(entry.getValue(),entry.getKey(),fontrenderer,x,y,z,(entry.getKey().contains("_dot"))?true:false);
            }

        }

    }

    public void tessellateDebugPoint(Vec3 pv,String nametag, FontRenderer fontrenderer,double x, double y, double z, boolean dotOnly)
    {

        GL11.glPushMatrix();
        GL11.glTranslatef((float)pv.xCoord+(float)x,(float)pv.yCoord+(float)y,(float)pv.zCoord+(float)z+0.02F);
        //GL11.glTranslatef((float)x,(float)y,(float)z+0.25F);
        GL11.glScalef(0.009f, -0.009f, 0.009f);
        fontrenderer.drawString((dotOnly)?".":nametag,
                -fontrenderer.getStringWidth((dotOnly)?".":nametag) / 2,
                0,
                0);

        GL11.glPopMatrix();
    }

    public static void tessellateGridWithUV(Tessellator tessellator, float size, float uvsizex, float uvsizey, float w, float h ) {
        GL11.glPushMatrix();
        tessellator.startDrawingQuads();
        //GL11.glTranslatef(x/2.0F,y/2.0F,z/2.0F);
        //GL11.glScalef(x, y, z);
        for(int x=0;x<w;x++)
        {
            for(int y=0;y<h;y++)
            {

            }
        }
        tessellator.addVertexWithUV(0,      0,      0,    0,     0);
        tessellator.addVertexWithUV(size,   0,      0,    uvsizex,     0);
        tessellator.addVertexWithUV(size,   size,      0,    uvsizex,      uvsizey);
        tessellator.addVertexWithUV(0,      size,      0,    0,      uvsizey);
//        tessellator.addVertexWithUV(0, 0, 0,    uvx2, uvy);
//        tessellator.addVertexWithUV(0, 0, 0,    uvx2, uvy2);
//        tessellator.addVertexWithUV(0, 0, 0,    uvx, uvy2);
//        tessellator.addVertexWithUV(0, 0, 0,    uvx, uvy);

        tessellator.draw();
        GL11.glPopMatrix();


    }
    public static void tessellateCenteredCubeWithUV(Tessellator tessellator, float x,float y, float z, float uvx, float uvy,float uvsize ) {
        float uvx2=uvx+uvsize;
        float uvy2=uvy+uvsize;
        GL11.glPushMatrix();
        tessellator.startDrawingQuads();
        //GL11.glTranslatef(x/2.0F,y/2.0F,z/2.0F);
        GL11.glScalef(x, y, z);

        tessellator.addVertexWithUV(0.5F, 0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(0.5F, -0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(0.5F, -0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(0.5F, 0.5F, -0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(-0.5F, -0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(-0.5F, 0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(-0.5F, 0.5F, -0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(-0.5F, -0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(0.5F, -0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(0.5F, 0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(-0.5F, 0.5F, 0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(-0.5F, -0.5F, 0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(0.5F, 0.5F, -0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(0.5F, -0.5F, -0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(-0.5F, -0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(-0.5F, 0.5F, -0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(0.5F, -0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(-0.5F, -0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(-0.5F, -0.5F, -0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(0.5F, -0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(-0.5F, 0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(0.5F, 0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(0.5F, 0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(-0.5F, 0.5F, -0.5F,    uvx, uvy);
        tessellator.draw();
        GL11.glPopMatrix();


    }



}


