package com.parrot.oregano.client.render.tileentity;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.tileentity.TileEntityBlob;
import com.parrot.oregano.tileentity.TileEntityBottleRack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.src.FMLRenderAccessLibrary;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Created by Shane on 3/14/2015.
 */
public class TileEntityRendererBottleRack extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x  , (float) y  , (float) z  );
        GL11.glPushMatrix();

        //---render code goes here-------

        //GL11.glDisable(GL11.GL_TEXTURE_2D);

        //net.minecraftforge.client.model.obj.TextureCoordinate// TODO: Try to make a new extension of the WavefrontModel loader renderer thingy that can translate UVs according to a parameter. this will allow me to do things like Carpenters material borrowing.

//        IModelCustom model=AdvancedModelLoader.loadModel(new ResourceLocation(Oregano.MODID.toLowerCase(),"models/ChestBarrelMini.obj"));
//        bindTexture(new ResourceLocation("minecraft", "textures/blocks/planks_spruce.png"));
//        model.renderPart("Box");
//        model.renderPart("Lid");
//        bindTexture(new ResourceLocation("minecraft", "textures/blocks/iron_block.png"));
//        model.renderPart("Clasp");
//        bindTexture(new ResourceLocation("minecraft", "textures/blocks/wool_colored_purple.png"));
//        model.renderPart("Lining");

        IModelCustom model=AdvancedModelLoader.loadModel(new ResourceLocation(Oregano.MODID.toLowerCase(),"models/Easel.obj"));
        bindTexture(new ResourceLocation("minecraft", "textures/blocks/planks_spruce.png"));
        model.renderPart("Easel");
        bindTexture(new ResourceLocation("minecraft", "textures/blocks/wool_colored_white.png"));
        model.renderPart("CanvasBlock");
//        bindTexture(new ResourceLocation(((TileEntityBottleRack)entity).texture));
//        model.renderPart("Cube");
//        bindTexture(new ResourceLocation("minecraft", "textures/blocks/glass_pink.png"));
//        model.renderPart("Suzanne");

        //GL11.glEnable(GL11.GL_TEXTURE_2D);

        //---render code stops here------

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }



}


