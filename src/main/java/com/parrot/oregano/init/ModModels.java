package com.parrot.oregano.init;

import com.parrot.oregano.Oregano;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * Created by Shane on 3/24/2015.
 */
public class ModModels {

    public static final IModelCustom easel= AdvancedModelLoader.loadModel(new ResourceLocation(Oregano.MODID.toLowerCase(), "models/Easel.obj"));
    public static final IModelCustom chestBarrelMini= AdvancedModelLoader.loadModel(new ResourceLocation(Oregano.MODID.toLowerCase(), "models/ChestBarrelMini.obj"));
    public static final IModelCustom lecternDouble= AdvancedModelLoader.loadModel(new ResourceLocation(Oregano.MODID.toLowerCase(), "models/LecternDouble.obj"));

}
