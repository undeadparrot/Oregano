package com.parrot.oregano.tileentity;

import com.parrot.oregano.client.render.special.TileEntityRendererRotatable;
import com.parrot.oregano.init.ModModels;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.*;

/**
 * Created by Shane on 3/22/2015.
 */
public class TileEntityBottleRack extends TileEntityContainerRotatable {

    public String texture="minecraft:textures/blocks/brick.png";

    public TileEntityBottleRack()
    {

        LogHelper.info("*bottlerack");

    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
    }

    @Override
    public void renderCentered(TileEntityRendererRotatable tesr, TileEntity entity) {

        WavefrontObject model = (WavefrontObject)ModModels.chestBarrelMini;
        tesr.bindTexturePublic(TextureMap.locationBlocksTexture);

        Tessellator tess = Tessellator.instance;
        tess.startDrawing(4);

        IIcon materialitemicon= Block.getBlockFromItem(GameRegistry.findItem("minecraft","glass")).getBlockTextureFromSide(0);


        for (GroupObject part : model.groupObjects)
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

        tess.draw();

        //model.renderPart("Box");
        //((WavefrontObject)model).renderPart("Lid");
        //tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/iron_block.png"));
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D,37);

        //model.renderPart("Clasp");
        //tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/wool_colored_purple.png"));
        //model.renderPart("Lining");
//
//        Tessellator tess = Tessellator.instance;
//        tess.startDrawing(4);
//        tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/planks_spruce.png"));
//        //tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
//
//
//
//        tess.addVertexWithUV(0.061058,0.177769,-0.228917,0.995624,0.386253);
//        tess.addVertexWithUV(-0.268908,0.20928,-0.228917,1,0.999842);
//        tess.addVertexWithUV(-0.268908,0.176527,-0.097239,0.608965,0.999621);
//
//        tess.addVertexWithUV(0.057746,0.192614,-0.059627,0.500459,0.392698);
//        tess.addVertexWithUV(-0.268908,0.176527,-0.097239,0.608965,0.999621);
//        tess.addVertexWithUV(-0.268908,0.197194,0.109663,0.004121,1);
//
//        tess.addVertexWithUV(0.268908,0.16859,-0.052701,0.476807,0);
//        tess.addVertexWithUV(0.057746,0.192614,-0.059627,0.500459,0.392698);
//        tess.addVertexWithUV(-0.052653,0.167107,0.109663,0,0.597769);
//
//        tess.addVertexWithUV(0.268908,0.211652,-0.228917,1,0.000288);
//        tess.addVertexWithUV(0.061058,0.177769,-0.228917,0.995624,0.386253);
//        tess.addVertexWithUV(0.057746,0.192614,-0.059627,0.500459,0.392698);
//
//        tess.addVertexWithUV(0.057746,0.192614,-0.059627,0.500459,0.392698);
//        tess.addVertexWithUV(0.061058,0.177769,-0.228917,0.995624,0.386253);
//        tess.addVertexWithUV(-0.268908,0.176527,-0.097239,0.608965,0.999621);
//
//        tess.addVertexWithUV(-0.052653,0.167107,0.109663,0,0.597769);
//        tess.addVertexWithUV(0.057746,0.192614,-0.059627,0.500459,0.392698);
//        tess.addVertexWithUV(-0.268908,0.197194,0.109663,0.004121,1);
//
//        tess.addVertexWithUV(0.268908,0.219253,0.109663,0.006734,0.000645);
//        tess.addVertexWithUV(0.268908,0.16859,-0.052701,0.476807,0);
//        tess.addVertexWithUV(-0.052653,0.167107,0.109663,0,0.597769);
//
//        tess.addVertexWithUV(0.268908,0.16859,-0.052701,0.476807,0);
//        tess.addVertexWithUV(0.268908,0.211652,-0.228917,1,0.000288);
//        tess.addVertexWithUV(0.057746,0.192614,-0.059627,0.500459,0.392698);
//
//        tess.addVertexWithUV(-0.026089,0.22019,0.16382,0.055129,0.911597);
//        tess.addVertexWithUV(-0.026089,0.123939,0.16382,0.055129,0.089379);
//        tess.addVertexWithUV(0.026089,0.123939,0.16382,0.939725,0.089379);
//
//        tess.addVertexWithUV(-0.03131,0.115164,0.138373,0.008283,0.008814);
//        tess.addVertexWithUV(0.03131,0.115164,0.138373,0.986571,0.014004);
//        tess.addVertexWithUV(0.026089,0.123939,0.16382,0.939725,0.089379);
//
//        tess.addVertexWithUV(-0.03131,0.115164,0.138373,0.008283,0.008814);
//        tess.addVertexWithUV(-0.026089,0.123939,0.16382,0.055129,0.089379);
//        tess.addVertexWithUV(-0.026089,0.22019,0.16382,0.055129,0.911597);
//
//        tess.addVertexWithUV(-0.03131,0.228966,0.138373,0.008283,0.986932);
//        tess.addVertexWithUV(-0.026089,0.22019,0.16382,0.055129,0.911597);
//        tess.addVertexWithUV(0.026089,0.22019,0.16382,0.939725,0.911597);
//
//        tess.addVertexWithUV(0.03131,0.228966,0.138373,0.986571,0.986932);
//        tess.addVertexWithUV(0.026089,0.22019,0.16382,0.939725,0.911597);
//        tess.addVertexWithUV(0.026089,0.123939,0.16382,0.939725,0.089379);
//
//        tess.addVertexWithUV(0.026089,0.22019,0.16382,0.939725,0.911597);
//        tess.addVertexWithUV(-0.026089,0.22019,0.16382,0.055129,0.911597);
//        tess.addVertexWithUV(0.026089,0.123939,0.16382,0.939725,0.089379);
//
//        tess.addVertexWithUV(-0.026089,0.123939,0.16382,0.055129,0.089379);
//        tess.addVertexWithUV(-0.03131,0.115164,0.138373,0.008283,0.008814);
//        tess.addVertexWithUV(0.026089,0.123939,0.16382,0.939725,0.089379);
//
//        tess.addVertexWithUV(-0.03131,0.228966,0.138373,0.008283,0.986932);
//        tess.addVertexWithUV(-0.03131,0.115164,0.138373,0.008283,0.008814);
//        tess.addVertexWithUV(-0.026089,0.22019,0.16382,0.055129,0.911597);
//
//        tess.addVertexWithUV(0.03131,0.228966,0.138373,0.986571,0.986932);
//        tess.addVertexWithUV(-0.03131,0.228966,0.138373,0.008283,0.986932);
//        tess.addVertexWithUV(0.026089,0.22019,0.16382,0.939725,0.911597);
//
//        tess.addVertexWithUV(0.03131,0.115164,0.138373,0.986571,0.014004);
//        tess.addVertexWithUV(0.03131,0.228966,0.138373,0.986571,0.986932);
//        tess.addVertexWithUV(0.026089,0.123939,0.16382,0.939725,0.089379);
//
//        tess.addVertexWithUV(0.298786,0.259659,-0.257627,0.99991,0.803475);
//        tess.addVertexWithUV(-0.298786,0.259659,-0.257627,9E-05,0.803475);
//        tess.addVertexWithUV(-0.298786,0.260926,-0.331761,9E-05,0.67942);
//
//        tess.addVertexWithUV(0.298786,0.513946,-0.508927,0.99991,0.124144);
//        tess.addVertexWithUV(-0.298786,0.513946,-0.508927,9E-05,0.124145);
//        tess.addVertexWithUV(-0.298786,0.584043,-0.484763,9E-05,9.1E-05);
//
//        tess.addVertexWithUV(0.298786,0.400927,-0.49083,0.99991,0.315649);
//        tess.addVertexWithUV(-0.298786,0.400927,-0.49083,9E-05,0.31565);
//        tess.addVertexWithUV(-0.298786,0.513946,-0.508927,9E-05,0.124145);
//
//        tess.addVertexWithUV(0.298786,0.400927,-0.49083,0.99991,0.315649);
//        tess.addVertexWithUV(0.298786,0.316588,-0.431774,0.99991,0.487916);
//        tess.addVertexWithUV(-0.298786,0.316587,-0.431774,9E-05,0.487916);
//
//        tess.addVertexWithUV(0.298786,0.316588,-0.431774,0.99991,0.487916);
//        tess.addVertexWithUV(0.298786,0.260926,-0.331761,0.99991,0.679421);
//        tess.addVertexWithUV(-0.298786,0.260926,-0.331761,9E-05,0.67942);
//
//        tess.addVertexWithUV(-0.298786,0.584043,-0.484763,5.3E-05,0.277801);
//        tess.addVertexWithUV(-0.298786,0.513946,-0.508927,0.110041,0.126302);
//        tess.addVertexWithUV(-0.298786,0.400927,-0.49083,0.370014,5.3E-05);
//
//        tess.addVertexWithUV(0.298786,0.259659,-0.257627,0.999947,5.3E-05);
//        tess.addVertexWithUV(0.298786,0.260926,-0.331761,0.889959,0.151552);
//        tess.addVertexWithUV(0.298786,0.316588,-0.431774,0.629986,0.277801);
//
//        tess.addVertexWithUV(-0.2615,0.294107,-0.339723,9E-05,0.67942);
//        tess.addVertexWithUV(-0.2615,0.293104,-0.281072,9E-05,0.803475);
//        tess.addVertexWithUV(0.2615,0.293104,-0.281072,0.99991,0.803475);
//
//        tess.addVertexWithUV(-0.2615,0.549739,-0.46077,9E-05,9.1E-05);
//        tess.addVertexWithUV(-0.2615,0.494282,-0.479887,9E-05,0.124145);
//        tess.addVertexWithUV(0.2615,0.494282,-0.479887,0.99991,0.124144);
//
//        tess.addVertexWithUV(0.2615,0.494282,-0.479887,0.99991,0.124144);
//        tess.addVertexWithUV(-0.2615,0.494282,-0.479887,9E-05,0.124145);
//        tess.addVertexWithUV(-0.2615,0.404868,-0.465569,9E-05,0.31565);
//
//        tess.addVertexWithUV(-0.2615,0.404868,-0.465569,9E-05,0.31565);
//        tess.addVertexWithUV(-0.2615,0.338143,-0.418848,9E-05,0.487916);
//        tess.addVertexWithUV(0.2615,0.338143,-0.418848,0.99991,0.487916);
//
//        tess.addVertexWithUV(-0.2615,0.338143,-0.418848,9E-05,0.487916);
//        tess.addVertexWithUV(-0.2615,0.294107,-0.339723,9E-05,0.67942);
//        tess.addVertexWithUV(0.2615,0.294107,-0.339723,0.99991,0.679421);
//
//        tess.addVertexWithUV(-0.2615,0.293104,-0.281072,0.999947,0.277801);
//        tess.addVertexWithUV(-0.2615,0.294107,-0.339723,0.889959,0.126302);
//        tess.addVertexWithUV(-0.2615,0.338143,-0.418848,0.629987,5.3E-05);
//
//        tess.addVertexWithUV(0.2615,0.549739,-0.46077,9E-05,9.1E-05);
//        tess.addVertexWithUV(0.2615,0.494282,-0.479887,0.110041,0.151552);
//        tess.addVertexWithUV(0.2615,0.404868,-0.465569,0.370014,0.277801);
//
//        tess.addVertexWithUV(0.298786,0.584043,-0.484763,0.999947,5.3E-05);
//        tess.addVertexWithUV(0.2615,0.549739,-0.46077,0.937532,0.070124);
//        tess.addVertexWithUV(0.2615,0.293104,-0.281072,0.937533,0.594315);
//
//        tess.addVertexWithUV(-0.2615,0.293104,-0.281072,0.062468,0.594316);
//        tess.addVertexWithUV(-0.2615,0.549739,-0.46077,0.062467,0.070125);
//        tess.addVertexWithUV(-0.298786,0.584043,-0.484763,9E-05,9.1E-05);
//
//        tess.addVertexWithUV(0.2615,0.293104,-0.281072,0.937533,0.594315);
//        tess.addVertexWithUV(-0.2615,0.293104,-0.281072,0.062468,0.594316);
//        tess.addVertexWithUV(-0.298786,0.259659,-0.257627,8.2E-05,0.662656);
//
//        tess.addVertexWithUV(-0.2615,0.549739,-0.46077,0.062467,0.070125);
//        tess.addVertexWithUV(0.2615,0.549739,-0.46077,0.937532,0.070124);
//        tess.addVertexWithUV(0.298786,0.584043,-0.484763,0.999947,5.3E-05);
//
//        tess.addVertexWithUV(0.298786,0.260926,-0.331761,0.99991,0.679421);
//        tess.addVertexWithUV(0.298786,0.259659,-0.257627,0.99991,0.803475);
//        tess.addVertexWithUV(-0.298786,0.260926,-0.331761,9E-05,0.67942);
//
//        tess.addVertexWithUV(0.298786,0.584043,-0.484763,0.999947,5.3E-05);
//        tess.addVertexWithUV(0.298786,0.513946,-0.508927,0.99991,0.124144);
//        tess.addVertexWithUV(-0.298786,0.584043,-0.484763,9E-05,9.1E-05);
//
//        tess.addVertexWithUV(0.298786,0.513946,-0.508927,0.99991,0.124144);
//        tess.addVertexWithUV(0.298786,0.400927,-0.49083,0.99991,0.315649);
//        tess.addVertexWithUV(-0.298786,0.513946,-0.508927,9E-05,0.124145);
//
//        tess.addVertexWithUV(-0.298786,0.400927,-0.49083,9E-05,0.31565);
//        tess.addVertexWithUV(0.298786,0.400927,-0.49083,0.99991,0.315649);
//        tess.addVertexWithUV(-0.298786,0.316587,-0.431774,9E-05,0.487916);
//
//        tess.addVertexWithUV(-0.298786,0.316587,-0.431774,9E-05,0.487916);
//        tess.addVertexWithUV(0.298786,0.316588,-0.431774,0.99991,0.487916);
//        tess.addVertexWithUV(-0.298786,0.260926,-0.331761,9E-05,0.67942);
//
//        tess.addVertexWithUV(-0.298786,0.260926,-0.331761,0.889959,0.126302);
//        tess.addVertexWithUV(-0.298786,0.259659,-0.257627,0.999947,0.277801);
//        tess.addVertexWithUV(-0.298786,0.316587,-0.431774,0.629987,5.3E-05);
//
//        tess.addVertexWithUV(0.298786,0.513946,-0.508927,0.110041,0.151552);
//        tess.addVertexWithUV(0.298786,0.584043,-0.484763,9E-05,9.1E-05);
//        tess.addVertexWithUV(0.298786,0.400927,-0.49083,0.370014,0.277801);
//
//        tess.addVertexWithUV(-0.298786,0.584043,-0.484763,5.3E-05,0.277801);
//        tess.addVertexWithUV(-0.298786,0.400927,-0.49083,0.370014,5.3E-05);
//        tess.addVertexWithUV(-0.298786,0.259659,-0.257627,0.999947,0.277801);
//
//        tess.addVertexWithUV(-0.298786,0.259659,-0.257627,0.999947,0.277801);
//        tess.addVertexWithUV(-0.298786,0.400927,-0.49083,0.370014,5.3E-05);
//        tess.addVertexWithUV(-0.298786,0.316587,-0.431774,0.629987,5.3E-05);
//
//        tess.addVertexWithUV(0.2615,0.294107,-0.339723,0.99991,0.679421);
//        tess.addVertexWithUV(-0.2615,0.294107,-0.339723,9E-05,0.67942);
//        tess.addVertexWithUV(0.2615,0.293104,-0.281072,0.99991,0.803475);
//
//        tess.addVertexWithUV(0.298786,0.259659,-0.257627,0.999947,5.3E-05);
//        tess.addVertexWithUV(0.298786,0.316588,-0.431774,0.629986,0.277801);
//        tess.addVertexWithUV(0.298786,0.584043,-0.484763,9E-05,9.1E-05);
//
//        tess.addVertexWithUV(0.298786,0.584043,-0.484763,9E-05,9.1E-05);
//        tess.addVertexWithUV(0.298786,0.316588,-0.431774,0.629986,0.277801);
//        tess.addVertexWithUV(0.298786,0.400927,-0.49083,0.370014,0.277801);
//
//        tess.addVertexWithUV(0.2615,0.549739,-0.46077,0.999947,5.3E-05);
//        tess.addVertexWithUV(-0.2615,0.549739,-0.46077,9E-05,9.1E-05);
//        tess.addVertexWithUV(0.2615,0.494282,-0.479887,0.99991,0.124144);
//
//        tess.addVertexWithUV(0.2615,0.404868,-0.465569,0.99991,0.315649);
//        tess.addVertexWithUV(0.2615,0.494282,-0.479887,0.99991,0.124144);
//        tess.addVertexWithUV(-0.2615,0.404868,-0.465569,9E-05,0.31565);
//
//        tess.addVertexWithUV(0.2615,0.404868,-0.465569,0.99991,0.315649);
//        tess.addVertexWithUV(-0.2615,0.404868,-0.465569,9E-05,0.31565);
//        tess.addVertexWithUV(0.2615,0.338143,-0.418848,0.99991,0.487916);
//
//        tess.addVertexWithUV(0.2615,0.338143,-0.418848,0.99991,0.487916);
//        tess.addVertexWithUV(-0.2615,0.338143,-0.418848,9E-05,0.487916);
//        tess.addVertexWithUV(0.2615,0.294107,-0.339723,0.99991,0.679421);
//
//        tess.addVertexWithUV(0.2615,0.338143,-0.418848,0.629986,0.277801);
//        tess.addVertexWithUV(0.2615,0.294107,-0.339723,0.889959,0.151552);
//        tess.addVertexWithUV(0.2615,0.293104,-0.281072,0.999947,5.3E-05);
//
//        tess.addVertexWithUV(-0.2615,0.404868,-0.465569,0.370014,5.3E-05);
//        tess.addVertexWithUV(-0.2615,0.494282,-0.479887,0.110041,0.126302);
//        tess.addVertexWithUV(-0.2615,0.549739,-0.46077,5.3E-05,0.277801);
//
//        tess.addVertexWithUV(-0.2615,0.404868,-0.465569,0.370014,5.3E-05);
//        tess.addVertexWithUV(-0.2615,0.293104,-0.281072,0.999947,0.277801);
//        tess.addVertexWithUV(-0.2615,0.338143,-0.418848,0.629987,5.3E-05);
//
//        tess.addVertexWithUV(-0.298786,0.259659,-0.257627,8.2E-05,0.662656);
//        tess.addVertexWithUV(-0.2615,0.293104,-0.281072,0.062468,0.594316);
//        tess.addVertexWithUV(-0.298786,0.584043,-0.484763,9E-05,9.1E-05);
//
//        tess.addVertexWithUV(-0.2615,0.404868,-0.465569,0.370014,5.3E-05);
//        tess.addVertexWithUV(-0.2615,0.549739,-0.46077,5.3E-05,0.277801);
//        tess.addVertexWithUV(-0.2615,0.293104,-0.281072,0.999947,0.277801);
//
//        tess.addVertexWithUV(0.2615,0.338143,-0.418848,0.629986,0.277801);
//        tess.addVertexWithUV(0.2615,0.549739,-0.46077,9E-05,9.1E-05);
//        tess.addVertexWithUV(0.2615,0.404868,-0.465569,0.370014,0.277801);
//
//        tess.addVertexWithUV(0.298786,0.259659,-0.257627,0.999919,0.662656);
//        tess.addVertexWithUV(0.298786,0.584043,-0.484763,0.999947,5.3E-05);
//        tess.addVertexWithUV(0.2615,0.293104,-0.281072,0.937533,0.594315);
//
//        tess.addVertexWithUV(0.2615,0.338143,-0.418848,0.629986,0.277801);
//        tess.addVertexWithUV(0.2615,0.293104,-0.281072,0.999947,5.3E-05);
//        tess.addVertexWithUV(0.2615,0.549739,-0.46077,9E-05,9.1E-05);
//
//        tess.addVertexWithUV(0.298786,0.259659,-0.257627,0.999919,0.662656);
//        tess.addVertexWithUV(0.2615,0.293104,-0.281072,0.937533,0.594315);
//        tess.addVertexWithUV(-0.298786,0.259659,-0.257627,8.2E-05,0.662656);
//
//        tess.addVertexWithUV(-0.298786,0.584043,-0.484763,9E-05,9.1E-05);
//        tess.addVertexWithUV(-0.2615,0.549739,-0.46077,0.062467,0.070125);
//        tess.addVertexWithUV(0.298786,0.584043,-0.484763,0.999947,5.3E-05);
//
//        tess.addVertexWithUV(-0.298786,0.26,0.138373,0.1,0.9);
//        tess.addVertexWithUV(-0.298786,0,0.138373,0.9,0.9);
//        tess.addVertexWithUV(0.298786,0,0.138373,0.9,0.1);
//
//        tess.addVertexWithUV(-0.298786,0.26,-0.257627,0.1,0.9);
//        tess.addVertexWithUV(-0.298786,0,-0.257627,0.9,0.9);
//        tess.addVertexWithUV(-0.298786,0,0.138373,0.9,0.1);
//
//        tess.addVertexWithUV(0.298786,0.26,-0.257627,0.1,0.9);
//        tess.addVertexWithUV(0.298786,0,-0.257627,0.9,0.9);
//        tess.addVertexWithUV(-0.298786,0,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(0.298786,0.26,0.138373,0.1,0.9);
//        tess.addVertexWithUV(0.298786,0,0.138373,0.9,0.9);
//        tess.addVertexWithUV(0.298786,0,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(-0.298786,0,0.138373,0.1,0.9);
//        tess.addVertexWithUV(-0.298786,0,-0.257627,0.9,0.9);
//        tess.addVertexWithUV(0.298786,0,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(0.268908,0,0.109663,0.9,0.1);
//        tess.addVertexWithUV(-0.268908,0,0.109663,0.9,0.9);
//        tess.addVertexWithUV(-0.268908,0.26,0.109663,0.1,0.9);
//
//        tess.addVertexWithUV(-0.268908,0,0.109663,0.9,0.1);
//        tess.addVertexWithUV(-0.268908,0,-0.228917,0.9,0.9);
//        tess.addVertexWithUV(-0.268908,0.26,-0.228917,0.1,0.9);
//
//        tess.addVertexWithUV(-0.268908,0,-0.228917,0.9,0.1);
//        tess.addVertexWithUV(0.268908,0,-0.228917,0.9,0.9);
//        tess.addVertexWithUV(0.268908,0.26,-0.228917,0.1,0.9);
//
//        tess.addVertexWithUV(0.268908,0,-0.228917,0.9,0.1);
//        tess.addVertexWithUV(0.268908,0,0.109663,0.9,0.9);
//        tess.addVertexWithUV(0.268908,0.26,0.109663,0.1,0.9);
//
//        tess.addVertexWithUV(0.268908,0,-0.228917,0.9,0.1);
//        tess.addVertexWithUV(-0.268908,0,-0.228917,0.9,0.9);
//        tess.addVertexWithUV(-0.268908,0,0.109663,0.1,0.9);
//
//        tess.addVertexWithUV(0.268908,0.26,-0.228917,0.1,0.9);
//        tess.addVertexWithUV(0.268908,0.26,0.109663,0.9,0.9);
//        tess.addVertexWithUV(0.298786,0.26,0.138373,0.9,0.1);
//
//        tess.addVertexWithUV(-0.268908,0.26,-0.228917,0.1,0.9);
//        tess.addVertexWithUV(0.268908,0.26,-0.228917,0.9,0.9);
//        tess.addVertexWithUV(0.298786,0.26,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(-0.268908,0.26,0.109663,0.1,0.9);
//        tess.addVertexWithUV(-0.268908,0.26,-0.228917,0.9,0.9);
//        tess.addVertexWithUV(-0.298786,0.26,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(0.268908,0.26,0.109663,0.1,0.9);
//        tess.addVertexWithUV(-0.268908,0.26,0.109663,0.9,0.9);
//        tess.addVertexWithUV(-0.298786,0.26,0.138373,0.9,0.1);
//
//        tess.addVertexWithUV(0.298786,0.26,0.138373,0.1,0.104286);
//        tess.addVertexWithUV(-0.298786,0.26,0.138373,0.1,0.9);
//        tess.addVertexWithUV(0.298786,0,0.138373,0.9,0.1);
//
//        tess.addVertexWithUV(-0.298786,0.26,0.138373,0.1,0.1);
//        tess.addVertexWithUV(-0.298786,0.26,-0.257627,0.1,0.9);
//        tess.addVertexWithUV(-0.298786,0,0.138373,0.9,0.1);
//
//        tess.addVertexWithUV(-0.298786,0.26,-0.257627,0.1,0.1);
//        tess.addVertexWithUV(0.298786,0.26,-0.257627,0.1,0.9);
//        tess.addVertexWithUV(-0.298786,0,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(0.298786,0.26,-0.257627,0.1,0.1);
//        tess.addVertexWithUV(0.298786,0.26,0.138373,0.1,0.9);
//        tess.addVertexWithUV(0.298786,0,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(0.298786,0,0.138373,0.1,0.1);
//        tess.addVertexWithUV(-0.298786,0,0.138373,0.1,0.9);
//        tess.addVertexWithUV(0.298786,0,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(0.268908,0.26,0.109663,0.1,0.1);
//        tess.addVertexWithUV(0.268908,0,0.109663,0.9,0.1);
//        tess.addVertexWithUV(-0.268908,0.26,0.109663,0.1,0.9);
//
//        tess.addVertexWithUV(-0.268908,0.26,0.109663,0.1,0.1);
//        tess.addVertexWithUV(-0.268908,0,0.109663,0.9,0.1);
//        tess.addVertexWithUV(-0.268908,0.26,-0.228917,0.1,0.9);
//
//        tess.addVertexWithUV(-0.268908,0.26,-0.228917,0.1,0.1);
//        tess.addVertexWithUV(-0.268908,0,-0.228917,0.9,0.1);
//        tess.addVertexWithUV(0.268908,0.26,-0.228917,0.1,0.9);
//
//        tess.addVertexWithUV(0.268908,0.26,-0.228917,0.1,0.1);
//        tess.addVertexWithUV(0.268908,0,-0.228917,0.9,0.1);
//        tess.addVertexWithUV(0.268908,0.26,0.109663,0.1,0.9);
//
//        tess.addVertexWithUV(0.268908,0,0.109663,0.1,0.1);
//        tess.addVertexWithUV(0.268908,0,-0.228917,0.9,0.1);
//        tess.addVertexWithUV(-0.268908,0,0.109663,0.1,0.9);
//
//        tess.addVertexWithUV(0.298786,0.26,-0.257627,0.1,0.1);
//        tess.addVertexWithUV(0.268908,0.26,-0.228917,0.1,0.9);
//        tess.addVertexWithUV(0.298786,0.26,0.138373,0.9,0.1);
//
//        tess.addVertexWithUV(-0.298786,0.26,-0.257627,0.1,0.1);
//        tess.addVertexWithUV(-0.268908,0.26,-0.228917,0.1,0.9);
//        tess.addVertexWithUV(0.298786,0.26,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(-0.298786,0.26,0.138373,0.1,0.1);
//        tess.addVertexWithUV(-0.268908,0.26,0.109663,0.1,0.9);
//        tess.addVertexWithUV(-0.298786,0.26,-0.257627,0.9,0.1);
//
//        tess.addVertexWithUV(0.298786,0.26,0.138373,0.1,0.104286);
//        tess.addVertexWithUV(0.268908,0.26,0.109663,0.1,0.9);
//        tess.addVertexWithUV(-0.298786,0.26,0.138373,0.9,0.1);
//
//        tess.draw();


    }

}
