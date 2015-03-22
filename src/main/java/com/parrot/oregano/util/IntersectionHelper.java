package com.parrot.oregano.util;

import net.minecraft.util.Vec3;

/**
 * Created by Shane on 3/21/2015.
 */
public class IntersectionHelper {

    public static Vec3 intersectLinePlane(Vec3 lineOrigin, Vec3 lineVec,float length, Vec3 planeBL,Vec3 planeTL,Vec3 planeBR)
    {

        Vec3 planeN=planeBR.subtract(planeBL)
                .crossProduct(planeTL.subtract(planeBL));

        float t =(float)(
                planeN.dotProduct(planeBL.subtract(lineOrigin))
                / planeN.dotProduct(lineVec)
        );

        t = Math.abs(t);

        if( t>0.0F && t<=length ){
            return lineOrigin.addVector(
                    t*lineVec.xCoord,
                    t*lineVec.yCoord,
                    t*lineVec.zCoord
            );
        }

        return null;

    }

}
