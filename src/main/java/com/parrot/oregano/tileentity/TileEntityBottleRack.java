package com.parrot.oregano.tileentity;

import com.parrot.oregano.client.render.special.TileEntityRendererRotatable;
import com.parrot.oregano.init.ModModels;
import com.parrot.oregano.util.LogHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import scala.tools.nsc.doc.model.Public;

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

        IModelCustom model = ModModels.chestBarrelMini;
        tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/planks_spruce.png"));

        model.renderPart("Box");
        model.renderPart("Lid");
        tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/iron_block.png"));
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D,37);

        model.renderPart("Clasp");
        tesr.bindTexturePublic(new ResourceLocation("minecraft", "textures/blocks/wool_colored_purple.png"));
        model.renderPart("Lining");

    }

}
