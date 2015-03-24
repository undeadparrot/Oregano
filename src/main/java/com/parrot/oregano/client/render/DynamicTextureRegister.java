package com.parrot.oregano.client.render;

import com.google.common.collect.Maps;
import com.parrot.oregano.tileentity.TileEntityCanvas;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by Shane on 3/24/2015.
 */
public class DynamicTextureRegister {
    private final Map<String,DynamicTexture> mapTextureObjects = Maps.newHashMap();
    private final Map<String,ResourceLocation> mapResourceLocations = Maps.newHashMap();

    private static DynamicTextureRegister instance;

    public static DynamicTextureRegister getInstance()
    {
        if(instance==null)
        {
            instance=new DynamicTextureRegister();
        }
        return instance;
    }

    public  DynamicTexture getDynamicTexture(String name,int w, int h)
    {
        String fullname=name+'+'+w+'+'+h;
        if(mapTextureObjects.containsKey(fullname)) {
            return mapTextureObjects.get(fullname);
        }
        else
        {
            makeDynamicTextureWithResourceLocation(name,w,h);
            return getDynamicTexture(name,w,h);
        }
    }


    public  ResourceLocation getResourceLocation(String name,int w, int h)
    {
        String fullname=name+'+'+w+'+'+h;
        if(mapResourceLocations.containsKey(fullname)) {
            return mapResourceLocations.get(fullname);
        }
        else
        {
            makeDynamicTextureWithResourceLocation(name,w,h);
            return getResourceLocation(name,w,h);
        }
    }

    private void makeDynamicTextureWithResourceLocation(String name, int w, int h) {
        String fullname=name+'+'+w+'+'+h;
        TextureManager texManager= FMLClientHandler.instance().getClient().getTextureManager();

        DynamicTexture dynamicTexture=new DynamicTexture(w,h);//bufferedImage);;
        ResourceLocation dynamicTextureLocation=texManager.getDynamicTextureLocation(fullname,dynamicTexture);
        mapTextureObjects.put(fullname,dynamicTexture);
        mapResourceLocations.put(fullname,dynamicTextureLocation);

    }

    public void updateDynamicTexture(String name, int width, int height, int[] data) {
        DynamicTexture dynamicTexture=getDynamicTexture(name,width,height);

        int numPixs = dynamicTexture.getTextureData().length;
        for(int i=0;i<numPixs;i++) {
            dynamicTexture.getTextureData()[i] =data[i];
        }

        dynamicTexture.updateDynamicTexture();
    }
}
