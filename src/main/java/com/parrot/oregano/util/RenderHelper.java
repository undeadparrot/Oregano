package com.parrot.oregano.util;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.model.obj.*;

/**
 * Created by smatu on 1/13/2016.
 */
public class RenderHelper {

    public static void RenderModelWithIcon(WavefrontObject model, IIcon materialitemicon) {
        Tessellator tess = Tessellator.instance;
        tess.startDrawing(4);

        for (GroupObject part : model.groupObjects)
        {
            TesselateModelPart(materialitemicon, tess, part);
        }

        tess.draw();
    }
    public static void RenderModelPartWithIcon(WavefrontObject model, IIcon materialitemicon,String partname) {

        partname=partname.toLowerCase();

        Tessellator tess = Tessellator.instance;
        tess.startDrawing(4);

        for (GroupObject part : model.groupObjects)
        {
            if(part.name.toLowerCase().contains(partname)) {
                TesselateModelPart(materialitemicon, tess, part);
            }
        }

        tess.draw();
    }

    static void TesselateModelPart(IIcon materialitemicon, Tessellator tess, GroupObject part) {
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
